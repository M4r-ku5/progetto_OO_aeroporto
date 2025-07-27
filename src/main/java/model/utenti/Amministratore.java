package model.utenti;

// ******************************************************************************************
//  ******************************* CLASSE AMMINISTRATORE ************************************
//   ******************************************************************************************

public class Amministratore extends Utente {

    /**
     * Costruttore della classe Amministratore.
     * @param login Login dell'Utente.
     * @param password Password dell'Utente.
     */
    public Amministratore (String login, String password) {
        super(login, password);
    }
}
