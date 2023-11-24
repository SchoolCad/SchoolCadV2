package views;

import models.Aluno;
import models.DatabaseSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class CreateAluno extends JFrame {

    private JPanel CreateAluno;

    private JTextField name;
    private JTextField registry;
    private JComboBox classroom;
    private JComboBox internship;
    private JLabel labelName;
    private JLabel registryLabel;
    private JLabel classroomLabel;
    private JLabel internshipLabel;
    private JButton add;
    private JButton back;

    public CreateAluno() {

        setContentPane(CreateAluno);
        setTitle("Menu Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        add.setEnabled(false);
        Map<Integer, String> classroomMap = new HashMap<>();
        Map<Integer, String> internshipMap = new HashMap<>();
        comboBoxesSetup(classroomMap, internshipMap);


        name.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                isInputsValid();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                isInputsValid();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                isInputsValid();
            }
        });
        registry.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                isInputsValid();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                isInputsValid();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                isInputsValid();
            }
        });
        classroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter a string selecionada
                isInputsValid();
            }
        });
        internship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isInputsValid();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno novoAluno = new Aluno(
                        name.getText(),
                        registry.getText(),
                        getKeyByValue(classroomMap, (String) classroom.getSelectedItem()) < 0 ?
                                null :
                                getKeyByValue(classroomMap, (String) classroom.getSelectedItem())
                        ,
                        getKeyByValue(internshipMap, (String)internship.getSelectedItem()) < 0 ?
                                null :
                                getKeyByValue(internshipMap, (String)internship.getSelectedItem())
                        );
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAluno();
                setVisible(false);
            }
        });

    }

    private void comboBoxesSetup(Map<Integer, String> classroomIndexOptions, Map<Integer, String> internshipIndexOptions) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            classroomIndexOptions.put(-1, "");
            classroomIndexOptions.put(-2, "O aluno ainda não pertence a nenhuma tuma");

            ResultSet turmas = singleton.executeSelect("SELECT id, ano FROM turma");
            while (turmas.next()){
                int turma_id = turmas.getInt("id");
                String turma_ano = turmas.getString("ano");
                classroomIndexOptions.put(turma_id, turma_ano);
            }
            String[] classroomOptionsArray = classroomIndexOptions.values().toArray(new String[0]);
            classroom.setModel(new DefaultComboBoxModel(classroomOptionsArray));

            DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            classroom.setRenderer(centerRenderer);


            internshipIndexOptions.put(-1, "");
            internshipIndexOptions.put(-2, "O aluno ainda não pertence a nenhuma empresa de estágio");

            ResultSet estagios = singleton.executeSelect("SELECT id, nome FROM empresa");
            while (estagios.next()){
                int estagio_id = estagios.getInt("id");
                String estagio_nome = estagios.getString("nome");
                internshipIndexOptions.put(estagio_id, estagio_nome);
            }
            String[] internshipOptionsArray = internshipIndexOptions.values().toArray(new String[0]);
            internship.setModel(new DefaultComboBoxModel(internshipOptionsArray));

            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            internship.setRenderer(centerRenderer);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }

    }

    private void isInputsValid () {

        if (
                name.getText().trim().isEmpty() ||
                        //TODO: verify if is a valid registry
                registry.getText().trim().isEmpty() ||
                classroom.getSelectedIndex() == 0 ||
                internship.getSelectedIndex() == 0
        ) {
            add.setEnabled(false);
        } else {
            add.setEnabled(true);
        }

    }

    private int getKeyByValue(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
