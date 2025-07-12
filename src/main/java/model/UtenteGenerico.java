package model;

import java.util.ArrayList;
import java.util.List;


// ******************************************************************************************
//  ********************************* CLASSE UTENTE GENERICO *********************************
//   ******************************************************************************************

public class UtenteGenerico extends Utente {

    /** Lista delle prenotazioni. */
    private ArrayList<Prenotazione> prenotazioni;




    /**
     * Costruttore della classe UtenteGenerico.
     * @param login Login dell'Utente.
     * @param password Password dell'Utente.
     */
    public UtenteGenerico(String login, String password) {
        super(login, password);
        this.prenotazioni = new ArrayList<>();
    }




// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di prenotazioni
     * @return prenotazioni
     */
    public List<Prenotazione> getPrenotazioni () {
        return prenotazioni;
    }




// ************************************************************************************
//  ************************************** METODI **************************************
//   ************************************************************************************

    /**
     * Metodo per effettuare una prenotazione. Aggiunge la prenotazione passata per
     * parametro e poi la lega all'Utente tramite il metodo setUtente di Prenotazione.
     * @param p Prenotazione da aggiungere.
     */
    public void effettuaPrenotazione (Prenotazione p) {
        prenotazioni.add(p);
        p.setUtenteGenerico(this);
    }


    public void cercaPrenotazione () {}

    public void modificaPrenotazione () {}
}