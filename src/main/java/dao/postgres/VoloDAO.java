package dao.postgres;

import model.volo.Volo;
import model.volo.StatoVolo;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Interfaccia DAO per la gestione dei Voli nel database.
 */
public interface VoloDAO {

    /**
     * Trova tutti i voli nel database.
     *
     * @return Lista di tutti i voli.
     */
    List<Volo> findAll();

    /**
     * Trova un volo tramite codice.
     *
     * @param codiceVolo Codice del volo.
     * @return Il volo trovato, null se non esiste.
     */
    Volo findByCodice(String codiceVolo);

    /**
     * Salva un nuovo volo nel database.
     *
     * @param volo Volo da salvare.
     * @return true se salvato con successo, false altrimenti.
     */
    boolean save(Volo volo);

    /**
     * Aggiorna un volo esistente nel database.
     * @param codiceVolo Codice del volo da aggiornare.
     * @param nuovoOrario Nuovo orario del volo.
     * @param nuovoRitardo Nuovo ritardo del volo.
     * @param nuovoStato Nuovo stato del volo.
     * @return true se aggiornato con successo, false altrimenti.
     */
    boolean update(String codiceVolo, LocalTime nuovoOrario, Duration nuovoRitardo, StatoVolo nuovoStato);

    /**
     * Aggiorna il gate di un volo nel database.
     * @param codiceVolo Codice del volo da aggiornare.
     * @param numeroGate Numero del gate da assegnare.
     * @return true se aggiornato con successo, false altrimenti.
     */
    boolean updateGate(String codiceVolo, short numeroGate);

    /**
     * Elimina un volo dal database.
     * @param codiceVolo Codice del volo da eliminare.
     * @return true se eliminato con successo, false altrimenti.
     */
    boolean delete(String codiceVolo);
}
