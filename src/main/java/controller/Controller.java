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

        // Restituisco false.
        return false;
    }


    /**
     * Metodo di logout dell'Utente che setta a null l'Utente Loggato.
     */
    public void logout() {
        this.utenteLoggato = null;
    }


    /**
     * Metodo per verificare che l'Utente Loggato sia Amministratore.
     * @return true se l'Utente Loggato è Amministratore, altrimenti false.
     */
    public boolean isAmministratore() {
        return this.utenteLoggato instanceof Amministratore;
    }


    /**
     * Metodo per aggiungere un Volo In Partenza. Il metodo controlla che l'Utente Loggato
     * sia un Amministratore e, successivamente, aggiunge il Volo.
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

            VoloInPartenza nuovoVolo = new VoloInPartenza(codiceVolo, compagniaAerea, dataVolo, orarioVolo,
                                                            Duration.ZERO, aeroportoDestinazione);

            // Aggiunge il Volo alla lista del Sistema.
            this.voli.add(nuovoVolo);

            Amministratore amministratore = (Amministratore) this.utenteLoggato;

            // Aggiunge il Volo alla lista dei voli gestiti dall'Amministratore.
            amministratore.inserisciVolo(nuovoVolo);

            return true;

    }


    /**
     * Metodo per aggiungere del Volo in Arrivo. Il metodo controlla che l'Utente Loggato
     * sia un Amministratore e, successivamente, aggiunge il Volo.
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

        // Aggiunge il Volo alla lista del Sistema.
        this.voli.add(nuovoVolo);

        Amministratore amministratore = (Amministratore) this.utenteLoggato;

        // Aggiunge il Volo alla lista dei voli gestiti dall'Amministratore loggato.
        amministratore.inserisciVolo(nuovoVolo);

        return true;
    }


    /**
     * Metodo per assegnare un Gate a un Volo specifico.
     * Il metodo controlla che l'Utente Loggato sia un Amministratore.
     * Successivamente, controlla che il Volo e il Gate in ingresso esista effettivamente e,
     * se ciò è vero, setta il Gate al Volo.
     * @param codiceVolo Codice del Volo.
     * @param numeroGate Numero del Gate.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean assegnaGate (String codiceVolo, short numeroGate) {

        if (!(isAmministratore())) {
            return false;
        }

        Volo voloTrovato = trovaVolo(codiceVolo);
        Gate gateTrovato = trovaGate(numeroGate);

        if (voloTrovato instanceof VoloInPartenza && gateTrovato != null) {
            ((VoloInPartenza) voloTrovato).setGate(gateTrovato);
            return true;
        }

        return false;
    }


    /**
     * Aggiorna le informazioni di un Volo esistente.
     * @param codiceVolo Il codice del Volo da aggiornare.
     * @param nuovoOrario L'orario aggiornato del Volo.
     * @param nuovoRitardo Il ritardo aggiornato del Volo.
     * @param nuovoStato Lo stato aggiornato del Volo.
     * @return true se l'aggiornamento è andato a buon fine, altrimenti false.
     */
    public boolean aggiornaVolo(String codiceVolo, LocalTime nuovoOrario, Duration nuovoRitardo,
                                StatoVolo nuovoStato) {

        if (!isAmministratore()) {
            return false;
        }

        Volo voloTrovato = trovaVolo(codiceVolo);

        if (voloTrovato != null) {
            voloTrovato.setOrarioVolo(nuovoOrario);
            voloTrovato.setRitardoVolo(nuovoRitardo);
            voloTrovato.setStatoDelVolo(nuovoStato);
            return true;
        }

        return false; // Volo non trovato
    }


    /**
     * Ritorna una copia della lista dei Voli per evitare che quella originale possa essere
     * modificata in qualche modo.
     * @return new ArrayList<>(this.voli).
     */
    public List<Volo> getVoli() {
        return new ArrayList<>(this.voli);
    }


    /**
     * Metodo per far effettuare una Prenotazione a un Utente Generico. Prima controlla se l'Utente
     * Loggato sia un Utente Generico. Successivamente, verifica che il Volo in ingresso effettivamente
     * esista. Infine, verifica che il Volo in ingresso sia un Volo In Partenza e, in caso affermativo,
     * crea la nuova Prenotazione, la aggiunge alla lista di Prenotazioni e la aggiunge alla lista
     * di prenotazioni dell'Utente Loggato.
     * @param codiceVolo Codice del Volo.
     * @param nomePasseggero Nome del passeggero della Prenotazione.
     * @param numeroBiglietto Numero del biglietto della Prenotazione.
     * @param postoAssegnato Posto assegnato della Prenotazione.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean effettuaPrenotazione (String codiceVolo, String nomePasseggero,
                                         String numeroBiglietto, String postoAssegnato) {

        if (!(this.utenteLoggato instanceof UtenteGenerico)) {
            return false;
        }

        Volo voloTrovato = trovaVolo(codiceVolo);

        if (voloTrovato instanceof VoloInPartenza) {
            VoloInPartenza voloInPartenza = (VoloInPartenza)  voloTrovato;
            Prenotazione nuovaPrenotazione = new Prenotazione(nomePasseggero, numeroBiglietto, postoAssegnato);

            // Aggiunge la prenotazione alla lista di prenotazioni del Sistema.
            this.prenotazioni.add(nuovaPrenotazione);

            UtenteGenerico utente = (UtenteGenerico) this.utenteLoggato;

            // Associa la nuova Prenotazione all'Utente.
            utente.effettuaPrenotazione(nuovaPrenotazione);

            // Associa la nuova Prenotazione al relativo Volo.
            voloInPartenza.aggiungiPrenotazione(nuovaPrenotazione);

            return true;
        }
        return false;
    }


    /**
     * Cerca le prenotazioni dell'Utente Loggato in base a un criterio di ricerca, ossia
     * il nome del passeggero o il codice del volo.
     * @param criterio Il testo da cercare (nomePasseggero o codiceVolo).
     * @return risultati.
     */
    public List<Prenotazione> cercaPrenotazioniUtente (String criterio) {
        List<Prenotazione> risultati = new ArrayList<>();

        if (!(this.utenteLoggato instanceof UtenteGenerico)) {
            return risultati;
        }

        UtenteGenerico utente = (UtenteGenerico) this.utenteLoggato;
        List<Prenotazione> prenotazioniUtente = utente.getPrenotazioni();

        for (Prenotazione p : prenotazioniUtente) {

            if (p.getNomePasseggero().equalsIgnoreCase(criterio) ||
                    p.getVoloInPartenza().getCodiceVolo().equalsIgnoreCase(criterio)) {
                risultati.add(p);
            }
        }
        return risultati;
    }


    /**
     * Modifica una prenotazione esistente dell'Utente Loggato. La prenotazione è
     * identificata grazie al numero del biglietto.
     * @param numeroBiglietto Il numero del biglietto della Prenotazione.
     * @param nuovoNomePasseggero Il nuovo nome del passeggero.
     * @param nuovoPostoAssegnato Il nuovo posto assegnato.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean modificaPrenotazione(String numeroBiglietto, String nuovoNomePasseggero, String nuovoPostoAssegnato) {

        if (!(this.utenteLoggato instanceof UtenteGenerico)) {
            return false;
        }

        UtenteGenerico utente = (UtenteGenerico) this.utenteLoggato;
        List<Prenotazione> prenotazioniUtente = utente.getPrenotazioni();

        for (Prenotazione p : prenotazioniUtente) {

            if (p.getNumeroBiglietto().equals(numeroBiglietto)) {

                p.setNomePasseggero(nuovoNomePasseggero);
                p.setPostoAssegnato(nuovoPostoAssegnato);

                // Setto lo stato della Prenotazione in attesa di approvazione delle modifiche.
                p.setStatoDellaPrenotazione(StatoPrenotazione.IN_ATTESA);

                return true;
            }
        }

        return false;
    }


    /**
     * Metodo per cercare un passeggero tramite il suo nomePasseggero.
     * @param nomePasseggero Nome del passeggero da cercare.
     * @return risultati.
     */
    public List<Prenotazione> cercaPasseggero(String nomePasseggero) {

        List<Prenotazione> risultati = new ArrayList<>();

        // Itera su tutti i voli.
        for (Volo iVolo : this.voli) {

            if (iVolo instanceof  VoloInPartenza) {
                VoloInPartenza vp = (VoloInPartenza) iVolo;

                // Itera su tutte le prenotazioni del Volo attuale.
                for (Prenotazione p : vp.getPrenotazioni()) {

                    if (p.getNomePasseggero().equalsIgnoreCase(nomePasseggero)) {
                        risultati.add(p);
                    }

                }
            }

        }

        return risultati;
    }




    // ************************************************************************************
    //  ********************************* METODI AGGIUNTIVI *********************************
    //   *************************************************************************************

    /**
     * Cerca un Volo nella lista dei voli del Sistema tramite il codiceVolo.
     * @param codiceVolo Il codice del Volo.
     * @return Il Volo trovato, altrimenti null.
     */
    public Volo trovaVolo(String codiceVolo) {

        for (Volo v : this.voli) {

            if (v.getCodiceVolo().equals(codiceVolo)) {
                return v;
            }

        }

        return null;

    }


    /**
     * Cerca un Gate nella lista dei Gate del Gistema tramite il numeroGate.
     * @param numeroGate Il numero del Gate.
     * @return Il Gate trovato, altrimenti null.
     */
    public Gate trovaGate(short numeroGate) {

        for (Gate g : this.gates) {

            if (g.getNumeroGate() == numeroGate) {
                return g;
            }

        }

        return null;

    }


}