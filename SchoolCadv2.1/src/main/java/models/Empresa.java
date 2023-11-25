package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Empresa {
    private int id;
    private String nome;
    private String registro;
    private String area;

    public Empresa() {
        // Construtor padrão
    }

    public Empresa(String nome, String registro, String area) {
        this.nome = nome;
        this.registro = registro;
        this.area = area;
    }

    public Empresa(int id, String nome, String registro, String area) {
        this.id = id;
        this.nome = nome;
        this.registro = registro;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Empresa(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.registro = resultSet.getString("registro");
        this.area = resultSet.getString("area");
    }
}
