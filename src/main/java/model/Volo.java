package model;

public class Volo {

    private String codice;
    private String compagniaAerea;
    private String data;
    private String orario;
    private String ritardo;
    private StatoDelVolo statoDelVolo;


    public Volo (String codice, String compagniaAerea, String data,
                 String orario, String ritardo, StatoDelVolo statoDelVolo) {

        this.codice = codice;
        this.compagniaAerea = compagniaAerea;
        this.data = data;
        this.orario = orario;
        this.ritardo = ritardo;
        this.statoDelVolo = statoDelVolo;

    }


    public String getCodice () {
        return codice;
    }
    public void setCodice (String codice) {
        this.codice = codice;
    }


    public String getCompagniaAerea () {
        return compagniaAerea;
    }
    public void setCompagniaAerea (String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }


    public String getData () {
        return data;
    }
    public void setData (String data) {
        this.data = data;
    }


    public String getOrario () {
        return orario;
    }
    public void setOrario (String orario) {
        this.orario = orario;
    }


    public String getRitardo () {
        return ritardo;
    }
    public void setRitardo (String ritardo) {
        this.ritardo = ritardo;
    }


    public StatoDelVolo getStatoDelVolo() {
        return statoDelVolo;
    }
    public void setStatoDelVolo (StatoDelVolo statoDelVolo) {
        this.statoDelVolo = statoDelVolo;
    }

}
