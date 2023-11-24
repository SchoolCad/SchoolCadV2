package views;

import javax.swing.*;

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
    }

    private void isInputsValid () {

        if (
                name.getText().trim().isEmpty() ||
                registry.getText().trim().isEmpty() ||
                classroom.getSelectedIndex() == -1 ||
                internship.getSelectedIndex() == -1
        ) {
            add.setEnabled(false);
        } else {
            add.setEnabled(true);
        }

    }

}
