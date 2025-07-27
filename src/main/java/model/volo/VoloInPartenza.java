package model.volo;

import model.prenotazione.Prenotazione;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



// ******************************************************************************************
//  ******************************* CLASSE VOLO IN PARTENZA **********************************
//   ******************************************************************************************

/**
 * Classe che rappresenta un volo in partenza dall'aeroporto.
 * Estende la classe base Volo aggiungendo informazioni specifiche per i voli in partenza.
 */
public class VoloInPartenza extends Volo {

    /** Aeroporto di destinazione del volo in partenza. */
    private String aeroportoDestinazione;

    /** Gate assegnato al volo in partenza. */
    private Gate gate;

    /** Lista delle prenotazioni associate a questo volo. */
    private final List<Prenotazione> prenotazioni;

    /**
     * Costruttore per VoloInPartenza.
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
        this.aeroportoDestinazione = aeroportoDestinazione;
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
     * @param aeroportoDestinazione Aeroporto di Destinazione del Volo in Partenza.
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


    @Override
    public String getTipoVolo() {
        return "Partenza";
    }
}
