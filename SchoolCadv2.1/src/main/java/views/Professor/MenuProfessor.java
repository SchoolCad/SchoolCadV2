package views.Professor;

import views.Professor.CreateProfessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProfessor extends JFrame {
    private JButton list;
    private JButton add;
    private JButton update;
    private JButton delete;
    private JPanel MenuProfessor;

    public MenuProfessor() {

        setContentPane(MenuProfessor);
        setTitle("Menu Professor");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ListProfessor();
            }
        });


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                dispose();
                new CreateProfessor();
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
