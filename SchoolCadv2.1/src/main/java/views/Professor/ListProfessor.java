package views.Professor;

import adapters.TableAdapter;
import models.DatabaseSingleton;
import models.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListProfessor extends JFrame {
    private JTable table;
    private JPanel ListProfessorPane;
    private JButton back;
    private TableAdapter tableModel;

    private void CenterJTableCells() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        }
    }

    public ListProfessor () {
        setContentPane(ListProfessorPane);
        setTitle("Lista de Professores");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        try {

            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            ResultSet countProfessorRows = singleton.executeSelect("SELECT COUNT(*) AS qtd FROM professor");

            countProfessorRows.next();
            int rows = countProfessorRows.getInt("qtd");
            Object tableItens[] = new Object[rows];

            int index = 0;
            String[] columnNames = {"Id", "Nome", "Registro", "Matéria", "Turma"};

            List<Professor> professors = new ArrayList<>();
            ResultSet ps = singleton.executeSelect("SELECT * FROM professor");
            while (ps.next()) {
                Professor p = new Professor(ps);
                professors.add(p);

                tableItens[index] = new Object[] {
                        p.getId(),
                        p.getNome(),
                        p.getRegistro(),
                        p.getMateria(),
                        p.getTurma()
                };
                index++;
            }

            tableModel = new TableAdapter(columnNames, tableItens);
            this.table.setModel(tableModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
        CenterJTableCells();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuProfessor();
            }
        });
    }
}