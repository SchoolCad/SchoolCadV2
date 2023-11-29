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

public class DeleteTurma extends JFrame{
    private JPanel DeleteTurma;
    private JButton DeleteBtn;
    private JTextField IdInput;
    private JButton CancelBtn;

    private boolean checker(){
        if(IdInput.getText().isBlank() || IdInput.getText().isEmpty() || !IdInput.getText().matches("^[1-9]+$")){
            DeleteBtn.setEnabled(false);

            return false;
        } else {
            DeleteBtn.setEnabled(true);

            return  true;
        }
    }
    public DeleteTurma(){

        setTitle("Cadastrar turma");

        setContentPane(DeleteTurma);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

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

        DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checker()){
                    try {
                        DatabaseSingleton singleton = DatabaseSingleton.getInstance();
                        String sql = "DELETE FROM turma WHERE turma.id = ?";
                        PreparedStatement preparedStatement = singleton.getConnection().prepareStatement(sql);

                        preparedStatement.setInt(1, Integer.parseInt(IdInput.getText()));

                        if(preparedStatement.executeUpdate() > 0){
                            JOptionPane.showMessageDialog(null, "Turma deletada com sucesso!");

                            sql = "UPDATE aluno SET id.turma = null WHERE id_turma = ?";
                            preparedStatement = singleton.getConnection().prepareStatement(sql);

                            preparedStatement.setInt(1, Integer.parseInt(IdInput.getText()));

                            if (preparedStatement.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "Os dados dos alunos com as turmas deletadas foram atualizados.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Não haviam alunos na turma deletada.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível deletar a turma :(");
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
