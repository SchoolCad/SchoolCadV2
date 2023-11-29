package views.empresa;

import models.DatabaseSingleton;
import models.Empresa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteEmpresaForm extends JFrame{
    private JButton excluirButton;
    private JLabel idLabel;
    private JLabel nomeLabel;
    private JLabel registroLabel;
    private JLabel areaLabel;
    private JPanel DeleteEmpresaForm;
    private JButton returnToMenu;

    public DeleteEmpresaForm(Empresa initialValues) {
        setContentPane(DeleteEmpresaForm);
        setTitle("Excluir Empresa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);

        idLabel.setText("" + initialValues.getId());
        nomeLabel.setText(initialValues.getNome());
        registroLabel.setText(initialValues.getRegistro());
        areaLabel.setText(initialValues.getArea());


        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseSingleton singleton = DatabaseSingleton.getInstance();

                    String sql = "SELECT COUNT(*) FROM aluno WHERE id_estagio = ?";

                    PreparedStatement preparedVerifyStatement = singleton.getConnection().prepareStatement(sql);

                    preparedVerifyStatement.setInt(1, initialValues.getId());

                    try (ResultSet resultSet = preparedVerifyStatement.executeQuery()) {
                        if (resultSet.next()) {
                            if (resultSet.getInt(1) > 0) {
                                sql = "UPDATE aluno SET id_estagio = null WHERE id_estagio = ?";

                                PreparedStatement preparedStatement = singleton.getConnection().prepareStatement(sql);

                                preparedStatement.setInt(1, initialValues.getId());

                                int rowsAffected = preparedStatement.executeUpdate();
                            }

                            sql = "DELETE FROM empresa WHERE id = ?";

                            PreparedStatement preparedStatement2 = singleton.getConnection().prepareStatement(sql);

                            preparedStatement2.setInt(1, initialValues.getId());

                            int rowsAffected2 = preparedStatement2.executeUpdate();

                            if(rowsAffected2 > 0) {
                                exibirMensagem("Empresa exlcuída com sucesso!");
                            } else {
                                exibirMensagem("Falha ao excluir a empresa.");
                            }
                        }
                    } catch (Exception error) {
                        error.printStackTrace();
                        exibirMensagem("Falha ao excluir a empresa.");
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
        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
                new UpdateEmpresaTable(false);
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
}
