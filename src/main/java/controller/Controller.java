package controller;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    /**
     * Verifica le credenziali dell'utente e restituisce il suo ruolo.
     * @param username L'username inserito dall'utente.
     * @param password La password inserita dall'utente.
     * @return Una stringa che indica il ruolo ("admin", "user") se le credenziali sono valide,
     *         null altrimenti.
     */

    private List<Object[]> flightsData = new ArrayList<>();

    public Controller() {
        flightsData.add(new Object[]{"AZ101", "ITA Airways", "FCO", "NAP", "2025-05-28", "10:00"});
        flightsData.add(new Object[]{"LH202", "Lufthansa", "MUC", "NAP", "2025-05-28", "11:30"});
    }


    public String authenticate(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Login successful for admin " + username);
            return "admin";
        }
        else if (username.equals("user") && password.equals("user")) {
            System.out.println("Login successful for user " + username);
            return "user";
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }


    public Object[][] getAllFlights() {
        System.out.println("Controller: Richiesta recupero di tutti i voli.");
        return flightsData.toArray(new Object[0][0]);
    }


    public boolean addFlight(String codiceVolo, String compagniaAerea, String aeroportoO,
                             String aeroportoD, String dataVolo, String orarioPrevisto) {

        System.out.println("Controller: Richiesta aggiunta volo ricevuta per " + codiceVolo);

        flightsData.add(new Object[] {  codiceVolo, compagniaAerea, aeroportoO,
                                        aeroportoD, dataVolo, orarioPrevisto});

        return true;
    }


    public boolean updateFlight(String codiceVoloDaAggiornare, String compagniaAerea, String aeroportoPartenza, String aeroportoDestinazione,
                                String dataVolo, String orarioPrevisto) {
        System.out.println("Controller: Richiesta aggiornamento volo ricevuta per " + codiceVoloDaAggiornare);

        for (int i = 0; i < flightsData.size(); i++) {
            if (flightsData.get(i)[0].equals(codiceVoloDaAggiornare)) {
                flightsData.set(i, new Object[]{codiceVoloDaAggiornare, compagniaAerea, aeroportoPartenza, aeroportoDestinazione, dataVolo, orarioPrevisto});
                return true;
            }
        }

        return false;
    }
}