package views;

import models.DatabaseSingleton;
import models.Empresa;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmpresaForm extends  JFrame{
    private JLabel titleLabel;
    private JTextField nomeField;
    private JTextField registroField;
    private JTextField areaField;
    private JButton returnToMenu;
    private JButton updateButton;
    private JPanel UpdateEmpresaForm;
    private JButton clearButton;

    private void checkFields(Empresa initialValues) {
        String nomeAtual = nomeField.getText();
        String registroAtual = registroField.getText();
        String areaAtual = areaField.getText();

        boolean fieldsCompleted = !nomeAtual.isEmpty() &&
                isCnpjValido(registroAtual) &&
                !areaAtual.isEmpty() &&
                (!nomeAtual.equals(initialValues.getNome()) ||
                        !registroAtual.equals(initialValues.getRegistro()) ||
                        !areaAtual.equals(initialValues.getArea()));

        updateButton.setEnabled(fieldsCompleted);
    }
    public UpdateEmpresaForm(Empresa initialValues) {
        //System.out.println(initialValues.getId());
        //System.out.println(initialValues.getNome());
        //System.out.println(initialValues.getRegistro());
        //System.out.println(initialValues.getArea());

        setTitle("Atualizar Empresa");
        setContentPane(UpdateEmpresaForm);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);

        nomeField.setText(initialValues.getNome());
        registroField.setText(initialValues.getRegistro());
        areaField.setText(initialValues.getArea());

        updateButton.setEnabled(false);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields(initialValues);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields(initialValues);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields(initialValues);
            }
        };

        nomeField.getDocument().addDocumentListener(documentListener);
        registroField.getDocument().addDocumentListener(documentListener);
        areaField.getDocument().addDocumentListener(documentListener);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                    String novoNome = nomeField.getText();
                    String novoRegistro = registroField.getText();
                    String novaArea = areaField.getText();

                    String sql = "UPDATE empresa SET nome=?, registro=?, area=? WHERE id=?";

                    PreparedStatement preparedStatement = singleton.getConnection().prepareStatement(sql);

                    preparedStatement.setString(1, nomeField.getText());
                    preparedStatement.setString(2, registroField.getText());
                    preparedStatement.setString(3, areaField.getText());
                    preparedStatement.setInt(4, initialValues.getId());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        exibirMensagem("Empresa alterada com sucesso!");
                    } else {
                        exibirMensagem("Falha ao alterar a empresa.");
                    }
                    redirecionarParaMenuEmpresa();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    exibirMensagem("Erro ao executar a instrução SQL: " + ex.getMessage());
                    redirecionarParaMenuEmpresa();
                }
            }
        });

        setVisible(true);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeField.setText(initialValues.getNome());
                registroField.setText(initialValues.getRegistro());
                areaField.setText(initialValues.getArea());
            }
        });
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UpdateEmpresaTable(true);
            }
        });
    }

    private void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para redirecionar para o MenuEmpresa
    private void redirecionarParaMenuEmpresa() {
        setVisible(false);
        new MenuEmpresa();
    }
    private boolean isCnpjValido(String cnpj) {
        return cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    }
}
