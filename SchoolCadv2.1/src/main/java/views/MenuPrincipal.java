package views;

import views.Professor.MenuProfessor;
import views.aluno.MenuAluno;
import views.empresa.MenuEmpresa;
import views.turma.MenuTurma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel MenuPrincipalPane;
    private JButton alunoButton;
    private JButton professorButton;
    private JButton turmaButton;
    private JButton empresaButton;

    public MenuPrincipal () {
        setTitle("Menu Principal");

        setContentPane(MenuPrincipalPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setVisible(true);

        turmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuTurma();

                dispose();
            }
        });
        alunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAluno();

                dispose();
            }
        });
        empresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuEmpresa();

                dispose();
            }
        });
        professorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuProfessor();

                dispose();
            }
        });
    }
}