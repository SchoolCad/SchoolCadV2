package views.aluno;

import views.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAluno extends JFrame {

    private JPanel MenuAlunoPane;
    private JLabel options;
    private JButton list;
    private JButton add;
    private JButton update;
    private JButton delete;
    private JButton back;

    public MenuAluno() {
        setContentPane(MenuAlunoPane);
        setTitle("Menu Aluno");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new ListAluno();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new CreateAluno();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new UpdateAlunoList();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new DeleteAluno();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new MenuPrincipal();
            }
        });
    }
}
