package controller;

import dao.postgres.GateDAO;
import dao.postgres.VoloDAO;
import dao.postgres.VoloImplDAO;
import dao.postgres.GateImplDAO;
import model.volo.Volo;
import model.volo.Gate;
import java.util.List;

/**
 * Controller per le funzioni di servizio generali relative a voli e gate.
 */
public class VoloController {

    private VoloDAO voloDAO;
    private GateDAO gateDAO;

    /**
     * Costruttore di VoloController.
     */
    public VoloController() {
        this.voloDAO = new VoloImplDAO();
        this.gateDAO = new GateImplDAO();
    }

    /**
     * Metodo per ottenere una lista di tutti i voli.
     * @return Lista di tutti i voli.
     */
    public List<Volo> getVoli() {
        return voloDAO.findAll();
    }

    /**
     * Metodo per trovare un volo tramite il suo codice associato, delega alla funzione omonima
     * del controller specifico.
     * @param codiceVolo Codice del volo da trovare.
     * @return Il volo trovato.
     */
    public Volo trovaVolo(String codiceVolo) {
        return voloDAO.findByCodice(codiceVolo);
    }

    /**
     * Metodo per trovare un gate, delega alla funzione omonima del controller specifico.
     * @param numeroGate Numero del gate da trovare.
     * @return Il gate trovato.
     */
    public Gate trovaGate(short numeroGate) {
        return gateDAO.findByNumero(numeroGate);
    }

    /**
     * Getter dei gate
     * @return Lista dei gates.
     */
    public List<Gate> getGates() {
        return gateDAO.findAll();
    }

    /**
     * Metodo per ottenere i dati dei voli formattati per la visualizzazione in tabella.
     * @return Array bidimensionale contenente i dati formattati per la tabella.
     */
    public Object[][] getVoliDataForTable() {
        List<Volo> voli = voloDAO.findAll();
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
