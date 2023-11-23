package views;

import adapters.TableAdapter;
import models.Aluno;
import models.DatabaseSingleton;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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

            int index = 0;
            String[] columnNames = {"Id", "Nome", "Registro", "Serie", "Turma", "Estágio"};

            List<Aluno> listaAlunos = new ArrayList<>();
            ResultSet alunos = singleton.executeSelect("SELECT * FROM aluno");
            while (alunos.next()) {
                Aluno aluno = new Aluno(alunos);
                listaAlunos.add(aluno);

                tableItens[index] = new Object[]{
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getRegistro(),
                        aluno.getSerie(),
                        aluno.getTurma(),
                        aluno.getEstagios()
                };
                index++;
            }

            tableModel = new TableAdapter(columnNames, tableItens);
            this.table.setModel(tableModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }


    }
}
