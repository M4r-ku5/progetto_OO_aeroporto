package gui.admin.operazioni;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  *************************** CLASSE ASSEGNA GATE PANEL ***********************************
//   ******************************************************************************************

/**
 * Pannello per l'assegnazione di un gate a un volo da parte dell'amministratore.
 */
public class AssegnaGatePanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Campo di testo per il codice del volo. */
    private JTextField codiceVoloTextField;
    /** Campo di testo per il numero del gate. */
    private JTextField numeroGateTextField;
    /** Pulsante per confermare l'assegnazione del gate. */
    private JButton confermaButton;
    /** Pulsante per tornare indietro al pannello admin. */
    private JButton indietroButton;
    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore del pannello AssegnaGatePanel.
     * @param mainFrame il frame principale
     * @param controller il controller dell'applicazione
     */
    public AssegnaGatePanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assegnaGate();
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
     * Gestisce l'assegnazione del gate al volo, validando i dati e mostrando eventuali errori.
     */
    private void assegnaGate() {
        String codiceVolo = codiceVoloTextField.getText().trim();
        String numeroGateStr = numeroGateTextField.getText().trim();

        try {
            short numeroGate = Short.parseShort(numeroGateStr);
            boolean successo = controller.assegnaGate(codiceVolo, numeroGate);

            if (successo) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Gate assegnato con successo!",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel,
                        "Errore durante l'assegnazione del gate!",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException _) {
            JOptionPane.showMessageDialog(rootPanel,
                    "Formato numero gate non valido!",
                    "Errore di Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(rootPanel,
                    "Errore durante l'assegnazione del gate: " + ex.getMessage(),
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pulisce tutti i campi del form dopo l'assegnazione o in caso di reset.
     */
    private void pulisciCampi() {
        codiceVoloTextField.setText("");
        numeroGateTextField.setText("");
    }

    /**
     * Restituisce il root panel del pannello.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}