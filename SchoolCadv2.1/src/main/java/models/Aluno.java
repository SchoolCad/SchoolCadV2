package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno {
    private int id;
    private String nome;
    private String registro;
    private int serie;
    private String Turma;
    private String Estagios;

    // Construtores, getters e setters

    public Aluno(int id, String nome, String registro, int serie, String Turma, String Estagios) {
        this.id = id;
        this.nome = nome;
        this.registro = registro;
        this.serie = serie;
        this.Turma = Turma;
        this.Estagios = Estagios;
    }

    // Segundo construtor que aceita um ResultSet
    public Aluno(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.registro = resultSet.getString("registro");
        this.serie = resultSet.getInt("serie");

        //TODO: o certo é fazer com que ele busque na table
        //isso aq é gambs
        this.Turma = Integer.toString(resultSet.getInt("id_turma"));
        this.Estagios = Integer.toString(resultSet.getInt("id_estagio"));
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

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public void setTurma(String turma) {
        Turma = turma;
    }

    public void setEstagios(String estagios) {
        Estagios = estagios;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRegistro() {
        return registro;
    }

    public int getSerie() {
        return serie;
    }

    public String getTurma() {
        return Turma;
    }

    public String getEstagios() {
        return Estagios;
    }

}
