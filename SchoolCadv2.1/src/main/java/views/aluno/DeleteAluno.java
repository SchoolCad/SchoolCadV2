package views.aluno;

import models.DatabaseSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DeleteAluno extends JFrame{
    private JPanel DeleteAluno;
    private JLabel deleteLabel;
    private JComboBox comboBox;
    private JButton delete;
    private JButton back;

    public DeleteAluno() {
        setContentPane(DeleteAluno);
        setTitle("Deletar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        delete.setEnabled(false);
        Map<Integer, String> registryMap = new HashMap<>();
        comboBoxSetup(registryMap);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAluno();
                setVisible(false);
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedIndex() != 0) {
                    delete.setEnabled(true);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                    int delete = singleton.executeDML(String.format("DELETE FROM aluno WHERE registro='%s'", comboBox.getSelectedItem()));

                    JOptionPane.showMessageDialog(null, String.format("Aluno de registro %s excluído com sucesso!", comboBox.getSelectedItem()), "Excluir", 2);
                    setVisible(false);

                    new MenuAluno();
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Error", 2);
                }

            }
        });
    }

    private void comboBoxSetup (Map<Integer, String> options) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            ResultSet registros = singleton.executeSelect("SELECT registro FROM aluno");
            options.put(0, "");
            for(int i=1 ; registros.next() ; i++){
                options.put(i, registros.getString("registro"));
            }
            String[] registryOptionsArray = options.values().toArray(new String[0]);
            comboBox.setModel(new DefaultComboBoxModel(registryOptionsArray));

            DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            comboBox.setRenderer(centerRenderer);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }

    }
}
