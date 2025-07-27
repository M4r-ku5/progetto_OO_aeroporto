package model.volo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;



// ******************************************************************************************
//  ******************************* CLASSE VOLO IN ARRIVO ***********************************
//   ******************************************************************************************

/**
 * Classe che rappresenta un volo in arrivo all'aeroporto.
 * Estende la classe base Volo aggiungendo informazioni specifiche per i voli in arrivo.
 */
public class VoloInArrivo extends Volo {

    /** Aeroporto di origine del volo in arrivo. */
    private String aeroportoOrigine;

    /**
     * Costruttore per VoloInArrivo.
     * @param codiceVolo Codice del Volo.
     * @param compagniaAerea Compagnia Aerea del Volo.
     * @param dataVolo Data del Volo.
     * @param orarioVolo Orario del Volo.
     * @param ritardoVolo Ritardo del Volo.
     * @param aeroportoOrigine Aeroporto di Origine del Volo in Arrivo.
     */
    public VoloInArrivo(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                        LocalTime orarioVolo, Duration ritardoVolo, String aeroportoOrigine) {
        super(codiceVolo, compagniaAerea, dataVolo, orarioVolo, ritardoVolo);
        this.aeroportoOrigine = aeroportoOrigine;
    }



// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di aeroportoOrigine.
     * @return aeroportoOrigine.
     */
    public String getAeroportoOrigine() {
        return aeroportoOrigine;
    }

    /**
     * Setter di aeroportoOrigine.
     * @param aeroportoOrigine Aeroporto di Origine del Volo in Arrivo.
     */
    public void setAeroportoOrigine(String aeroportoOrigine) {
        this.aeroportoOrigine = aeroportoOrigine;
    }

    @Override
    public String getTipoVolo() {
        return "Arrivo";
    }
}
