package views.empresa;

import views.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpresa extends JFrame{
    private JLabel titleMenu;
    private JButton deleteCompany;
    private JButton listCompany;
    private JButton postCompany;
    private JButton updateCompany;
    private JPanel MenuEmpresaPane;
    private JButton back;

    public MenuEmpresa() {
        setContentPane(MenuEmpresaPane);
        setTitle("Menu Empresa");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);
        listCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new ListEmpresas();
            }
        });
        postCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new CadastrarEmpresa();
            }
        });
        updateCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new UpdateEmpresaTable(true);
            }
        });
        deleteCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();

                new UpdateEmpresaTable(false);
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
