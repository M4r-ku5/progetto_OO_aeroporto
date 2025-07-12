package controller;

import model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;


// ************************************************************************************
//  ****************************** CLASSE CONTROLLER ***********************************
//   ************************************************************************************

public class Controller {

    /** Lista di utenti. */
    private List<Utente> utenti;

    /** Lista di voli. */
    private List<Volo> voli;

    /** Lista di gates. */
    private List<Gate> gates;

    /** Lista di prenotazioni. */
    private List<Prenotazione> prenotazioni;

    /** Utente Loggato. */
    private Utente utenteLoggato;




    /**
     * Costruttore della classe Controller.
     */
    public Controller() {
        this.utenti = new ArrayList<>();
        this.voli = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();

        this.utenti.add(new Amministratore("admin", "admin"));
        this.utenti.add(new UtenteGenerico("user", "user"));

        this.gates.add(new Gate((short)1));
        this.gates.add(new Gate((short)2));
        this.gates.add(new Gate((short)3));
        this.gates.add(new Gate((short)4));
    }




    // ************************************************************************************
    //  ************************************** METODI **************************************
    //   ************************************************************************************

    /**
     * Metodo che gestisce il login dell'Utente.
     * Il metodo scorre la lista di utenti e se trova una corrispondenza di login e password,
     * effettua il login.
     * @param login Login dell'Utente.
     * @param password Password dell'Utente.
     * @return true se il login va a buon fine, altrimenti false.
     */
    public boolean login(String login, String password) {
        for (Utente u : this.utenti) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                this.utenteLoggato = u;
                return true;
            }
        }
        return false;
    }


    /**
     * Metodo di logout dell'Utente.
     * Il metodo setta a null l'Utente Loggato.
     */
    public void logout() {
        this.utenteLoggato = null;
    }


    /**
     * Metodo per verificare che l'Utente Loggato sia Amministratore.
     * Il metodo verifica che utenteLoggato sia un'istanza di Amministratore.
     * @return true se l'Utente Loggato è Amministratore, altrimenti false.
     */
    public boolean isAmministratore() {
        return this.utenteLoggato instanceof Amministratore;
    }


    /**
     * Metodo per aggiungere un Volo In Partenza.
     * Il metodo controlla che l'Utente Loggato sia un Amministratore.
     * Successivamente, aggiunge il volo.
     * @param codiceVolo Codice del Volo.
     * @param compagniaAerea Compagnia Aerea del Volo.
     * @param dataVolo Data del Volo.
     * @param orarioVolo Orario del Volo.
     * @param aeroportoDestinazione Aeroporto di Destinazione del Volo in Partenza.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean aggiungiVoloInPartenza(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                                          LocalTime orarioVolo, String aeroportoDestinazione) {

        if (!(isAmministratore())) {
            return false;
        }

        else {
            VoloInPartenza nuovoVolo = new VoloInPartenza(codiceVolo, compagniaAerea, dataVolo, orarioVolo,
                                                            Duration.ZERO, aeroportoDestinazione);

            this.voli.add(nuovoVolo);
            return true;
        }

    }


    /**
     * Metodo per aggiungere del Volo in Arrivo.
     * @param codiceVolo Codice del Volo.
     * @param compagniaAerea Compagnia Aerea del Volo.
     * @param dataVolo Data del Volo.
     * @param orarioVolo Orario del Volo.
     * @param aeroportoOrigine Aeroporto di Origine del Volo.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean aggiungiVoloInArrivo(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                                        LocalTime orarioVolo, String aeroportoOrigine) {

        if (!(isAmministratore())) {
            return false;
        }

        VoloInArrivo nuovoVolo = new VoloInArrivo(codiceVolo, compagniaAerea, dataVolo, orarioVolo,
                Duration.ZERO, aeroportoOrigine);

        this.voli.add(nuovoVolo);
        return true;
    }


    /**
     * Metodo per assegnare un Gate a un Volo specifico.
     * Il metodo controlla che l'Utente Loggato sia un Amministratore.
     * Successivamente, controlla che il Volo e il Gate in ingresso esista effettivamente.
     * Infine, se ciò è vero, setta il Gate al Volo.
     * @param codiceVolo Codice del Volo.
     * @param numeroGate Numero del Gate.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean assegnaGate (String codiceVolo, short numeroGate) {

        if (!(isAmministratore())) {
            return false;
        }

        Volo voloTrovato = null;
        for (Volo v : this.voli) {
            if (v.getCodiceVolo().equals(codiceVolo)) {
                voloTrovato = v;
                break;
            }
        }

        Gate gateTrovato = null;
        for (Gate g : this.gates) {
            if (g.getNumeroGate() == numeroGate) {
                gateTrovato = g;
                break;
            }
        }

        if (voloTrovato instanceof VoloInPartenza && gateTrovato != null) {
            ((VoloInPartenza) voloTrovato).setGate(gateTrovato);
            return true;
        }

        return false;
    }

}