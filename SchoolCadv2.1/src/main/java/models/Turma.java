package models;

public class Turma {
    private int id;
    private int ano;
    private String cod_sala;

    // constructor:
    public Turma(int id, int ano, String cod_sala){
        this.id = id;
        this.ano = ano;
        this.cod_sala = cod_sala;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setCod_sala(String cod_sala) {
        this.cod_sala = cod_sala;
    }

    // getters:

    public int getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public String getCod_sala() {
        return cod_sala;
    }
}
