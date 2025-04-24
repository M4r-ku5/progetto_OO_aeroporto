package model;

public class Prenotazione {

    private String nomePasseggero;
    private String numeroBiglietto;
    private String postoAssegnato;
    private StatoPrenotazione statoPrenotazione;


    public Prenotazione (String nomePasseggero, String numeroBiglietto,
                         String postoAssegnato, StatoPrenotazione statoPrenotazione) {

        this.nomePasseggero = nomePasseggero;
        this.numeroBiglietto = numeroBiglietto;
        this.postoAssegnato = postoAssegnato;
        this.statoPrenotazione = statoPrenotazione;

    }


    public String getNomePasseggero () {
        return nomePasseggero;
    }
    public void setNomePasseggero (String nomePasseggero) {
        this.nomePasseggero = nomePasseggero;
    }


    public String getNumeroBiglietto () {
        return numeroBiglietto;
    }
    public void setNumeroBiglietto (String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }


    public String getPostoAssegnato () {
        return postoAssegnato;
    }
    public void setPostoAssegnato (String postoAssegnato) {
        this.postoAssegnato = postoAssegnato;
    }


    public StatoPrenotazione getStatoPrenotazione () {
        return statoPrenotazione;
    }
    public void setStatoPrenotazione(StatoPrenotazione statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }

}
