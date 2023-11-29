 package views.turma;

import views.MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class MenuTurma extends JFrame{
    private JPanel MenuTurm;
    private JButton createTurmaBtn;
    private JButton readTurmaBtn;
    private JButton updateTurmaBtn;
    private JButton deleteTurmaBtn;
    private JLabel titleMenu;
     private JButton backBtn;

     public MenuTurma(){
        setTitle("Menu das turmas");

        setContentPane(MenuTurm);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setMinimumSize(new Dimension(300,300));
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createTurmaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateTurma();

                dispose();
            }
        });

        readTurmaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadTurmas();

                dispose();
            }
        });

        updateTurmaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateTurma();

                dispose();
            }
        });

        deleteTurmaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteTurma();

                dispose();
            }
        });
         backBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 new MenuPrincipal();

                 dispose();
             }
         });
     }
}
