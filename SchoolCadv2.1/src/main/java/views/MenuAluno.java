package views;

import views.UpdateAluno.UpdateAlunoList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAluno extends JFrame {

    private JPanel MenuAluno;
    private JLabel options;
    private JButton list;
    private JButton add;
    private JButton update;

    public MenuAluno() {
        setContentPane(MenuAluno);
        setTitle("Menu Aluno");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new ListAluno();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new CreateAluno();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new UpdateAlunoList();
            }
        });

    }
}
