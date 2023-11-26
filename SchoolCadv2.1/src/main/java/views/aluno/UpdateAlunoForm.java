package views.aluno;

import models.Aluno;
import models.DatabaseSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class UpdateAlunoForm extends JFrame {

    private JPanel UpdateAluno;
    private JLabel labelName;
    private JLabel registryLabel;
    private JTextField registry;
    private JTextField name;
    private JComboBox classroom;
    private JLabel classroomLabel;
    private JLabel registryWarning;
    private JComboBox internship;
    private JButton edit;
    private JButton back;
    private JLabel internshipLabel;

    public UpdateAlunoForm(Aluno alunoValues){

        setContentPane(UpdateAluno);
        setTitle("Menu Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

        registryWarning.setVisible(false);
        edit.setEnabled(false);
        Map<Integer, String> classroomMap = new HashMap<>();
        Map<Integer, String> internshipMap = new HashMap<>();
        inputsSetup(classroomMap, internshipMap, alunoValues);

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
                isInputsValid();

            }
        });
        internship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isInputsValid();

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAluno();
                setVisible(false);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alunoValues.updateAluno(
                        name.getText(),
                        registry.getText(),
                        getKeyByValue(classroomMap, (String) classroom.getSelectedItem()),
                        getKeyByValue(internshipMap, (String)internship.getSelectedItem())
                );
                setVisible(false);
                new UpdateAlunoList();
            }
        });
    }
    private boolean isRegistryValid () {
        if(!registry.getText().isEmpty()){
            if(registry.getText().length() != 9){
                registryWarning.setVisible(true);
                return false;
            } else {
                registryWarning.setVisible(false);
                return true;
            }
        }
        return false;
    }

    private void isInputsValid () {

        if (
                        !isRegistryValid() ||
                        name.getText().trim().isEmpty() ||
                        classroom.getSelectedIndex() == 0 ||
                        internship.getSelectedIndex() == 0
        ) {
            edit.setEnabled(false);
        } else {
            edit.setEnabled(true);
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

    private void inputsSetup(Map<Integer, String> classroomIndexOptions, Map<Integer, String> internshipIndexOptions, Aluno aluno) {
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


            this.name.setText(aluno.getNome());
            this.registry.setText(aluno.getRegistro());
            if(aluno.getTurma().equals("Nenhuma")){
                classroom.setSelectedIndex(1);
            } else {
                classroom.setSelectedIndex(getKeyByValue(classroomIndexOptions, aluno.getTurma())+1);
            }
            if(aluno.getEstagios().equals("Nenhum")){
                internship.setSelectedIndex(1);
            } else {
                internship.setSelectedIndex(getKeyByValue(internshipIndexOptions, aluno.getEstagios())+1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }

    }

}
