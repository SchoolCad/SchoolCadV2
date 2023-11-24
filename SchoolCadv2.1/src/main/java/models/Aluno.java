package models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno {
    private int id;
    private String nome;
    private String registro;
    private String Turma;
    private String Estagios;

    // Construtores, getters e setters

    public Aluno(int id, String nome, String registro, String Turma, String Estagios) {
        this.id = id;
        this.nome = nome;
        this.registro = registro;
        this.Turma = Turma;
        this.Estagios = Estagios;
    }

    public Aluno(String nome, String registro, int id_turma, int id_estagio) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            String query = String.format(
                    "INSERT INTO aluno (nome, registro, id_turma, id_estagio) VALUES ('%s', '%s', %d, %d)",
                    nome, registro, id_turma, id_estagio
            );
            int insert = singleton.executeDML(query);
            JOptionPane.showMessageDialog(null, "Aluno " + nome +" cadastrado!");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
    }

    public Aluno(ResultSet resultSet) throws SQLException {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();

            this.id = resultSet.getInt("id");
            this.nome = resultSet.getString("nome");
            this.registro = resultSet.getString("registro");


            int id_turma = resultSet.getInt("id_turma");
            ResultSet turma = singleton.executeSelect("SELECT ano FROM turma WHERE id=" + id_turma);
            turma.next();
            this.Turma = turma.getString("ano");

            int id_estagio = resultSet.getInt("id_estagio");
            ResultSet estagio = singleton.executeSelect("SELECT nome FROM empresa WHERE id=" + id_estagio);
            estagio.next();
            this.Estagios = estagio.getString("nome");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public void setTurma(String turma) {
        this.Turma = turma;
    }

    public void setEstagios(String estagios) {
        this.Estagios = estagios;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getRegistro() {
        return this.registro;
    }

    public String getTurma() {
        return this.Turma;
    }

    public String getEstagios() {
        return this.Estagios;
    }

}
