package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InserisciVoloPanel {
    private JComboBox<String> tipoVolo;
    private JPanel rootPanel;
    private JTextField codiceTextField;
    private JTextField compagniaTextField;
    private JTextField dataTextField;
    private JTextField orarioTextField;
    private JButton confermaButton;
    private JButton indietroButton;
    private JTextField aeroportoTextField; // Aggiungi questo campo nel form designer
    private JLabel aeroportoLabel; // Aggiungi questo campo nel form designer

    private MainFrame mainFrame;
    private Controller controller;

    public InserisciVoloPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        // Listener per il pulsante "Indietro"
        indietroButton.addActionListener(e -> mainFrame.showPanel("ADMIN"));

        // Listener per il pulsante "Conferma"
        confermaButton.addActionListener(e -> aggiungiVolo());

        // Listener per il JComboBox per cambiare l'etichetta
        tipoVolo.addActionListener(e -> aggiornaLabelAeroporto());

        // Impostazione iniziale dell'etichetta
        aggiornaLabelAeroporto();
        aeroportoTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void aggiornaLabelAeroporto() {
        if ("Volo in Partenza".equals(tipoVolo.getSelectedItem())) {
            aeroportoLabel.setText("Aeroporto Destinazione");
        } else {
            aeroportoLabel.setText("Aeroporto Origine");
        }
    }

    private void aggiungiVolo() {
        String codice = codiceTextField.getText();
        String compagnia = compagniaTextField.getText();
        String dataStr = dataTextField.getText();
        String orarioStr = orarioTextField.getText();
        String aeroporto = aeroportoTextField.getText();

        if (codice.isEmpty() || compagnia.isEmpty() || dataStr.isEmpty() || orarioStr.isEmpty() || aeroporto.isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel, "Tutti i campi sono obbligatori.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime orario = LocalTime.parse(orarioStr, DateTimeFormatter.ofPattern("HH:mm"));

            boolean successo;
            if ("Volo in Partenza".equals(tipoVolo.getSelectedItem())) {
                successo = controller.aggiungiVoloInPartenza(codice, compagnia, data, orario, aeroporto);
            } else {
                successo = controller.aggiungiVoloInArrivo(codice, compagnia, data, orario, aeroporto);
            }

            if (successo) {
                JOptionPane.showMessageDialog(rootPanel, "Volo aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Errore durante l'aggiunta del volo.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Formato data (dd/MM/yyyy) o orario (HH:mm) non valido.", "Errore di Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pulisciCampi() {
        codiceTextField.setText("");
        compagniaTextField.setText("");
        dataTextField.setText("");
        orarioTextField.setText("");
        aeroportoTextField.setText("");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}