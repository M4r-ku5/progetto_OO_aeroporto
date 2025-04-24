package model;

public class VoloInPartenza {

    private String aeroportoDestinazione;

    public VoloInPartenza (String aeroportoDestinazione) {
        this.aeroportoDestinazione = aeroportoDestinazione;
    }


    public String getAeroportoDestinazione() {
        return aeroportoDestinazione;
    }

    public void setAeroportoDestinazione(String aeroportoDestinazione) {
        this.aeroportoDestinazione = aeroportoDestinazione;
    }

}
