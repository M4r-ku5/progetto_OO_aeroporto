package controller;

import model.prenotazione.Prenotazione;
import model.volo.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



// ******************************************************************************************
//  ********************************* CLASSE CONTROLLER **************************************
//   ******************************************************************************************

/**
 * Controller principale che coordina i controller specializzati.
 */
public class Controller {

    // Controller specializzati
    private AuthController authController;
    private AdminController adminController;
    private UtenteController utenteController;
    private VoloController voloController;

    /**
     * Costruttore della classe Controller.
     */
    public Controller() {
        // Inizializzazione dei controller specializzati
        this.authController = new AuthController();
        this.adminController = new AdminController(authController);
        this.utenteController = new UtenteController(authController);
        this.voloController = new VoloController();
    }



    // ************************************************************************************
    //  *************************** METODI DI DELEGAZIONE **********************************
    //   ************************************************************************************


    // Metodi per AuthController
    /**
     * Metodo per il login.
     * @param login Login dell'utente
     * @param password Password dell'utente
     * @return true se il login va a buon fine, false altrimenti.
     */
    public boolean login(String login, String password) {
        return authController.login(login, password);
    }

    /**
     * Metodo per il logout dell'utente, delega alla funzione omonima del controller specifico.
     */
    public void logout() {
        authController.logout();
    }

    /**
     * Metodo per verificare che l'utente loggato sia un amministratore, delega alla funzione
     * omonima del controller specifico.
     * @return the boolean
     */
    public boolean isAmministratore() {
        return authController.isAmministratore();
    }


    // Metodi per AdminController
    /**
     * Metodo per aggiungere un volo in partenza, delega alla funzione omonima del controller
     * specifico.
     * @param codiceVolo Codice del volo in partenza da aggiungere.
     * @param compagniaAerea Compagnia aerea del volo in partenza da aggiungere.
     * @param dataVolo Data del volo in partenza da aggiungere.
     * @param orarioVolo Orario del volo in partenza da aggiungere.
     * @param aeroportoDestinazione Aeroporto di destinazione del volo in partenza da aggiungere.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean aggiungiVoloInPartenza(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                                          LocalTime orarioVolo, String aeroportoDestinazione) {
        return adminController.aggiungiVoloInPartenza(codiceVolo, compagniaAerea, dataVolo, orarioVolo, aeroportoDestinazione);
    }

    /**
     * Metodo per aggiungere un volo in arrivo, delega alla funzione omonima del controller
     * specifico.
     * @param codiceVolo Codice del volo in arrivo da aggiungere.
     * @param compagniaAerea Compagnia aerea del volo in arrivo da aggiungere.
     * @param dataVolo Data del volo in arrivo da aggiungere.
     * @param orarioVolo Orario del volo in arrivo da aggiungere.
     * @param aeroportoOrigine Aeroporto di origine del volo in arrivo da aggiungere.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean aggiungiVoloInArrivo(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                                        LocalTime orarioVolo, String aeroportoOrigine) {
        return adminController.aggiungiVoloInArrivo(codiceVolo, compagniaAerea, dataVolo, orarioVolo, aeroportoOrigine);
    }

    /**
     * Metodo per assegnare un gate a un volo, delega alla funzione omonima del controller
     * specifico.
     * @param codiceVolo Codice del volo a cui assegnare il gate
     * @param numeroGate Numero del gate da assegnare
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean assegnaGate(String codiceVolo, short numeroGate) {
        return adminController.assegnaGate(codiceVolo, numeroGate);
    }

    /**
     * Metodo per aggiornare un volo, delega alla funzione omonima del controller specifico.
     * @param codiceVolo Codice del volo da aggiornare.
     * @param nuovoOrario  Il nuovo orario da impostare.
     * @param nuovoRitardo Il nuovo ritardo da impostare.
     * @param nuovoStato   Il nuovo stato del volo da impostare.
     * @return true se l'operazione va a buon fine, altrimenti false.
     */
    public boolean aggiornaVolo(String codiceVolo, LocalTime nuovoOrario, Duration nuovoRitardo,
                                StatoVolo nuovoStato) {
        return adminController.aggiornaVolo(codiceVolo, nuovoOrario, nuovoRitardo, nuovoStato);
    }

