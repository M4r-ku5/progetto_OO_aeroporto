package model;


// ******************************************************************************************
//  *********************************** CLASSE UTENTE ****************************************
//   ******************************************************************************************

public class Utente {

    /** Login dell'Utente. */
    private String login;

    /** Password dell'Utente. */
    private String password;




    /**
     * Costruttore della classe Utente
     @param login Username dell'utente
     @param password Password dell'utente
     */
    public Utente (String login, String password) {
        this.login = login;
        this.password = password;
    }




// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di login
     * @return login
     */
    public String getLogin () {
        return login;
    }

    /**
     * Setter di login
     * @param login Login dell'Utente
     */
    public void setLogin (String login) {
        this.login = login;
    }


    /**
     * Getter di password
     * @return password
     */
    public String getPassword () {
        return password;
    }

    /**
     * Setter di password
     * @param password Password dell'Utente
     */
    public void setPassword (String password) {
        this.password = password;
    }




    // ************************************************************************************
    //  ************************************** METODI **************************************
    //   ************************************************************************************

    /**
     * Metodo per l'accesso dell'Utente tramite le credenziali.
     */
    public void accedi () {}

}