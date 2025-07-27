package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  ********************************* CLASSE LOGIN FORM **************************************
//   ******************************************************************************************

/**
 * Form di login per l'autenticazione degli utenti nel sistema.
 * Gestisce il login degli utenti e l'accesso come guest.
 */
public class LoginForm {
    /** Pannello principale del form di login. */
    private JPanel rootPanel;
    /** Campo di testo per l'inserimento dell'username. */
    private JTextField usernameField;
    /** Campo di testo per l'inserimento della password. */
    private JPasswordField passwordField;
    /** Pulsante per effettuare il login. */
    private JButton loginButton;
    /** Pulsante per accedere come guest. */
    private JButton guestButton;

    /** Riferimento al frame principale dell'applicazione. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale dell'applicazione. */
    private Controller controller;

    /**
     * Costruttore di LoginForm.
     * @param mainFrame Il frame principale
     * @param controller Il controller
     */
    public LoginForm(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    if (controller.login(username, password)) {
                        usernameField.setText("");
                        passwordField.setText("");

                        if (controller.isAmministratore()) {
                            mainFrame.showPanel("ADMIN");
                        } else {
                            mainFrame.showPanel("USER");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPanel,
                                "Login fallito. Credenziali non valide.",
                                "Errore",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(rootPanel,
                            "Errore durante il login: " + ex.getMessage(),
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("GUEST");
            }
        });
    }

    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