    /**
     * Metodo per cercare un passeggero, delega alla funzione omonima del controller specifico.
     * @param nomePasseggero Nome del passeggero da cercare.
     * @return Lista dei passeggeri trovati.
     */
    public List<Prenotazione> cercaPasseggero(String nomePasseggero) {
        return adminController.cercaPasseggero(nomePasseggero);
    }


    // Metodi per UtenteController
    /**
     * Effettua prenotazione boolean, delega alla funzione omonima del controller specifico.
     * @param codiceVolo Codice del volo da prenotare.
     * @param nomePasseggero Nome del passeggero della prenotazione.
     * @param numeroBiglietto Numero del biglietto della prenotazione.
     * @param postoAssegnato Posto assegnato della prenotazione.
     * @return true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean effettuaPrenotazione(String codiceVolo, String nomePasseggero,
                                       String numeroBiglietto, String postoAssegnato) {
        return utenteController.effettuaPrenotazione(codiceVolo, nomePasseggero, numeroBiglietto, postoAssegnato);
    }

    /**
     * Cerca prenotazioni utente list, delega alla funzione omonima del controller specifico.
     * @param criterio Il criterio da usare per la ricerca.
     * @return Lista delle prenotazioni trovate.
     */
    public List<Prenotazione> cercaPrenotazioniUtente(String criterio) {
        return utenteController.cercaPrenotazioniUtente(criterio);
    }

    /**
     * Metodo per modificare una prenotazione.
     * @param numeroBiglietto Numero del biglietto della prenotazione da modificare.
     * @param nuovoNomePasseggero Il nuovo nome del passeggero.
     * @param nuovoPostoAssegnato Il nuovo posto assegnato.
     * @return true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean modificaPrenotazione(String numeroBiglietto, String nuovoNomePasseggero, String nuovoPostoAssegnato) {
        return utenteController.modificaPrenotazione(numeroBiglietto, nuovoNomePasseggero, nuovoPostoAssegnato);
    }


    // Metodi per VoloController
    /**
     * Getter dei voli.
     * @return the voli
     */
    public List<Volo> getVoli() {
        return voloController.getVoli();
    }

    /**
     * Metodo per trovare un volo tramite il codice, delega alla funzione omonima del controller specifico.
     * @param codiceVolo Codice del volo da trovare.
     * @return Il volo trovato.
     */
    public Volo trovaVolo(String codiceVolo) {
        return voloController.trovaVolo(codiceVolo);
    }

    /**
     * Gate da trovare.
     * @param numeroGate Numero del gate da trovare.
     * @return Il gate trovato.
     */
    public Gate trovaGate(short numeroGate) {
        return voloController.trovaGate(numeroGate);
    }

    /**
     * Metodo per ottenere i dati dei voli formattati per la visualizzazione in tabella.
     * @return Array bidimensionale contenente i dati formattati per la tabella.
     */
    public Object[][] getVoliDataForTable() {
        List<Volo> voli = voloController.getVoli();
        Object[][] data = new Object[voli.size()][6];

        for (int i = 0; i < voli.size(); i++) {
            Volo volo = voli.get(i);
            data[i][0] = volo.getCodiceVolo();
            data[i][1] = volo.getCompagniaAerea();
            data[i][2] = volo.getDataVolo();
            data[i][3] = volo.getOrarioVolo();
            data[i][4] = volo.getStatoDelVolo();
            data[i][5] = volo.getTipoVolo();
        }

        return data;
    }

    /**
     * Metodo per ottenere i nomi delle colonne per la tabella dei voli.
     * @return Array di stringhe con i nomi delle colonne.
     */
    public String[] getVoliColumnNames() {
        return new String[]{"Codice Volo", "Compagnia", "Data", "Orario", "Stato", "Tipo"};
    }
}