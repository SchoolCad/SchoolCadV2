package views.turma;

import adapters.TableAdapter;
import models.DatabaseSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ReadTurmas extends JFrame{
    private JPanel ReadTurmasPane;
    private JLabel pageTitle;
    private JButton backBtn;
    private JTable turmaTable;
    private TableAdapter tableModel;

    public ReadTurmas(){
        setTitle("Listar turmas");

        setContentPane(ReadTurmasPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            ResultSet countTurmasRows = singleton.executeSelect("SELECT COUNT(*) AS total FROM turma");

            countTurmasRows.next();
            int rows = countTurmasRows.getInt("Total");
            Object[] tableItens = new Object[rows];

            ResultSet turmas = singleton.executeSelect("SELECT * FROM turma");

            int index = 0;
            while (turmas.next()){
                int id = turmas.getInt("id");
                int ano = turmas.getInt("ano");
                String cod_sala = turmas.getString("cod_sala");

                tableItens[index] = new Object[]{id, ano, cod_sala};
                index++;
            }

            String[] columnNames = {"Id", "Ano da turma", "Código da sala"};
            tableModel = new TableAdapter(columnNames, tableItens);
            this.turmaTable.setModel(tableModel);
        } catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Houve algum erro :(", JOptionPane.ERROR_MESSAGE);
        }

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuTurma();

                // 'mata' a tela:
                dispose();
            }
        });
    }
}
