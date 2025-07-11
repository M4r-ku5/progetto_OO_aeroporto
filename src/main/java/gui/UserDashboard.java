package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserDashboard {
    private JTable infoVoliTable;
    private JPanel panel1;
    private JScrollPane tabellaJScrollPane;
    private JPanel tabellaPanel;
    private JButton areaPersonaleButton;
    private JLabel homeJLabel;

    private controller.Controller controller;


    public UserDashboard () {

        controller = new controller.Controller();



    }

    public JPanel getPanel () {
        return panel1;
    }

    private void populateVoliTable () {

        String[] columnNames = {"Codice Volo", "Compagnia Aerea", "Data", "Orario",
                "Ritardo", "Stato", "Aeroporto Origine", "Aeroporto Destinazione", "Gate"};

        DefaultTableModel model = new DefaultTableModel();

        List<Volo> voli = controller.getAllVoli();

        for(Volo volo : voli) {
            Object[] rowData = {
                    volo.getCodice();
                    volo.getCompagniaAerea();
                    volo.getData();
                    volo.getOrario();
                    volo.getRitardo();
                    volo.getStatoDelVolo();


            }
        }

    }
}
