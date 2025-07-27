package dao.postgres;

import dao.util.ConnessioneDatabase;
import model.volo.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Implementazione PostgreSQL del DAO per i Voli.
 * Segue le indicazioni del professore per l'uso di JDBC.
 */
public class VoloImplDAO implements VoloDAO {
    /** Logger per la classe. */
    private static final Logger logger = Logger.getLogger(VoloImplDAO.class.getName());

    /** Costanti per i messaggi di log. */
    private static final String SUCCESSO = "SUCCESSO";
    private static final String FALLITO = "FALLITO";
    private static final String RIGHE_AFFECTED = " - Righe affected: ";

    /** Connessione al database. */
    private Connection connection;

    /**
     * Costruttore che inizializza la connessione al database.
     */
    public VoloImplDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'inizializzazione della connessione al database", e);
        }
    }

    @Override
    public List<Volo> findAll() {
        List<Volo> voli = new ArrayList<>();
        String querySQL = "SELECT \"codice_volo\", \"compagnia_aerea\", \"data_volo\", \"orario_volo\", \"ritardo_minuti\", \"stato_volo\", \"tipo_volo\", \"aeroporto_origine\", \"aeroporto_destinazione\", \"numero_gate\" FROM public.\"volo\"";

        try (PreparedStatement query = connection.prepareStatement(querySQL);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                Volo volo = createVoloFromResultSet(rs);
                voli.add(volo);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante il recupero dei voli", e);
        }

        return voli;
    }

    @Override
    public Volo findByCodice(String codiceVolo) {
        String querySQL = "SELECT \"codice_volo\", \"compagnia_aerea\", \"data_volo\", \"orario_volo\", \"ritardo_minuti\", \"stato_volo\", \"tipo_volo\", \"aeroporto_origine\", \"aeroporto_destinazione\", \"numero_gate\" FROM public.\"volo\" WHERE UPPER(\"codice_volo\") = UPPER(?)";

        try (PreparedStatement stmt = connection.prepareStatement(querySQL)) {
            stmt.setString(1, codiceVolo.trim());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createVoloFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la ricerca del volo", e);
        }

        return null;
    }

    @Override
    public boolean save(Volo volo) {
        String querySQL = "INSERT INTO public.\"volo\" (\"codice_volo\", \"compagnia_aerea\", \"data_volo\", \"orario_volo\", \"ritardo_minuti\", \"stato_volo\", \"tipo_volo\", \"aeroporto_origine\", \"aeroporto_destinazione\", \"numero_gate\", \"amministratore_login\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement addVoloPS = connection.prepareStatement(querySQL)) {
            addVoloPS.setString(1, volo.getCodiceVolo());
            addVoloPS.setString(2, volo.getCompagniaAerea());
            addVoloPS.setDate(3, Date.valueOf(volo.getDataVolo()));
            addVoloPS.setTime(4, Time.valueOf(volo.getOrarioVolo()));
            addVoloPS.setLong(5, 0); // Ritardo fisso a 0 per ora
            addVoloPS.setString(6, "PROGRAMMATO"); // Stato fisso per ora
            addVoloPS.setString(7, volo instanceof VoloInPartenza ? "PARTENZA" : "ARRIVO");

            if (volo instanceof VoloInArrivo voloInArrivo) {
                addVoloPS.setString(8, voloInArrivo.getAeroportoOrigine());
                addVoloPS.setNull(9, Types.VARCHAR);
                addVoloPS.setNull(10, Types.SMALLINT);
            } else if (volo instanceof VoloInPartenza voloPartenza) {
                addVoloPS.setNull(8, Types.VARCHAR);
                addVoloPS.setString(9, voloPartenza.getAeroportoDestinazione());
                if (voloPartenza.getGate() != null) {
                    addVoloPS.setShort(10, voloPartenza.getGate().getNumeroGate());
                } else {
                    addVoloPS.setNull(10, Types.SMALLINT);
                }
            }

            addVoloPS.setString(11, "admin");
            int rowsAffected = addVoloPS.executeUpdate();

            boolean success = rowsAffected > 0;
            logger.log(Level.INFO, "Inserimento volo: {0}{1}{2}",
                      new Object[]{success ? SUCCESSO : FALLITO, RIGHE_AFFECTED, rowsAffected});
            return success;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore SQL durante inserimento volo", e);
            return false;
        }
    }

    @Override
    public boolean update(String codiceVolo, LocalTime nuovoOrario, Duration nuovoRitardo, StatoVolo nuovoStato) {
        String querySQL = "UPDATE public.\"volo\" SET \"orario_volo\" = ?, \"ritardo_minuti\" = ?, \"stato_volo\" = ? WHERE \"codice_volo\" = ?";

        try (PreparedStatement updateVoloPS = connection.prepareStatement(querySQL)) {
            updateVoloPS.setTime(1, Time.valueOf(nuovoOrario));
            updateVoloPS.setLong(2, nuovoRitardo.toMinutes());
            updateVoloPS.setString(3, nuovoStato.name());
            updateVoloPS.setString(4, codiceVolo);

            int rowsAffected = updateVoloPS.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'aggiornamento del volo", e);
            return false;
        }
    }

    @Override
    public boolean delete(String codiceVolo) {
        String querySQL = "DELETE FROM public.\"volo\" WHERE \"codice_volo\" = ?";

        try (PreparedStatement deleteVoloPS = connection.prepareStatement(querySQL)) {
            deleteVoloPS.setString(1, codiceVolo);
            int rowsAffected = deleteVoloPS.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'eliminazione del volo", e);
            return false;
        }
    }

    @Override
    public boolean updateGate(String codiceVolo, short numeroGate) {
        String querySQL = "UPDATE public.\"volo\" SET \"numero_gate\" = ? WHERE \"codice_volo\" = ?";

        try (PreparedStatement updateGatePS = connection.prepareStatement(querySQL)) {
            updateGatePS.setShort(1, numeroGate);
            updateGatePS.setString(2, codiceVolo);

            int rowsAffected = updateGatePS.executeUpdate();
            boolean success = rowsAffected > 0;

            logger.log(Level.INFO, "Aggiornamento gate per volo {0}: {1}{2}{3}",
                      new Object[]{codiceVolo, success ? SUCCESSO : FALLITO, RIGHE_AFFECTED, rowsAffected});

            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'aggiornamento del gate", e);
            return false;
        }
    }

    /**
     * Metodo di utilità per creare un volo a partire da un resultset.
     * @param rs Il resultset.
     * @return Il volo creato.
     * @throws SQLException Se si verifica un errore SQL
     */
    private Volo createVoloFromResultSet(ResultSet rs) throws SQLException {
        String codiceVolo = rs.getString("codice_volo");
        String compagniaAerea = rs.getString("compagnia_aerea");
        LocalDate dataVolo = rs.getDate("data_volo").toLocalDate();
        LocalTime orarioVolo = rs.getTime("orario_volo").toLocalTime();
        Duration ritardoVolo = Duration.ofMinutes(rs.getInt("ritardo_minuti"));
        String tipoVolo = rs.getString("tipo_volo");

        Volo volo;
        if ("PARTENZA".equals(tipoVolo)) {
            String aeroportoDestinazione = rs.getString("aeroporto_destinazione");
            volo = new VoloInPartenza(codiceVolo, compagniaAerea, dataVolo, orarioVolo, ritardoVolo, aeroportoDestinazione);

            short numeroGate = rs.getShort("numero_gate");
            if (!rs.wasNull()) {
                ((VoloInPartenza) volo).setGate(new Gate(numeroGate));
            }
        } else {
            String aeroportoOrigine = rs.getString("aeroporto_origine");
            volo = new VoloInArrivo(codiceVolo, compagniaAerea, dataVolo, orarioVolo, ritardoVolo, aeroportoOrigine);
        }

        String statoStr = rs.getString("stato_volo");
        if (statoStr != null) {
            volo.setStatoDelVolo(StatoVolo.valueOf(statoStr));
        }

        return volo;
    }
}
