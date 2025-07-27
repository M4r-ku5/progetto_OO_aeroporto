package controller;

import dao.postgres.VoloDAO;
import dao.postgres.PrenotazioneDAO;
import dao.postgres.PrenotazioneImplDAO;
import dao.postgres.VoloImplDAO;
import model.utenti.UtenteGenerico;
import model.volo.Volo;
import model.volo.VoloInPartenza;
import model.prenotazione.Prenotazione;
import java.util.List;
import java.util.ArrayList;



// ******************************************************************************************
//  ******************************* CLASSE UTENTE CONTROLLER ********************************
//   ******************************************************************************************

/**
 * Controller specializzato per la gestione delle operazioni degli utenti generici.
 * Gestisce le prenotazioni e le relative operazioni CRUD per gli utenti registrati.
 * Implementa il pattern DAO per l'accesso ai dati e si integra con l'AuthController
 * per la gestione dell'autenticazione.
 */
public class UtenteController {
    /** DAO per la gestione dei voli. */
    private VoloDAO voloDAO;

    /** DAO per la gestione delle prenotazioni. */
    private PrenotazioneDAO prenotazioneDAO;

    /** Controller per l'autenticazione degli utenti. */
    private AuthController authController;

    /**
     * Costruttore della classe UtenteController.
     * @param authController Il controller di autenticazione per verificare l'utente loggato.
     */
    public UtenteController(AuthController authController) {
        this.voloDAO = new VoloImplDAO();
        this.prenotazioneDAO = new PrenotazioneImplDAO();
        this.authController = authController;
    }

    /**
     * Metodo per far effettuare una nuova prenotazione a un utente generico.
     * @param codiceVolo Il codice del volo da prenotare.
     * @param nomePasseggero Il nome del passeggero della prenotazione.
     * @param numeroBiglietto Il numero del biglietto della prenotazione.
     * @param postoAssegnato Il posto assegnato della prenotazione.
     * @return true se l'operazione va a buon fine, false altrimenti.
     * @throws RuntimeException se il volo specificato non esiste.
     */
    public boolean effettuaPrenotazione(String codiceVolo, String nomePasseggero,
                                       String numeroBiglietto, String postoAssegnato) {

        Volo voloTrovato = voloDAO.findByCodice(codiceVolo);
        if (voloTrovato == null) {
            throw new RuntimeException("VOLO_NON_ESISTENTE: Il volo con codice '" + codiceVolo + "' non esiste.");
        }

        Prenotazione nuovaPrenotazione = new Prenotazione(nomePasseggero, numeroBiglietto, postoAssegnato);

        if (voloTrovato instanceof VoloInPartenza) {
            nuovaPrenotazione.setVoloInPartenza((VoloInPartenza) voloTrovato);
        }

        return prenotazioneDAO.save(nuovaPrenotazione, codiceVolo);
    }

    /**
     * Metodo per cercare una prenotazione usando un criterio.
     * @param criterio Il criterio di ricerca da usare.
     * @return Lista delle prenotazioni risultanti.
     */
    public List<Prenotazione> cercaPrenotazioniUtente(String criterio) {
        UtenteGenerico utente = (UtenteGenerico) authController.getUtenteLoggato();

        if (utente == null) {
            return new ArrayList<>();
        }

        return prenotazioneDAO.findByNomePasseggero(criterio);
    }

    /**
     * Metodo per modificare una prenotazione dell'utente loggato.
     * @param numeroBiglietto Il numero del biglietto della prenotazione da modificare.
     * @param nuovoNomePasseggero Il nuovo nome del passeggero.
     * @param nuovoPostoAssegnato Il nuovo posto assegnato.
     * @return true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean modificaPrenotazione(String numeroBiglietto, String nuovoNomePasseggero, String nuovoPostoAssegnato) {
        UtenteGenerico utente = (UtenteGenerico) authController.getUtenteLoggato();

        if (utente == null) {
            return false;
        }

        // Verifica che la prenotazione esista prima di modificarla
        Prenotazione prenotazione = prenotazioneDAO.findByNumeroBiglietto(numeroBiglietto);
        if (prenotazione != null) {
            return prenotazioneDAO.update(numeroBiglietto, nuovoNomePasseggero, nuovoPostoAssegnato);
        }

        return false;
    }
}
