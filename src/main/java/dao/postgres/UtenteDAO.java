package dao.postgres;

import model.utenti.Utente;
import java.util.List;

/**
 * Interfaccia DAO per la gestione degli Utenti nel database.
 */
public interface UtenteDAO {

    /**
     * Trova tutti gli utenti nel database.
     * @return Lista di tutti gli utenti.
     */
    List<Utente> findAll();

    /**
     * Trova un utente tramite login e password.
     * @param login Login dell'utente.
     * @param password Password dell'utente.
     * @return L'utente trovato, null se non esiste.
     */
    Utente findByLoginAndPassword(String login, String password);

    /**
     * Salva un nuovo utente nel database.
     * @param utente Utente da salvare.
     * @return true se salvato con successo, false altrimenti.
     */
    boolean save(Utente utente);

    /**
     * Aggiorna un utente esistente nel database.
     * @param utente Utente da aggiornare.
     * @return true se aggiornato con successo, false altrimenti.
     */
    boolean update(Utente utente);

    /**
     * Elimina un utente dal database.
     * @param login Login dell'utente da eliminare.
     * @return true se eliminato con successo, false altrimenti.
     */
    boolean delete(String login);
}
