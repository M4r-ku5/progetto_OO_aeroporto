package dao.postgres;

import dao.util.ConnessioneDatabase;
import model.prenotazione.Prenotazione;
import model.prenotazione.StatoPrenotazione;
import model.volo.Volo;
import model.volo.VoloInPartenza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;



// ******************************************************************************************
//  **************** CLASSE PRENOTAZIONE IMPLEMENTAZIONE POSTGRES DAO ************************
//   ******************************************************************************************

/**
 * Implementazione PostgreSQL del DAO per le Prenotazioni.
 * Segue le indicazioni del professore per l'uso di JDBC.
 */
public class PrenotazioneImplDAO implements PrenotazioneDAO {
    /** Logger per la classe. */
    private static final Logger logger = Logger.getLogger(PrenotazioneImplDAO.class.getName());

    /** Costanti per i nomi delle colonne del database. */
    private static final String NUMERO_BIGLIETTO = "numero_biglietto";
    private static final String NOME_PASSEGGERO = "nome_passeggero";
    private static final String POSTO_ASSEGNATO = "posto_assegnato";
    private static final String STATO_PRENOTAZIONE = "stato_prenotazione";
    private static final String CODICE_VOLO = "codice_volo";

    /** Costanti per i messaggi di log. */
    private static final String SUCCESSO = "SUCCESSO";
    private static final String FALLITO = "FALLITO";
    private static final String RIGHE_AFFECTED = " - Righe affected: ";

    /** Connessione al database. */
    private Connection connection;

    /**
     * Costruttore che inizializza la connessione al database.
     */
    public PrenotazioneImplDAO() {
        try {
                connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'inizializzazione della connessione al database", e);
        }
    }

    /**
     * Restituisce la lista di tutte le prenotazioni presenti nel database.
     * @return lista di prenotazioni
     */
    @Override
    public List<Prenotazione> findAll() {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        String querySQL = "SELECT \"numero_biglietto\", \"nome_passeggero\", \"posto_assegnato\", \"stato_prenotazione\", \"codice_volo\" FROM public.\"prenotazione\"";

        try (PreparedStatement query = connection.prepareStatement(querySQL);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String numeroBiglietto = rs.getString(NUMERO_BIGLIETTO);
                String nomePasseggero = rs.getString(NOME_PASSEGGERO);
                String postoAssegnato = rs.getString(POSTO_ASSEGNATO);
                String statoStr = rs.getString(STATO_PRENOTAZIONE);
                String codiceVolo = rs.getString(CODICE_VOLO);

                Prenotazione prenotazione = new Prenotazione(nomePasseggero, numeroBiglietto, postoAssegnato);
                prenotazione.setStatoDellaPrenotazione(StatoPrenotazione.valueOf(statoStr));

                if (codiceVolo != null && !codiceVolo.trim().isEmpty()) {
                    VoloImplDAO voloDAO = new VoloImplDAO();
                    Volo volo = voloDAO.findByCodice(codiceVolo);
                    if (volo instanceof VoloInPartenza voloInPartenza) {
                        prenotazione.setVoloInPartenza(voloInPartenza);
                    }
                }

                prenotazioni.add(prenotazione);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante il recupero delle prenotazioni", e);
        }

        return prenotazioni;
    }

    /**
     * Trova una prenotazione tramite il numero del biglietto.
     * @param numeroBiglietto numero del biglietto
     * @return la prenotazione trovata, null se non esiste
     */
    @Override
    public Prenotazione findByNumeroBiglietto(String numeroBiglietto) {
        String querySQL = "SELECT \"numero_biglietto\", \"nome_passeggero\", \"posto_assegnato\", \"stato_prenotazione\" FROM public.\"prenotazione\" WHERE \"numero_biglietto\" = ?";

        try (PreparedStatement query = connection.prepareStatement(querySQL)) {
            query.setString(1, numeroBiglietto);

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String nomePasseggero = rs.getString(NOME_PASSEGGERO);
                    String postoAssegnato = rs.getString(POSTO_ASSEGNATO);
                    String statoStr = rs.getString(STATO_PRENOTAZIONE);

                    Prenotazione prenotazione = new Prenotazione(nomePasseggero, numeroBiglietto, postoAssegnato);
                    prenotazione.setStatoDellaPrenotazione(StatoPrenotazione.valueOf(statoStr));
                    return prenotazione;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la ricerca della prenotazione con numero: " + numeroBiglietto, e);
        }

        return null;
    }

