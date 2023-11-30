package views.Professor;

import models.DatabaseSingleton;
import models.Professor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class UpdateProfessorForm extends JFrame {
    private JTextField name;
    private JTextField registry;
    private JTextField subject;
    private JButton update;
    private JButton cancel;
    private JLabel registrywarning;
    private JLabel labelname;
    private JLabel labelregistry;
    private JLabel labelsubject;
    private JComboBox classroom;
    private JPanel UpdateProfessorFormPane;

    private boolean checkForms () {
        boolean x = isRegistryValid() && !name.getText().isEmpty() &&
                !subject.getText().isEmpty() && classroom.getSelectedIndex() != 0;
        update.setEnabled(x);
        return x;
    }

    private boolean isRegistryValid () {
        if(!registry.getText().isEmpty()){
            if(registry.getText().length() != 4){
                registrywarning.setVisible(true);
                return false;
            }
            registrywarning.setVisible(false);
            return true;
        }
        return false;
    }

    private void comboBoxSetup (Map<Integer, String> classroomIndexOptions) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            classroomIndexOptions.put(-1, "");

            ResultSet turmas = singleton.executeSelect("SELECT id, ano FROM turma");
            while (turmas.next()) {
                int turma_id = turmas.getInt("id");
                String turma_ano = turmas.getString("ano");
                classroomIndexOptions.put(turma_id, turma_ano);
            }
            String[] classroomOptionsArray = classroomIndexOptions.values().toArray(new String[0]);
            classroom.setModel(new DefaultComboBoxModel(classroomOptionsArray));

            DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            classroom.setRenderer(centerRenderer);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }

    }

    public UpdateProfessorForm (Professor professor) {
        setContentPane(UpdateProfessorFormPane);
        setTitle("Atualizar Professor");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        Map<Integer, String> classroomMap = new HashMap<>();
        comboBoxSetup(classroomMap);
        update.setEnabled(false);

        this.name.setText(professor.getNome());
        this.registry.setText(professor.getRegistro());
        this.subject.setText(professor.getMateria());
        this.classroom.setSelectedIndex(professor.getId_turma());

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkForms();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkForms();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkForms();
            }
        };

        name.getDocument().addDocumentListener(documentListener);

        subject.getDocument().addDocumentListener(documentListener);

        registry.getDocument().addDocumentListener(documentListener);

        classroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkForms();
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professor.updateProfessor(name.getText(), registry.getText(), subject.getText(), classroom.getSelectedIndex());
                dispose();
                new MenuProfessor();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UpdateProfessorList();
            }
        });
    }
}
