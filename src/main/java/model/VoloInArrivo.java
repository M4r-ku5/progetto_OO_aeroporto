package model;

public class VoloInArrivo extends Volo {

    private String aeroportoOrigine;


    public VoloInArrivo (String codice, String compagniaAerea, String data, String orario,
                        String ritardo, StatoDelVolo statoDelVolo, String aeroportoOrigine) {

        super(codice, compagniaAerea, data, orario, ritardo, statoDelVolo);
        this.aeroportoOrigine = aeroportoOrigine;

    }


     public String getAeroportoOrigine () {
        return aeroportoOrigine;
     }

     public void setAeroportoOrigine (String aeroportoOrigine) {
        this.aeroportoOrigine = aeroportoOrigine;
     }

}
