package views;

import adapters.TableAdapter;
import models.DatabaseSingleton;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Vector;

public class ListAluno extends JFrame {
    private JPanel ListAluno;
    private JTable table;
    private TableAdapter tableModel;


    public ListAluno() {
        setContentPane(ListAluno);
        setTitle("Lista de Alunos");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);
//        ListAluno.add(new JScrollPane(table));

        try {

            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            ResultSet countAlunosRows = singleton.executeSelect("SELECT COUNT(*) AS total FROM aluno");

            countAlunosRows.next();
            int rows = countAlunosRows.getInt("total");
            Object[] tableItens = new Object[rows];


            ResultSet alunos = singleton.executeSelect("SELECT * FROM aluno");

            int index = 0;
            while (alunos.next()) {
                int id = alunos.getInt("id");
                String nome = alunos.getString("nome");
                String registro = alunos.getString("registro");
                int serie = alunos.getInt("serie");
                int idTurma = alunos.getInt("id_turma");
                int idEstagio = alunos.getInt("id_estagio");


                tableItens[index] = new Object[]{id, nome, registro, serie, idTurma, idEstagio};
                index++;
            }

            String[] columnNames = {"Id", "Nome", "Registro", "Serie", "Turma", "Estágio"};

            System.out.println(tableModel);

            tableModel = new TableAdapter(columnNames, tableItens);
            this.table.setModel(tableModel);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);

        }



    }

}
