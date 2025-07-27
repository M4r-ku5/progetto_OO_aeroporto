package dao.postgres;

import dao.util.ConnessioneDatabase;
import model.volo.Gate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

// ******************************************************************************************
//  *********************** CLASSE GATE IMPLEMENTAZIONE POSTGRES DAO ************************
//   ******************************************************************************************

/**
 * Implementazione PostgreSQL del DAO per i Gate.
 * Segue le indicazioni del professore per l'uso di JDBC.
 */
public class GateImplDAO implements GateDAO {
    /** Logger per la classe. */
    private static final Logger logger = Logger.getLogger(GateImplDAO.class.getName());

    /** Connessione al database. */
    private Connection connection;

    /**
     * Costruttore che inizializza la connessione al database.
     */
    public GateImplDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'inizializzazione della connessione al database", e);
        }
    }

    /**
     * Restituisce la lista di tutti i gate presenti nel database.
     * @return lista di gate
     */
    @Override
    public List<Gate> findAll() {
        List<Gate> gates = new ArrayList<>();
        String querySQL = "SELECT \"numero_gate\" FROM public.\"gate\"";

        try (PreparedStatement query = connection.prepareStatement(querySQL);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                short numeroGate = rs.getShort("numero_gate");
                gates.add(new Gate(numeroGate));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante il recupero di tutti i gate", e);
        }

        return gates;
    }

    /**
     * Trova un gate tramite il numero.
     * @param numeroGate numero del gate
     * @return il gate trovato, null se non esiste
     */
    @Override
    public Gate findByNumero(short numeroGate) {
        String querySQL = "SELECT \"numero_gate\" FROM public.\"gate\" WHERE \"numero_gate\" = ?";

        try (PreparedStatement query = connection.prepareStatement(querySQL)) {
            query.setShort(1, numeroGate);

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    short numero = rs.getShort("numero_gate");
                    return new Gate(numero);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, () -> "Errore durante la ricerca del gate con numero: " + numeroGate);
            logger.log(Level.SEVERE, "Dettagli errore SQL:", e);
        }

        return null;
    }

    /**
     * Salva un nuovo gate nel database.
     * @param gate gate da salvare
     * @return true se salvato con successo, false altrimenti
     */
    @Override
    public boolean save(Gate gate) {
        String querySQL = "INSERT INTO public.\"gate\" (\"numero_gate\") VALUES (?)";

        try (PreparedStatement addGatePS = connection.prepareStatement(querySQL)) {
            addGatePS.setShort(1, gate.getNumeroGate());

            int rowsAffected = addGatePS.executeUpdate();

            boolean success = rowsAffected > 0;
            if (success) {
                logger.log(Level.INFO, "Inserimento gate completato con successo - Righe affected: {0}", rowsAffected);
            } else {
                logger.log(Level.WARNING, "Inserimento gate fallito - Righe affected: {0}", rowsAffected);
            }
            return success;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore SQL durante inserimento gate", e);
            return false;
        }
    }

    /**
     * Elimina un gate dal database.
     * @param numeroGate numero del gate da eliminare
     * @return true se eliminato con successo, false altrimenti
     */
    @Override
    public boolean delete(short numeroGate) {
        String querySQL = "DELETE FROM public.\"gate\" WHERE \"numero_gate\" = ?";

        try (PreparedStatement deleteGatePS = connection.prepareStatement(querySQL)) {
            deleteGatePS.setShort(1, numeroGate);

            int rowsAffected = deleteGatePS.executeUpdate();

            boolean success = rowsAffected > 0;
            if (success) {
                logger.log(Level.INFO, "Eliminazione gate completata con successo - Righe affected: {0}", rowsAffected);
            } else {
                logger.log(Level.WARNING, "Eliminazione gate fallita - Righe affected: {0}", rowsAffected);
            }
            return success;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore SQL durante eliminazione gate", e);
            return false;
        }
    }
}
