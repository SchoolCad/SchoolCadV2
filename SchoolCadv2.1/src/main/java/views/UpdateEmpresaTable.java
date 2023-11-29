package views;

import adapters.TableAdapter;
import models.DatabaseSingleton;
import models.Empresa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateEmpresaTable extends JFrame{
    private JTable tableList;
    private JPanel UpdateEmpresaTable;
    private JButton returnToMenu;
    private JButton selectCompany;

    private TableAdapter tableModel;

    private Empresa initialValues;

    public UpdateEmpresaTable(boolean isUpdate) {
        setContentPane(UpdateEmpresaTable);
        setTitle("Selecionar Empresa");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);

        if(!isUpdate) {

        }

        selectCompany.setEnabled(false);

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

        tableList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableList.getSelectedRow();

                int id = (int) tableList.getValueAt(selectedRow, 0);
                String nome = (String) tableList.getValueAt(selectedRow, 1);
                String registro = (String) tableList.getValueAt(selectedRow, 2);
                String area = (String) tableList.getValueAt(selectedRow, 3);

                initialValues = new Empresa(id, nome, registro, area);

                selectCompany.setEnabled(true);
            }
        });
        selectCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                if(isUpdate) {
                    new UpdateEmpresaForm(initialValues);
                } else {
                    new DeleteEmpresaForm(initialValues);
                }
            }
        });

        setVisible(true);
    }

    private void redirecionarParaMenuEmpresa() {
        setVisible(false);
        new MenuEmpresa();
    }
}
