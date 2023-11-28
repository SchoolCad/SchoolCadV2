package models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor {

    //atributes

    private int id;
    private String nome;
    private String registro;
    private String materia;
    private int id_turma;

    private String turma;

    //constructor

    public Professor (String nome, String registro, String materia, int turma, int id) {
        this.id = id;
        this.nome = nome;
        this.registro = registro;
        this.materia = materia;
        this.id_turma = turma;
    }

    public Professor (String nome, String registro, String materia, int turma) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            String query = String.format(
                    "INSERT INTO professor (nome, registro, materia, id_turma) VALUES ('%s', '%s', '%s', '%d')",
                    nome, registro, materia, turma
            );
            int insert = singleton.executeDML(query);
            JOptionPane.showMessageDialog(null, "Professor " + nome +" cadastrado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
    }

    public Professor (ResultSet prof) throws SQLException {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();

            this.id = prof.getInt("id");
            this.nome = prof.getString("nome");
            this.registro = prof.getString("registro");
            this.materia = prof.getString("materia");
            this.id_turma = prof.getInt("id_turma");

            ResultSet turma = singleton.executeSelect("SELECT ano FROM turma WHERE id=" + id_turma);
            if(turma.next()) {
                this.turma = turma.getString("ano");
            } else {
                this.turma = "Nenhuma";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
    }

    //methods

    public void updateProfessor (String nome, String registro, String materia, int turma) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();

            String updateQuery = String.format(
                    "UPDATE professor SET nome='%s', registro='%s', id_turma=%d, materia='%s' WHERE id='%d'",
                    nome, registro, turma, materia, this.id
            );
            int update = singleton.executeDML(updateQuery);
            JOptionPane.showMessageDialog(null, "Professor " + nome +" alterado!");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public String getTurma() {
        return turma;
    }

    public String getMateria() {
        return materia;
    }

    public String getNome() {
        return nome;
    }

    public String getRegistro() {
        return registro;
    }

    public int getId_turma() {
        return id_turma;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }
}
