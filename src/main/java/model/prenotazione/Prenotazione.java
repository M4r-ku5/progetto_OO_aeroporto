package model.prenotazione;

import model.utenti.UtenteGenerico;
import model.volo.VoloInPartenza;

// ******************************************************************************************
//  ******************************** CLASSE PRENOTAZIONE ************************************
//   ******************************************************************************************

/**
 * Classe che rappresenta una prenotazione di un volo.
 * Contiene tutte le informazioni relative alla prenotazione di un passeggero.
 */
public class Prenotazione {

    /** Nome del passeggero della prenotazione. */
    private String nomePasseggero;

    /** Numero del biglietto della prenotazione. */
    private String numeroBiglietto;

    /** Posto assegnato al passeggero. */
    private String postoAssegnato;

    /** Stato corrente della prenotazione. */
    private StatoPrenotazione statoDellaPrenotazione;

    /** Riferimento all'utente generico che ha effettuato la prenotazione. */
    private UtenteGenerico utenteGenerico;

    /** Riferimento al volo in partenza prenotato. */
    private VoloInPartenza voloInPartenza;

    /**
     * Costruttore della classe Prenotazione.
     * @param nomePasseggero  Nome del passeggero della Prenotazione.
     * @param numeroBiglietto Numero del biglietto della Prenotazione.
     * @param postoAssegnato  Posto assegnato della Prenotazione.
     */
    public Prenotazione(String nomePasseggero, String numeroBiglietto,
                        String postoAssegnato) {
        this.nomePasseggero = nomePasseggero;
        this.numeroBiglietto = numeroBiglietto;
        this.postoAssegnato = postoAssegnato;
        this.statoDellaPrenotazione = StatoPrenotazione.IN_ATTESA;
    }



// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di nomePasseggero.
     * @return nomePasseggero.
     */
    public String getNomePasseggero() {
        return nomePasseggero;
    }

    /**
     * Setter di nomePasseggero.
     * @param nomePasseggero Nome del passeggero della Prenotazione.
     */
    public void setNomePasseggero(String nomePasseggero) {
        this.nomePasseggero = nomePasseggero;
    }

    /**
     * Getter di numeroBiglietto.
     * @return numeroBiglietto.
     */
    public String getNumeroBiglietto() {
        return numeroBiglietto;
    }

    /**
     * Setter di numeroBiglietto.
     * @param numeroBiglietto Numero del biglietto della Prenotazione.
     */
    public void setNumeroBiglietto(String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    /**
     * Getter di postoAssegnato.
     * @return postoAssegnato.
     */
    public String getPostoAssegnato() {
        return postoAssegnato;
    }

    /**
     * Setter di postoAssegnato.
     * @param postoAssegnato Posto assegnato della Prenotazione.
     */
    public void setPostoAssegnato(String postoAssegnato) {
        this.postoAssegnato = postoAssegnato;
    }

    /**
     * Getter di statoDellaPrenotazione.
     * @return statoDellaPrenotazione.
     */
    public StatoPrenotazione getStatoDellaPrenotazione() {
        return statoDellaPrenotazione;
    }

    /**
     * Setter di statoDellaPrenotazione.
     * @param statoDellaPrenotazione Stato della Prenotazione.
     */
    public void setStatoDellaPrenotazione(StatoPrenotazione statoDellaPrenotazione) {
        this.statoDellaPrenotazione = statoDellaPrenotazione;
    }

    /**
     * Getter di utenteGenerico.
     * @return utenteGenerico.
     */
    public UtenteGenerico getUtenteGenerico() {
        return utenteGenerico;
    }

    /**
     * Setter di utenteGenerico.
     * @param utenteGenerico Riferimento all'Utente Generico che ha effettuato la Prenotazione.
     */
    public void setUtenteGenerico(UtenteGenerico utenteGenerico) {
        this.utenteGenerico = utenteGenerico;
    }

    /**
     * Getter di voloInPartenza.
     * @return voloInPartenza.
     */
    public VoloInPartenza getVoloInPartenza() {
        return voloInPartenza;
    }

    /**
     * Setter di voloInPartenza.
     * @param voloInPartenza Riferimento al Volo in Partenza della Prenotazione.
     */
    public void setVoloInPartenza(VoloInPartenza voloInPartenza) {
        this.voloInPartenza = voloInPartenza;
    }

}