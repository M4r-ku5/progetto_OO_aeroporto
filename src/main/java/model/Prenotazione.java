package model;


// ***************************************************************************************
//  ********************************* CLASSE PRENOTAZIONE *********************************
//   ***************************************************************************************

public class Prenotazione {

    /** Nome del passeggero della Prenotazione. */
    private String nomePasseggero;

    /** Numero del biglietto della Prenotazione. */
    private String numeroBiglietto;

    /** Posto assegnato della Prenotazione. */
    private String postoAssegnato;

    /** Stato della Prenotazione (Confermata, In attesa, Cancellata). */
    private StatoPrenotazione statoDellaPrenotazione;

    /** Riferimento al singolo Utente che effettua le prenotazioni. */
    private UtenteGenerico utenteGenerico;

    /** Riferimento al Volo In Partenza specifico della Prenotazione. */
    private VoloInPartenza voloInPartenza;




    /**
     * Costruttore della classe Prenotazione.
     * @param nomePasseggero Nome del passeggero della Prenotazione.
     * @param numeroBiglietto Numero del biglietto della Prenotazione.
     * @param postoAssegnato Posto assegnato della Prenotazione.
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
    public String getNomePasseggero () {
        return nomePasseggero;
    }

    /**
     * Setter di nomePasseggero
     * @param nomePasseggero Nome del passeggero della Prenotazione.
     */
    public void setNomePasseggero (String nomePasseggero) {
        this.nomePasseggero = nomePasseggero;
    }


    /**
     * Getter di numeroBiglietto.
     * @return numeroBiglietto.
     */
    public String getNumeroBiglietto () {
        return numeroBiglietto;
    }

    /**
     * Setter di numeroBiglietto
     * @param numeroBiglietto Numero del biglietto della Prenotazione.
     */
    public void setNumeroBiglietto (String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }


    /**
     * Getter di postoAssegnato.
     * @return postoAssegnato.
     */
    public String getPostoAssegnato () {
        return postoAssegnato;
    }

    /**
     * Setter di postoAssegnato
     * @param postoAssegnato Posto assegnato della Prenotazione.
     */
    public void setPostoAssegnato (String postoAssegnato) {
        this.postoAssegnato = postoAssegnato;
    }


    /**
     * Getter di statoDellaPrenotazione.
     * @return statoDellaPrenotazione.
     */
    public StatoPrenotazione getStatoDellaPrenotazione () {
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
     * Getter di UtenteGenerico.
     * @return utenteGenerico.
     */
    public UtenteGenerico getUtenteGenerico () {
        return utenteGenerico;
    }

    /**
     * Setter di UtenteGenerico.
     * @param utenteGenerico UtenteGenerico che effettua la Prenotazione.
     */
    public void setUtenteGenerico (UtenteGenerico utenteGenerico) {
        this.utenteGenerico = utenteGenerico;
    }


    /**
     * Getter di volo.
     * @return il volo associato alla prenotazione.
     */
    public VoloInPartenza getVoloInPartenza () {
        return voloInPartenza;
    }

    /**
     * Setter di volo.
     * @param voloInPartenza Il VoloInPartenza da associare.
     */
    public void setVoloInPartenza(VoloInPartenza voloInPartenza) {
        this.voloInPartenza = voloInPartenza;
    }

}