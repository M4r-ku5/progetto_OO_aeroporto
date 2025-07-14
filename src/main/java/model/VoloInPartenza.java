package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;


// ******************************************************************************************
//  ****************************** CLASSE VOLO IN PARTENZA ***********************************
//   ******************************************************************************************

public class VoloInPartenza extends Volo {

    /** Aeroporto di Origine del Volo in Arrivo.*/
    private String aeroportoDestinazione;

    /** Riferimento al Gate associato. */
    private Gate gate;

    private List<Prenotazione> prenotazioni;




    /**
     *
     * @param codiceVolo Codice del Volo.
     * @param compagniaAerea Compagnia Aerea del Volo.
     * @param dataVolo Data del Volo.
     * @param orarioVolo Orario del Volo.
     * @param ritardoVolo Ritardo del Volo.
     * @param aeroportoDestinazione Aeroporto di Destinazione del Volo in Partenza.
     */
    public VoloInPartenza(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                        LocalTime orarioVolo, Duration ritardoVolo, String aeroportoDestinazione) {

        super(codiceVolo, compagniaAerea, dataVolo, orarioVolo, ritardoVolo);
        this.aeroportoDestinazione =  aeroportoDestinazione;
        this.prenotazioni = new ArrayList<>();

    }




// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di aeroportoDestinazione.
     * @return aeroportoDestinazione.
     */
    public String getAeroportoDestinazione() {
        return aeroportoDestinazione;
    }

    /**
     * Setter di aeroportoDestinazione.
     * @param aeroportoDestinazione aeroportoDestinazione.
     */
    public void setAeroportoDestinazione(String aeroportoDestinazione) {
        this.aeroportoDestinazione = aeroportoDestinazione;
    }


    /**
     * Getter di gate.
     * @return gate.
     */
    public Gate getGate() {
        return gate;
    }

    /**
     * Setter di gate.
     * @param gate Riferimento al Gate associato.
     */
    public void setGate(Gate gate) {
        this.gate = gate;
    }


    /**
     * Restituisce una copia della lista delle prenotazioni per proteggere l'originale.
     * @return new ArrayList<>(this.prenotazioni).
     */
    public List<Prenotazione> getPrenotazioni() {
        return new ArrayList<>(this.prenotazioni);
    }




// ************************************************************************************
//  ************************************** METODI **************************************
//   ************************************************************************************

    /**
     * Metodo che aggiunge la Prenotazione alla lista di prenotazioni di questo Volo e imposta
     * il collegamento col Volo.
     * @param p La Prenotazione da aggiungere.
     */
    public void aggiungiPrenotazione(Prenotazione p) {
        this.prenotazioni.add(p);
        p.setVoloInPartenza(this);
    }

}
