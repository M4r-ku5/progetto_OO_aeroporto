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
     * Getter di prenotazioni. Ritorna una copia della lista di prenotazioni originale.
     * @return new ArrayList<>(this.prenotazioni).
     */
    public List<Prenotazione> getPrenotazioni () {
        return new ArrayList<>(this.prenotazioni);
    }




// ************************************************************************************
//  ************************************** METODI **************************************
//   ************************************************************************************

    /**
     * Metodo per aggiungere una prenotazione.
     * @param p Prenotazione da aggiungere.
     */
    public void effettuaPrenotazione (Prenotazione p) {

        // Aggiunge la Prenotazione alla lista di prenotazioni.
        prenotazioni.add(p);

        // Imposta il collegamento tra l'Utente Generico che effettua la Prenotazione e la
        // Prenotazione stessa.
        p.setUtenteGenerico(this);
    }


    public void cercaPrenotazione () {
        // La logica è implementata nel controller.
    }

    public void modificaPrenotazione () {
        // La logica è implementata nel controller.
    }
}