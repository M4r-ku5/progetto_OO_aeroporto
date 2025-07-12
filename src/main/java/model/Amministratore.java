package model;

import java.util.ArrayList;
import java.util.List;


// ******************************************************************************************
//  ******************************* CLASSE AMMINISTRATORE ************************************
//   ******************************************************************************************

public class Amministratore extends Utente {

    /** Lista di voli */
    private List<Volo> voli;




    /**
     * Costruttore della classe Amministratore.
     * @param login Login dell'Utente.
     * @param password Password dell'Utente.
     */
    public Amministratore (String login, String password) {
        super(login, password);
        this.voli = new ArrayList<>();
    }




// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di voli.
     * @return voli.
     */
    public List<Volo> getVoli () {
        return voli;
    }




    // ************************************************************************************
    //  ************************************** METODI **************************************
    //   ************************************************************************************

    public void inserisciVolo () {}

    public void aggiornaVolo () {}

    public void assegnaGate () {}

}
