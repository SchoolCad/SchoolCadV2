package views.empresa;

import models.DatabaseSingleton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarEmpresa extends JFrame{
    private JLabel titleCadastrar;
    private JPanel CadastrarEmpresa;
    private JTextField nomeField;
    private JTextField registroField;
    private JTextField areaField;
    private JButton submitButton;
    private JButton returnToMenu;


    private void checkFields() {
        boolean fieldsCompleted = !nomeField.getText().isEmpty() &&
                isCnpjValido(registroField.getText()) &&
                !areaField.getText().isEmpty();

        submitButton.setEnabled(fieldsCompleted);
    }
    public CadastrarEmpresa() {
        setContentPane(CadastrarEmpresa);
        setTitle("Cadastro de Empresa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        submitButton.setEnabled(false);
        setVisible(true);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }
        };

        nomeField.getDocument().addDocumentListener(documentListener);
        registroField.getDocument().addDocumentListener(documentListener);
        areaField.getDocument().addDocumentListener(documentListener);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(nomeField.getText() + '\n' + registroField.getText() + '\n' + areaField.getText());

                try {
                    DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                    String sql = "INSERT INTO empresa(nome, registro, area) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = singleton.getConnection().prepareStatement(sql);

                    preparedStatement.setString(1, nomeField.getText());
                    preparedStatement.setString(2, registroField.getText());
                    preparedStatement.setString(3, areaField.getText());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        exibirMensagem("Empresa cadastrada com sucesso!");
                    } else {
                        exibirMensagem("Falha ao cadastrar a empresa.");
                    }

                    preparedStatement.close();
                    redirecionarParaMenuEmpresa();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    exibirMensagem("Erro ao executar a instrução SQL: " + ex.getMessage());
                    redirecionarParaMenuEmpresa();
                }
            }
        });

        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirecionarParaMenuEmpresa();
            }
        });
    }
    private void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para redirecionar para o MenuEmpresa
    private void redirecionarParaMenuEmpresa() {
       dispose();
        new MenuEmpresa();
    }

    private boolean isCnpjValido(String cnpj) {
        // Utiliza uma expressão regular para verificar o formato do CNPJ
        return cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    }
}
