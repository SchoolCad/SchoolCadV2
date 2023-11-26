package views.turma;

import controllers.Turma;
import models.DatabaseSingleton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTurma extends JFrame {
    private JPanel CreateTurma;
    private JTextField anoInput;
    private JTextField codeInput;
    private JButton saveBtn;
    private JLabel pageTitle;
    private JLabel anoLabel;
    private JLabel codeLabel;
    private JButton cancelBtn;

    private boolean checker(){
        if(
                (!codeInput.getText().isEmpty() && !codeInput.getText().isBlank() && codeInput.getText().length() > 0 && codeInput.getText().length() <= 64) && (!anoInput.getText().isEmpty() && !anoInput.getText().isBlank() && anoInput.getText().matches("^[0-9]+$"))
        ){
            saveBtn.setEnabled(true);

            return true;
        } else{
            saveBtn.setEnabled(false);

            return false;
        }
    }

    public CreateTurma(){
        setTitle("Cadastrar turma");

        setContentPane(CreateTurma);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        DocumentListener listenerList = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checker();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checker();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checker();
            }
        };

        anoInput.getDocument().addDocumentListener(listenerList);
        codeInput.getDocument().addDocumentListener(listenerList);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checker()){
                    try {
                        DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                        String sql = "INSERT INTO turma(ano, cod_sala) VALUES (?, ?)";
                        PreparedStatement preparedStatement = singleton.getConnection().prepareStatement(sql);

                        preparedStatement.setInt(1, Integer.parseInt(anoInput.getText()));
                        preparedStatement.setString(2, codeInput.getText());

                        if(preparedStatement.executeUpdate() > 0){
                            JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar a nova turma :(");
                        }

                        preparedStatement.close();

                        new MenuTurma();

                        dispose();
                    } catch (SQLException exception){
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Ocorreu um problema :(", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuTurma();

                // 'mata' a tela:
                dispose();
            }
        });
    }
}
