package gui.guest;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  ******************************** CLASSE GUEST PANEL *************************************
//   ******************************************************************************************

/**
 * Pannello per gli utenti guest che visualizza i voli disponibili.
 * Permette la visualizzazione dei voli senza necessità di autenticazione.
 */
public class GuestPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Tabella per la visualizzazione dei voli disponibili. */
    private JTable voliTable;
    /** Pulsante per accedere al sistema (login). */
    private JButton accediButton;

    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    public GuestPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        aggiornaTabellaVoli();

        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("LOGIN");
            }
        });
    }

    /**
     * Aggiorna la tabella dei voli per il guest.
     */
    public void aggiornaTabellaVoli() {
        String[] columnNames = controller.getVoliColumnNames();
        Object[][] data = controller.getVoliDataForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        voliTable.setModel(model);
    }

    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
