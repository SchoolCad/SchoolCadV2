package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MenuPrincipal extends JFrame {
    private JPanel MenuPrincipalPane;
    private JButton button1;

    public MenuPrincipal () {
        setContentPane(MenuPrincipalPane);
        setTitle("Menu Principal");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}