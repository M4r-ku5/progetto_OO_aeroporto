package gui.admin.operazioni;

import controller.Controller;
import gui.MainFrame;
import model.prenotazione.Prenotazione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



// ******************************************************************************************
//  ************************** CLASSE CERCA PASSEGGERO PANEL ********************************
//   ******************************************************************************************

/**
 * Pannello per la ricerca di passeggeri da parte dell'amministratore.
 */
public class CercaPasseggeroPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Tabella per la visualizzazione dei risultati della ricerca. */
    private JTable risultatiPasseggeroTable;
    /** Pulsante per tornare indietro al pannello admin. */
    private JButton indietroButton;
    /** Pulsante per cercare un passeggero specifico. */
    private JButton cercaButton;
    /** Pulsante per mostrare tutti i passeggeri. */
    private JButton mostraTuttiButton;
    /** Campo di testo per il nome del passeggero da cercare. */
    private JTextField nomePasseggeroTextField;
    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore del pannello CercaPasseggeroPanel.
     * @param mainFrame  il frame principale
     * @param controller il controller dell'applicazione
     */
    public CercaPasseggeroPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        // Mostra subito tutti i passeggeri all'apertura del pannello
        mostraPasseggeri();

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cercaPasseggero();
            }
        });
        mostraTuttiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraPasseggeri();
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("ADMIN");
            }
        });
    }

    /**
     * Mostra tutti i passeggeri nella tabella.
     */
    public void mostraPasseggeri() {
        try {
            List<Prenotazione> risultati = controller.cercaPasseggero(""); // Cerca tutti
            aggiornaTabella(risultati);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(rootPanel,
                "Errore durante il caricamento dei passeggeri: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Aggiorna la tabella dei risultati con la lista delle prenotazioni trovate.
     * @param risultati lista delle prenotazioni da mostrare
     */
    private void aggiornaTabella(List<Prenotazione> risultati) {
        String[] columnNames = {"Nome Passeggero", "Numero Biglietto", "Posto", "Codice Volo", "Stato"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Prenotazione p : risultati) {
            String codiceVolo = "";
            if (p.getVoloInPartenza() != null) {
                codiceVolo = p.getVoloInPartenza().getCodiceVolo();
            }
            Object[] row = new Object[]{
                    p.getNomePasseggero(),
                    p.getNumeroBiglietto(),
                    p.getPostoAssegnato(),
                    codiceVolo,
                    p.getStatoDellaPrenotazione()
            };
            model.addRow(row);
        }
        risultatiPasseggeroTable.setModel(model);
    }

    /**
     * Cerca i passeggeri in base al nome inserito nel campo di testo.
     * Mostra un messaggio di errore se il campo è vuoto o se non ci sono risultati.
     */
    private void cercaPasseggero() {
        String nome = nomePasseggeroTextField.getText();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel, "Inserisci il nome del passeggero.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<Prenotazione> risultati = controller.cercaPasseggero(nome);
            if (risultati.isEmpty()) {
                JOptionPane.showMessageDialog(rootPanel, "Nessun passeggero trovato con quel nome.", "Nessun risultato", JOptionPane.INFORMATION_MESSAGE);
            }
            aggiornaTabella(risultati);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(rootPanel,
                "Errore durante la ricerca del passeggero: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Restituisce il root panel del pannello.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