    /**
     * Trova le prenotazioni tramite il nome del passeggero.
     * @param nomePasseggero nome del passeggero
     * @return lista delle prenotazioni trovate
     */
    @Override
    public List<Prenotazione> findByNomePasseggero(String nomePasseggero) {
        List<Prenotazione> risultati = new ArrayList<>();
        String querySQL = "SELECT \"numero_biglietto\", \"nome_passeggero\", \"posto_assegnato\", \"stato_prenotazione\", \"codice_volo\" FROM public.\"prenotazione\"";
        if (nomePasseggero != null && !nomePasseggero.trim().isEmpty()) {
            querySQL += " WHERE \"nome_passeggero\" ILIKE ?";
        }

        try (PreparedStatement query = connection.prepareStatement(querySQL)) {
            if (nomePasseggero != null && !nomePasseggero.trim().isEmpty()) {
                query.setString(1, "%" + nomePasseggero + "%");
            }

            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String numeroBiglietto = rs.getString(NUMERO_BIGLIETTO);
                    String nomePasseggeroDB = rs.getString(NOME_PASSEGGERO);
                    String postoAssegnato = rs.getString(POSTO_ASSEGNATO);
                    String statoStr = rs.getString(STATO_PRENOTAZIONE);
                    String codiceVolo = rs.getString(CODICE_VOLO);

                    Prenotazione prenotazione = new Prenotazione(nomePasseggeroDB, numeroBiglietto, postoAssegnato);
                    prenotazione.setStatoDellaPrenotazione(StatoPrenotazione.valueOf(statoStr));

                    if (codiceVolo != null && !codiceVolo.trim().isEmpty()) {
                        VoloImplDAO voloDAO = new VoloImplDAO();
                        Volo volo = voloDAO.findByCodice(codiceVolo);
                        if (volo instanceof VoloInPartenza voloInPartenza) {
                            prenotazione.setVoloInPartenza(voloInPartenza);
                        }
                    }
                    risultati.add(prenotazione);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la ricerca per nome passeggero", e);
        }

        return risultati;
    }

    /**
     * Trova le prenotazioni tramite il codice del volo.
     * @param codiceVolo codice del volo
     * @return lista delle prenotazioni trovate
     */
    @Override
    public List<Prenotazione> findByCodiceVolo(String codiceVolo) {
        List<Prenotazione> risultati = new ArrayList<>();
        String querySQL = "SELECT \"numero_biglietto\", \"nome_passeggero\", \"posto_assegnato\", \"stato_prenotazione\", \"codice_volo\" FROM public.\"prenotazione\" WHERE \"codice_volo\" = ?";

        try (PreparedStatement query = connection.prepareStatement(querySQL)) {
            query.setString(1, codiceVolo);

            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String numeroBiglietto = rs.getString(NUMERO_BIGLIETTO);
                    String nomePasseggero = rs.getString(NOME_PASSEGGERO);
                    String postoAssegnato = rs.getString(POSTO_ASSEGNATO);
                    String statoStr = rs.getString(STATO_PRENOTAZIONE);
                    String codiceVoloDB = rs.getString(CODICE_VOLO);

                    Prenotazione prenotazione = new Prenotazione(nomePasseggero, numeroBiglietto, postoAssegnato);
                    prenotazione.setStatoDellaPrenotazione(StatoPrenotazione.valueOf(statoStr));

                    if (codiceVoloDB != null && !codiceVoloDB.trim().isEmpty()) {
                        VoloImplDAO voloDAO = new VoloImplDAO();
                        Volo volo = voloDAO.findByCodice(codiceVoloDB);
                        if (volo instanceof VoloInPartenza voloInPartenza) {
                            prenotazione.setVoloInPartenza(voloInPartenza);
                        }
                    }

                    risultati.add(prenotazione);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la ricerca per codice volo", e);
        }

        return risultati;
    }

