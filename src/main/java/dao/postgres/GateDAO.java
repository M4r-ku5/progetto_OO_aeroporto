package dao.postgres;

import model.volo.Gate;
import java.util.List;

/**
 * Interfaccia DAO per la gestione dei Gate nel database.
 */
public interface GateDAO {

    /**
     * Trova tutti i gate nel database.
     * @return Lista di tutti i gate.
     */
    List<Gate> findAll();

    /**
     * Trova un gate tramite numero.
     * @param numeroGate Numero del gate.
     * @return Il gate trovato, null se non esiste.
     */
    Gate findByNumero(short numeroGate);

    /**
     * Salva un nuovo gate nel database.
     * @param gate Gate da salvare.
     * @return true se salvato con successo, false altrimenti.
     */
    boolean save(Gate gate);

    /**
     * Elimina un gate dal database.
     *
     * @param numeroGate Numero del gate da eliminare.
     * @return true se eliminato con successo, false altrimenti.
     */
    boolean delete(short numeroGate);
}
