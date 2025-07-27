package dao.postgres;

import model.prenotazione.Prenotazione;
import java.util.List;

/**
 * Interfaccia DAO per la gestione delle Prenotazioni nel database.
 */
public interface PrenotazioneDAO {

    /**
     * Trova tutte le prenotazioni nel database.
     * @return Lista di tutte le prenotazioni.
     */
    List<Prenotazione> findAll();

    /**
     * Trova una prenotazione tramite numero biglietto.
     * @param numeroBiglietto Numero del biglietto.
     * @return La prenotazione trovata, null se non esiste.
     */
    Prenotazione findByNumeroBiglietto(String numeroBiglietto);

    /**
     * Trova prenotazioni tramite nome passeggero.
     * @param nomePasseggero Nome del passeggero.
     * @return Lista delle prenotazioni trovate.
     */
    List<Prenotazione> findByNomePasseggero(String nomePasseggero);

    /**
     * Trova prenotazioni tramite codice volo.
     * @param codiceVolo Codice del volo.
     * @return Lista delle prenotazioni trovate.
     */
    List<Prenotazione> findByCodiceVolo(String codiceVolo);

    /**
     * Salva una nuova prenotazione nel database.
     * @param prenotazione Prenotazione da salvare.
     * @return true se salvata con successo, false altrimenti.
     */
    boolean save(Prenotazione prenotazione);

    /**
     * Salva una nuova prenotazione nel database con codice volo specificato.
     * @param prenotazione Prenotazione da salvare.
     * @param codiceVolo Codice del volo associato.
     * @return true se salvata con successo, false altrimenti.
     */
    boolean save(Prenotazione prenotazione, String codiceVolo);

    /**
     * Aggiorna una prenotazione esistente nel database.
     * @param numeroBiglietto Numero del biglietto della prenotazione da aggiornare.
     * @param nuovoNomePasseggero Nuovo nome del passeggero.
     * @param nuovoPostoAssegnato Nuovo posto assegnato.
     * @return true se aggiornata con successo, false altrimenti.
     */
    boolean update(String numeroBiglietto, String nuovoNomePasseggero, String nuovoPostoAssegnato);

    /**
     * Elimina una prenotazione dal database.
     * @param numeroBiglietto Numero del biglietto della prenotazione da eliminare.
     * @return true se eliminata con successo, false altrimenti.
     */
    boolean delete(String numeroBiglietto);
}
