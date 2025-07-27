package model.utenti;

// ******************************************************************************************
//  ********************************* CLASSE UTENTE GENERICO *********************************
//   ******************************************************************************************

/**
 * Classe che rappresenta un utente generico del sistema.
 * Estende la classe Utente base.
 */
public class UtenteGenerico extends Utente {

    /**
     * Costruttore della classe UtenteGenerico.
     * @param login Login dell'Utente.
     * @param password Password dell'Utente.
     */
    public UtenteGenerico(String login, String password) {
        super(login, password);
    }

}