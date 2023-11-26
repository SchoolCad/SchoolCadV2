package models;

import javax.swing.*;
import java.sql.SQLException;

public class Professor {

    //atributes

    private int id;
    private String nome;
    private String registro;
    private String materia;
    private int turma;

    //constructor

    public Professor (String nome, String registro, String materia, int turma, int id) {
        this.id = id;
        this.nome = nome;
        this.registro = registro;
        this.materia = materia;
        this.turma = turma;
    }

    public Professor (String nome, String registro, String materia, int turma) {
        try {
            DatabaseSingleton singleton = DatabaseSingleton.getInstance();
            String query = String.format(
                    "INSERT INTO professor (nome, registro, materia, id_turma) VALUES ('%s', '%s', '%s', '%d')",
                    nome, registro, materia, turma < 0 ? null : turma
            );
            int insert = singleton.executeDML(query);
            JOptionPane.showMessageDialog(null, "Professor " + nome +" cadastrado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
        }
    }

    //methods

    //getters and setters

    public int getId() {
        return id;
    }

    public int getTurma() {
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

    public void setTurma(int turma) {
        this.turma = turma;
    }
}
