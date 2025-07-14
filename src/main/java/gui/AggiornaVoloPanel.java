package gui;

import controller.Controller;
import model.StatoVolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AggiornaVoloPanel {
    private JPanel rootPanel;
    private JTextField codiceVoloTextField;
    private JTextField orarioTextField;
    private JTextField ritardoTextField;
    private JComboBox<String> statoComboBox;
    private JButton confermaButton;
    private JButton indietroButton;

    private MainFrame mainFrame;
    private Controller controller;

    public AggiornaVoloPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        statoComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"PROGRAMMATO", "IN_RITARDO", "ATTERRATO", "CANCELLATO"}));


        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaVolo();
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("ADMIN");
            }
        });
    }

    private void aggiornaVolo() {
        String codiceVolo = codiceVoloTextField.getText();
        String orarioStr = orarioTextField.getText();
        String ritardoStr = ritardoTextField.getText();
        String statoStr = (String) statoComboBox.getSelectedItem();

        if (codiceVolo.isEmpty() || orarioStr.isEmpty() || ritardoStr.isEmpty() || statoStr == null) {
            JOptionPane.showMessageDialog(rootPanel, "Tutti i campi sono obbligatori.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalTime nuovoOrario = LocalTime.parse(orarioStr); // Formato HH:mm
            Duration nuovoRitardo = Duration.ofMinutes(Long.parseLong(ritardoStr)); // Ritardo in minuti
            StatoVolo nuovoStato = StatoVolo.valueOf(statoStr.toUpperCase().replace(" ", "_"));

            boolean successo = controller.aggiornaVolo(codiceVolo, nuovoOrario, nuovoRitardo, nuovoStato);

            if (successo) {
                JOptionPane.showMessageDialog(rootPanel, "Volo aggiornato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Errore: Volo non trovato o utente non autorizzato.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Formato orario non valido. Usare HH:mm.", "Errore di Formato", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Formato ritardo non valido. Inserire i minuti.", "Errore di Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Stato del volo non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pulisciCampi() {
        codiceVoloTextField.setText("");
        orarioTextField.setText("");
        ritardoTextField.setText("");
        statoComboBox.setSelectedIndex(0);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}