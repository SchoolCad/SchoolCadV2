package views.Professor;

import adapters.TableAdapter;
import models.DatabaseSingleton;
import models.Professor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeleteProfessor extends JFrame {
    private JTable table;
    private JPanel DeleteProfessorPane;
    private JButton delete;
    private JButton back;

    private TableAdapter tableModel;

    ArrayList<Professor> listProfessor = new ArrayList<Professor>();

    private void CenterJTableCells() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        }
    }

    public DeleteProfessor () {
        setContentPane(DeleteProfessorPane);
        setTitle("Atualizar Professores");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        delete.setEnabled(false);

        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            ResultSet countProfessorRows = singleton.executeSelect("SELECT COUNT(*) AS qtd FROM professor");

            countProfessorRows.next();
            int rows = countProfessorRows.getInt("qtd");
            Object tableItens[] = new Object[rows];

            int index = 0;
            String[] columnNames = {"Id", "Nome", "Registro", "Matéria", "Turma"};

            ResultSet ps = singleton.executeSelect("SELECT * FROM professor");
            while (ps.next()) {
                Professor p = new Professor(ps);
                listProfessor.add(p);

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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    delete.setEnabled(true);
                } else {
                    delete.setEnabled(false);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Professor selected = listProfessor.get(table.getSelectedRow());
                try {
                    DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                    singleton.executeDML(String.format("DELETE FROM professor WHERE id='%d'", selected.getId()));

                    JOptionPane.showMessageDialog(null, String.format("Professor %s deletado com sucesso!", selected.getNome()));
                    dispose();

                    new MenuProfessor();
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Error", 2);
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuProfessor();
            }
        });
    }
}
