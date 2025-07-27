package gui.user.operazioni;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  ************************ CLASSE MODIFICA PRENOTAZIONE PANEL ******************************
//   ******************************************************************************************

/**
 * Pannello per modificare una prenotazione esistente da parte dell'utente.
 * Permette la modifica dei dati di una prenotazione tramite numero biglietto.
 */
public class ModificaPrenotazionePanel {
    /** Costante per il titolo dei dialoghi di errore. */
    private static final String ERRORE = "Errore";

    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Campo di testo per il numero del biglietto da modificare. */
    private JTextField numeroBigliettoTextField;
    /** Campo di testo per il nuovo nome del passeggero. */
    private JTextField nuovoNomePasseggeroTextField;
    /** Campo di testo per il nuovo posto assegnato. */
    private JTextField nuovoPostoTextField;
    /** Pulsante per confermare la modifica. */
    private JButton confermaButton;
    /** Pulsante per tornare indietro al pannello utente. */
    private JButton indietroButton;

    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore di ModificaPrenotazione.
     * @param mainFrame Il frame principale.
     * @param controller Il controller.
     */
    public ModificaPrenotazionePanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaPrenotazione();
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("USER");
            }
        });
    }

    private void modificaPrenotazione() {
        String numeroBiglietto = numeroBigliettoTextField.getText();
        String nuovoNome = nuovoNomePasseggeroTextField.getText();
        String nuovoPosto = nuovoPostoTextField.getText();

        if (numeroBiglietto.isEmpty() || nuovoNome.isEmpty() || nuovoPosto.isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel, "Tutti i campi sono obbligatori.", ERRORE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean successo = controller.modificaPrenotazione(numeroBiglietto, nuovoNome, nuovoPosto);
            if (successo) {
                JOptionPane.showMessageDialog(rootPanel, "Prenotazione modificata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Errore: prenotazione non trovata o utente non autorizzato.", ERRORE, JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(rootPanel,
                "Errore durante la modifica: " + e.getMessage(),
                ERRORE,
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pulisciCampi() {
        numeroBigliettoTextField.setText("");
        nuovoNomePasseggeroTextField.setText("");
        nuovoPostoTextField.setText("");
    }


    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
    return rootPanel;
}
}
