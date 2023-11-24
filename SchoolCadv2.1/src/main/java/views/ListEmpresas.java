package views;

import adapters.TableAdapter;
import models.DatabaseSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class ListEmpresas extends JFrame{
    private JLabel titleList;
    private JTable tableList;
    private JPanel ListEmpresas;
    private JButton returnToMenu;

    private TableAdapter tableModel;

    public ListEmpresas() {
        setContentPane(ListEmpresas);
        setTitle("Lista de Alunos");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            ResultSet countCompanyRows = singleton.executeSelect("SELECT COUNT(*) AS total FROM empresa");

            countCompanyRows.next();
            int rows = countCompanyRows.getInt("total");
            Object[] tableItens = new Object[rows];


            ResultSet empresas = singleton.executeSelect("SELECT * FROM empresa");

            int index = 0;
            while (empresas.next()) {
                int id = empresas.getInt("id");
                String nome = empresas.getString("nome");
                String registro = empresas.getString("registro");
                String area = empresas.getString("area");


                tableItens[index] = new Object[]{id, nome, registro, area};
                index++;
            }

            String[] columnNames = {"Id", "Nome", "Registro", "Área"};

            tableModel = new TableAdapter(columnNames, tableItens);
            this.tableList.setModel(tableModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirecionarParaMenuEmpresa();
            }
        });
    }
    private void redirecionarParaMenuEmpresa() {
        setVisible(false);
        new MenuEmpresa();
    }
}
