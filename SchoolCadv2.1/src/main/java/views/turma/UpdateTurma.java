package views.turma;

import models.DatabaseSingleton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTurma extends JFrame{
    private JPanel UpdateTurma;
    private JTextField IdInput;
    private JButton UpdateBtn;
    private JButton CancelBtn;
    private JTextField newCodeInput;
    private JTextField newAnoInput;

    private boolean checker(){
        if(
                (!IdInput.getText().isBlank() && !IdInput.getText().isEmpty() && IdInput.getText().matches("^[1-9]+$")) &&
                (!newCodeInput.getText().isEmpty() && !newCodeInput.getText().isBlank() && newCodeInput.getText().length() > 0 && newCodeInput.getText().length() <= 64) &&
                (!newAnoInput.getText().isEmpty() && !newAnoInput.getText().isBlank() && newAnoInput.getText().matches("^[0-9]+$"))
        )
        {
            UpdateBtn.setEnabled(true);
            return true;
        } else {
            UpdateBtn.setEnabled(false);
            return false;
        }
    }

    public UpdateTurma() {

        setTitle("Atualizar turma");

        setContentPane(UpdateTurma);
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
        IdInput.getDocument().addDocumentListener(listenerList);
        newCodeInput.getDocument().addDocumentListener(listenerList);
        newAnoInput.getDocument().addDocumentListener(listenerList);

        UpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checker()){
                    try {
                        DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                        String sql = "UPDATE turma SET turma.ano = ?, turma.cod_sala = ? WHERE id = ?";
                        PreparedStatement preparedStatement = singleton.getConnection().prepareStatement(sql);

                        preparedStatement.setInt(1, Integer.parseInt(newAnoInput.getText()));
                        preparedStatement.setString(2, newCodeInput.getText());
                        preparedStatement.setInt(3, Integer.parseInt(IdInput.getText()));

                        if(preparedStatement.executeUpdate() > 0){
                            JOptionPane.showMessageDialog(null, "Turma atualizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível atualizar turma :(");
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

        CancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuTurma();

                dispose();
            }
        });
    }
}
