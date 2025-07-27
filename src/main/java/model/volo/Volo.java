package model.volo;

import model.utenti.Amministratore;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;



// ******************************************************************************************
//  ************************************ CLASSE VOLO *****************************************
//   ******************************************************************************************


/**
 * Classe che rappresenta un Volo.
 */
public class Volo {

    /** Codice del Volo. */
    private String codiceVolo;

    /** Compagnia Aerea del Volo */
    private String compagniaAerea;

    /** Data del Volo. */
    private LocalDate dataVolo;

    /** Orario del Volo. */
    private LocalTime orarioVolo;

    /** Ritardo del Volo. */
    private Duration ritardoVolo;

    /** Stato del volo (PROGRAMMATO, IN_RITARDO, ATTERRATO, CANCELLATO) */
    private StatoVolo statoDelVolo;

    /** Riferimento al singolo Amministratore che gestisce una lista di Voli. */
    private Amministratore amministratore;




    /**
     * Costruttore della classe Volo.
     * @param codiceVolo Codice del Volo.
     * @param compagniaAerea Compagnia Aerea del Volo.
     * @param dataVolo Data del Volo.
     * @param orarioVolo Orario del Volo.
     * @param ritardoVolo Ritardo del Volo.
     */
    public Volo (String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                 LocalTime orarioVolo, Duration ritardoVolo) {
        this.codiceVolo = codiceVolo;
        this.compagniaAerea = compagniaAerea;
        this.dataVolo = dataVolo;
        this.orarioVolo = orarioVolo;
        this.ritardoVolo = ritardoVolo;
        this.statoDelVolo = StatoVolo.PROGRAMMATO;
    }



// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di codiceVolo.
     * @return codiceVolo.
     */
    public String getCodiceVolo() {
        return codiceVolo;
    }

    /**
     * Setter di codiceVolo.
     * @param codiceVolo Codice del Volo.
     */
    public void setCodiceVolo(String codiceVolo) {
        this.codiceVolo = codiceVolo;
    }


    /**
     * Getter di compagniaAerea.
     * @return compagniaAerea.
     */
    public String getCompagniaAerea() {
        return compagniaAerea;
    }

    /**
     * Setter di compagniaAerea.
     * @param compagniaAerea Compagnia Aerea del Volo.
     */
    public void setCompagniaAerea(String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }


    /**
     * Getter di dataVolo.
     * @return dataVolo.
     */
    public LocalDate getDataVolo() {
        return dataVolo;
    }

    /**
     * Setter di dataVolo.
     * @param dataVolo Data del Volo.
     */
    public void setDataVolo(LocalDate dataVolo) {
        this.dataVolo = dataVolo;
    }


    /**
     * Getter di orarioVolo.
     * @return orarioVolo.
     */
    public LocalTime getOrarioVolo() {
        return orarioVolo;
    }

    /**
     * Setter di orarioVolo.
     * @param orarioVolo Orario del Volo.
     */
    public void setOrarioVolo(LocalTime orarioVolo) {
        this.orarioVolo = orarioVolo;
    }


    /**
     * Getter di ritardoVolo.
     * @return ritardoVolo.
     */
    public Duration getRitardoVolo() {
        return ritardoVolo;
    }

    /**
     * Setter di ritardoVolo.
     * @param ritardoVolo Orario del Volo.
     */
    public void setRitardoVolo(Duration ritardoVolo) {
        this.ritardoVolo = ritardoVolo;
    }


    /**
     * Getter di statoDelVolo.
     * @return statoDelVolo.
     */
    public StatoVolo getStatoDelVolo() {
        return statoDelVolo;
    }

    /**
     * Setter di statoDelVolo.
     * @param statoDelVolo Stato del Volo.
     */
    public  void setStatoDelVolo(StatoVolo statoDelVolo) {
        this.statoDelVolo = statoDelVolo;
    }


    /**
     * Getter di Amministratore.
     * @return amministratore.
     */
    public Amministratore getAmministratore() {
        return amministratore;
    }

    /**
     * Setter di Amministratore.
     * @param amministratore Riferimento all'Amministratore che gestisce una lista di Voli.
     */
    public void setAmministratore(Amministratore amministratore) {
        this.amministratore = amministratore;
    }

    /**
     * Getter di tipoVolo
     * @return "Generico"
     */
    public String getTipoVolo() {
        return "Generico";
    }

}