import gui.HomeLogin;

import javax.swing.*;

public class Main {
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("Aeroporto di Napoli - Login");
        HomeLogin loginForm = new HomeLogin();
        frame.setContentPane(loginForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}