    /**
     * Salva una nuova prenotazione nel database.
     * @param prenotazione prenotazione da salvare
     * @return true se salvata con successo, false altrimenti
     */
    @Override
    public boolean save(Prenotazione prenotazione) {
        String querySQL = "INSERT INTO public.\"prenotazione\" (\"numero_biglietto\", \"nome_passeggero\", \"posto_assegnato\", \"stato_prenotazione\", \"codice_volo\", \"utente_login\") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement addPrenotazionePS = connection.prepareStatement(querySQL)) {
            addPrenotazionePS.setString(1, prenotazione.getNumeroBiglietto());
            addPrenotazionePS.setString(2, prenotazione.getNomePasseggero());
            addPrenotazionePS.setString(3, prenotazione.getPostoAssegnato());
            addPrenotazionePS.setString(4, prenotazione.getStatoDellaPrenotazione().name());

            String codiceVolo = "";
            if (prenotazione.getVoloInPartenza() != null) {
                codiceVolo = prenotazione.getVoloInPartenza().getCodiceVolo();
            }
            addPrenotazionePS.setString(5, codiceVolo);
            addPrenotazionePS.setString(6, "user");

            int rowsAffected = addPrenotazionePS.executeUpdate();

            boolean success = rowsAffected > 0;
            logger.log(Level.INFO, "Inserimento prenotazione: {0}{1}{2}",
                      new Object[]{success ? SUCCESSO : FALLITO, RIGHE_AFFECTED, rowsAffected});
            return success;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore SQL durante inserimento prenotazione", e);
            return false;
        }
    }

    /**
     * Salva una nuova prenotazione nel database associata a un codice volo.
     * @param prenotazione prenotazione da salvare
     * @param codiceVolo codice del volo associato
     * @return true se salvata con successo, false altrimenti
     */
    @Override
    public boolean save(Prenotazione prenotazione, String codiceVolo) {
        String querySQL = "INSERT INTO public.\"prenotazione\" (\"numero_biglietto\", \"nome_passeggero\", \"posto_assegnato\", \"stato_prenotazione\", \"codice_volo\", \"utente_login\") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement addPrenotazionePS = connection.prepareStatement(querySQL)) {
            addPrenotazionePS.setString(1, prenotazione.getNumeroBiglietto());
            addPrenotazionePS.setString(2, prenotazione.getNomePasseggero());
            addPrenotazionePS.setString(3, prenotazione.getPostoAssegnato());
            addPrenotazionePS.setString(4, prenotazione.getStatoDellaPrenotazione().name());
            addPrenotazionePS.setString(5, codiceVolo);
            addPrenotazionePS.setString(6, "user");

            int rowsAffected = addPrenotazionePS.executeUpdate();

            boolean success = rowsAffected > 0;
            logger.log(Level.INFO, "Inserimento prenotazione con codice volo: {0}{1}{2}",
                      new Object[]{success ? SUCCESSO : FALLITO, RIGHE_AFFECTED, rowsAffected});
            return success;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore SQL durante inserimento prenotazione con codice volo", e);
            return false;
        }
    }

    /**
     * Aggiorna una prenotazione esistente nel database.
     * @param numeroBiglietto numero del biglietto
     * @param nuovoNomePasseggero nuovo nome del passeggero
     * @param nuovoPostoAssegnato nuovo posto assegnato
     * @return true se aggiornata con successo, false altrimenti
     */
    @Override
    public boolean update(String numeroBiglietto, String nuovoNomePasseggero, String nuovoPostoAssegnato) {
        String querySQL = "UPDATE public.\"prenotazione\" SET \"nome_passeggero\" = ?, \"posto_assegnato\" = ?, \"stato_prenotazione\" = ? WHERE \"numero_biglietto\" = ?";

        try (PreparedStatement updatePrenotazionePS = connection.prepareStatement(querySQL)) {
            updatePrenotazionePS.setString(1, nuovoNomePasseggero);
            updatePrenotazionePS.setString(2, nuovoPostoAssegnato);
            updatePrenotazionePS.setString(3, StatoPrenotazione.IN_ATTESA.name());
            updatePrenotazionePS.setString(4, numeroBiglietto);

            int rowsAffected = updatePrenotazionePS.executeUpdate();

            boolean success = rowsAffected > 0;
            logger.log(Level.INFO, "Aggiornamento prenotazione: {0}{1}{2}",
                      new Object[]{success ? SUCCESSO : FALLITO, RIGHE_AFFECTED, rowsAffected});
            return success;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'aggiornamento della prenotazione", e);
            return false;
        }
    }

    /**
     * Elimina una prenotazione dal database.
     * @param numeroBiglietto numero del biglietto
     * @return true se eliminata con successo, false altrimenti
     */
    @Override
    public boolean delete(String numeroBiglietto) {
        String querySQL = "DELETE FROM public.\"prenotazione\" WHERE \"numero_biglietto\" = ?";

        try (PreparedStatement deletePrenotazionePS = connection.prepareStatement(querySQL)) {
            deletePrenotazionePS.setString(1, numeroBiglietto);
            int rowsAffected = deletePrenotazionePS.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'eliminazione della prenotazione", e);
            return false;
        }
    }
}
