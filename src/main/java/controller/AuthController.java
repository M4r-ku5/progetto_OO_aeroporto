package controller;

import dao.postgres.UtenteDAO;
import dao.postgres.UtenteImplDAO;
import model.utenti.Amministratore;
import model.utenti.Utente;



// ******************************************************************************************
//  ******************************* CLASSE AUTH CONTROLLER ***********************************
//   ******************************************************************************************

/**
 * Controller per la gestione dell'autenticazione degli utenti.
 */
public class AuthController {
    /** DAO per la gestione degli utenti. */
    private final UtenteDAO utenteDAO;
    /** Utente attualmente loggato. */
    private Utente utenteLoggato;

    /**
     * Costruttore di AuthController.
     */
    public AuthController() {
        this.utenteDAO = new UtenteImplDAO();
        this.utenteLoggato = null;
    }

    /**
     * Metodo che gestisce il login dell'Utente.
     * @param login Login dell'Utente.
     * @param password Password dell'Utente.
     * @return true se il login va a buon fine, altrimenti false.
     */
    public boolean login(String login, String password) {
        Utente utente = utenteDAO.findByLoginAndPassword(login, password);
        if (utente != null) {
            this.utenteLoggato = utente;
            return true;
        }
        return false;
    }

    /**
     * Metodo di logout dell'Utente che setta a null l'Utente Loggato.
     */
    public void logout() {
        this.utenteLoggato = null;
    }

    /**
     * Metodo per verificare che l'Utente Loggato sia Amministratore.
     * @return true se l'Utente Loggato è Amministratore, altrimenti false.
     */
    public boolean isAmministratore() {
        return this.utenteLoggato instanceof Amministratore;
    }

    /**
     * Getter per l'utente attualmente loggato.
     * @return L'utente loggato, null se nessuno è loggato.
     */
    public Utente getUtenteLoggato() {
        return this.utenteLoggato;
    }

    /**
     * Verifica se c'è un utente loggato.
     * @return true se c'è un utente loggato, false altrimenti.
     */
    public boolean isLoggato() {
        return this.utenteLoggato != null;
    }
}
