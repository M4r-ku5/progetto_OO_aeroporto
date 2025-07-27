package controller;

import dao.postgres.VoloDAO;
import dao.postgres.GateDAO;
import dao.postgres.PrenotazioneDAO;
import dao.postgres.GateImplDAO;
import dao.postgres.PrenotazioneImplDAO;
import dao.postgres.VoloImplDAO;
import model.volo.*;
import model.prenotazione.Prenotazione;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



// ******************************************************************************************
//  ******************************* CLASSE ADMIN CONTROLLER **********************************
//   ******************************************************************************************

/**
 * Controller per la gestione delle operazioni riservate all'amministratore.
 */
public class AdminController {
    /** DAO per la gestione dei voli. */
    private final VoloDAO voloDAO;
    /** DAO per la gestione dei gate. */
    private final GateDAO gateDAO;
    /** DAO per la gestione delle prenotazioni. */
    private final PrenotazioneDAO prenotazioneDAO;
    /** Controller per l'autenticazione. */
    private final AuthController authController;

    /**
     * Costruttore di AdminController.
     * @param authController Controller per l'autenticazione
     */
    public AdminController(AuthController authController) {
        this.voloDAO = new VoloImplDAO();
        this.gateDAO = new GateImplDAO();
        this.prenotazioneDAO = new PrenotazioneImplDAO();
        this.authController = authController;
    }

    /**
     * Metodo per aggiungere un Volo In Partenza.
     * @param codiceVolo Codice del volo
     * @param compagniaAerea Compagnia aerea
     * @param dataVolo Data del volo
     * @param orarioVolo Orario del volo
     * @param aeroportoDestinazione Aeroporto di destinazione
     * @return true se inserito con successo, false altrimenti
     */
    public boolean aggiungiVoloInPartenza(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                                          LocalTime orarioVolo, String aeroportoDestinazione) {
        // Vincolo: codice volo unico
        if (voloDAO.findByCodice(codiceVolo) != null) {
            throw new IllegalArgumentException("VOLO_ESISTENTE");
        }
        // Vincolo: aeroporto destinazione diverso da "Aeroporto di Napoli", che è l'aeroporto
        // di origine
        if ("Aeroporto di Napoli".equalsIgnoreCase(aeroportoDestinazione)) {
            throw new IllegalArgumentException("AEROPORTI_UGUALI");
        }
        VoloInPartenza nuovoVolo = new VoloInPartenza(codiceVolo, compagniaAerea, dataVolo, orarioVolo,
                                                        Duration.ZERO, aeroportoDestinazione);
        return voloDAO.save(nuovoVolo);
    }

    /**
     * Metodo per aggiungere un Volo in Arrivo.
     * @param codiceVolo Codice del volo
     * @param compagniaAerea Compagnia aerea
     * @param dataVolo Data del volo
     * @param orarioVolo Orario del volo
     * @param aeroportoOrigine Aeroporto di origine
     * @return true se inserito con successo, false altrimenti
     */
    public boolean aggiungiVoloInArrivo(String codiceVolo, String compagniaAerea, LocalDate dataVolo,
                                        LocalTime orarioVolo, String aeroportoOrigine) {
        // Vincolo: codice volo unico
        if (voloDAO.findByCodice(codiceVolo) != null) {
            throw new IllegalArgumentException("VOLO_ESISTENTE");
        }
        // Vincolo: aeroporto origine/destinazione diversi rispetto a "Aeroporto di Napoli"
        if ("Aeroporto di Napoli".equalsIgnoreCase(aeroportoOrigine)) {
            throw new IllegalArgumentException("AEROPORTI_UGUALI");
        }
        VoloInArrivo nuovoVolo = new VoloInArrivo(codiceVolo, compagniaAerea, dataVolo, orarioVolo,
                Duration.ZERO, aeroportoOrigine);
        return voloDAO.save(nuovoVolo);
    }

    /**
     * Assegna un Gate a un Volo in partenza.
     * @param codiceVolo Codice del volo
     * @param numeroGate Numero del gate
     * @return true se assegnato con successo, false altrimenti
     */
    public boolean assegnaGate(String codiceVolo, short numeroGate) {

        Volo voloTrovato = voloDAO.findByCodice(codiceVolo);
        Gate gateTrovato = gateDAO.findByNumero(numeroGate);

        if (voloTrovato instanceof VoloInPartenza && gateTrovato != null) {
            // Aggiorna il gate
            ((VoloInPartenza) voloTrovato).setGate(gateTrovato);

            // Aggiorna il gate nel database
            boolean success = voloDAO.updateGate(codiceVolo, numeroGate);

            if (success) {
                System.out.println("Gate " + numeroGate + " assegnato al volo " + codiceVolo + " con successo!");
            } else {
                System.err.println("Errore durante l'aggiornamento del gate nel database per il volo " + codiceVolo);
            }

            return success;
        }

        System.err.println("Impossibile assegnare il gate: volo non trovato o non è un volo in partenza, oppure gate non esistente");
        return false;
    }

    /**
     * Aggiorna le informazioni di un Volo esistente.
     * @param codiceVolo Codice del volo
     * @param nuovoOrario Nuovo orario
     * @param nuovoRitardo Nuovo ritardo
     * @param nuovoStato Nuovo stato
     * @return true se aggiornato con successo, false altrimenti
     */
    public boolean aggiornaVolo(String codiceVolo, LocalTime nuovoOrario, Duration nuovoRitardo,
                                StatoVolo nuovoStato) {

        return voloDAO.update(codiceVolo, nuovoOrario, nuovoRitardo, nuovoStato);
    }

    /**
     * Metodo per cercare un passeggero tramite il suo nome.
     * @param nomePasseggero Nome del passeggero
     * @return Lista delle prenotazioni trovate
     */
    public List<Prenotazione> cercaPasseggero(String nomePasseggero) {
        return prenotazioneDAO.findByNomePasseggero(nomePasseggero);
    }
}
