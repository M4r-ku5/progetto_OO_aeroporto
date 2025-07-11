package controller;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public String HandleLogin (String username, String password) {

        if (username.equals("admin") && password.equals("admin")) {
            return "ADMIN";
        }
        else if (username.equals("user") && password.equals("user")) {
            return "USER";
        }
        else {
            return "FALLITO";
        }
    }


    public List<Volo> getAllVoli () {
        List<Volo> voli = new ArrayList<>();

        voli.add(new VoloInPartenza("AZ123", "Alitalia", "31/05/2025", "10:00", "0", StatoDelVolo.PROGRAMMATO, "Milano"));
        voli.add(new VoloInArrivo("LH456", "Lufthansa", "31/05/2025", "15:00", "120", StatoDelVolo.IN_RITARDO, "Monaco"));
        voli.add(new VoloInPartenza("RY789", "Ryanair", "01/06/2025", "15:00", "0", StatoDelVolo.CANCELATO, "Venezia"));
        voli.add(new VoloInArrivo("BA101", "British Airways", "01/06/2025", "09:15", "0", StatoDelVolo.ATTERRATO, "Londra"));

        return voli;
    }


}
