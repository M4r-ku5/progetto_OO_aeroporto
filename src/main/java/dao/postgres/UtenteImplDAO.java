package dao.postgres;

import dao.util.ConnessioneDatabase;
import model.utenti.Utente;
import model.utenti.Amministratore;
import model.utenti.UtenteGenerico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

// ******************************************************************************************
//  ******************** CLASSE UTENTE IMPLEMENTAZIONE POSTGRES DAO *************************
//   ******************************************************************************************

/**
 * Implementazione PostgreSQL del DAO per gli Utenti.
 * Segue le indicazioni del professore per l'uso di JDBC.
 */
public class UtenteImplDAO implements UtenteDAO {
    /** Logger per la classe. */
    private static final Logger logger = Logger.getLogger(UtenteImplDAO.class.getName());

    /** Costanti per i tipi di utente. */
    private static final String AMMINISTRATORE = "AMMINISTRATORE";
    private static final String UTENTE_GENERICO = "UTENTE_GENERICO";

    /** Connessione al database. */
    private Connection connection;

    /**
     * Costruttore che inizializza la connessione al database.
     */
    public UtenteImplDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'inizializzazione della connessione al database", e);
        }
    }

    /**
     * Restituisce la lista di tutti gli utenti presenti nel database.
     * @return lista di utenti
     */
    @Override
    public List<Utente> findAll() {
        List<Utente> utenti = new ArrayList<>();
        String querySQL = "SELECT \"login\", \"password\", \"ruolo\" FROM public.\"utente\"";

        try (PreparedStatement query = connection.prepareStatement(querySQL);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                String tipoUtente = rs.getString("ruolo");

                Utente utente;
                if (AMMINISTRATORE.equals(tipoUtente)) {
                    utente = new Amministratore(login, password);
                } else {
                    utente = new UtenteGenerico(login, password);
                }
                utenti.add(utente);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante il recupero degli utenti", e);
        }

        return utenti;
    }

    /**
     * Trova un utente tramite login e password.
     * @param login login dell'utente
     * @param password password dell'utente
     * @return l'utente trovato, null se non esiste
     */
    @Override
    public Utente findByLoginAndPassword(String login, String password) {
        String querySQL = "SELECT \"login\", \"password\", \"ruolo\" FROM public.\"utente\" WHERE \"login\" = ? AND \"password\" = ?";

        try (PreparedStatement query = connection.prepareStatement(querySQL)) {
            query.setString(1, login);
            query.setString(2, password);

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String tipoUtente = rs.getString("ruolo");

                    if (AMMINISTRATORE.equals(tipoUtente)) {
                        return new Amministratore(login, password);
                    } else {
                        return new UtenteGenerico(login, password);
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la ricerca dell'utente", e);
        }

        return null;
    }

    /**
     * Salva un nuovo utente nel database.
     * @param utente utente da salvare
     * @return true se salvato con successo, false altrimenti
     */
    @Override
    public boolean save(Utente utente) {
        String tipoUtente = (utente instanceof Amministratore) ? AMMINISTRATORE : UTENTE_GENERICO;
        String querySQL = "INSERT INTO public.\"utente\" (\"login\", \"password\", \"ruolo\") VALUES (?, ?, ?)";

        try (PreparedStatement addUtentePS = connection.prepareStatement(querySQL)) {
            addUtentePS.setString(1, utente.getLogin());
            addUtentePS.setString(2, utente.getPassword());
            addUtentePS.setString(3, tipoUtente);

            int rowsAffected = addUtentePS.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante il salvataggio dell'utente", e);
            return false;
        }
    }

    /**
     * Aggiorna un utente esistente nel database.
     * @param utente utente da aggiornare
     * @return true se aggiornato con successo, false altrimenti
     */
    @Override
    public boolean update(Utente utente) {
        String tipoUtente = (utente instanceof Amministratore) ? AMMINISTRATORE : UTENTE_GENERICO;
        String querySQL = "UPDATE public.\"utente\" SET \"password\" = ?, \"ruolo\" = ? WHERE \"login\" = ?";

        try (PreparedStatement updateUtentePS = connection.prepareStatement(querySQL)) {
            updateUtentePS.setString(1, utente.getPassword());
            updateUtentePS.setString(2, tipoUtente);
            updateUtentePS.setString(3, utente.getLogin());

            int rowsAffected = updateUtentePS.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'aggiornamento dell'utente", e);
            return false;
        }
    }

    /**
     * Elimina un utente dal database.
     * @param login login dell'utente da eliminare
     * @return true se eliminato con successo, false altrimenti
     */
    @Override
    public boolean delete(String login) {
        String querySQL = "DELETE FROM public.\"utente\" WHERE \"login\" = ?";

        try (PreparedStatement deleteUtentePS = connection.prepareStatement(querySQL)) {
            deleteUtentePS.setString(1, login);
            int rowsAffected = deleteUtentePS.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'eliminazione dell'utente", e);
            return false;
        }
    }
}
