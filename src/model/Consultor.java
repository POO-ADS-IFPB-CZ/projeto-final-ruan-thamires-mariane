package model;

import java.io.Serializable;

public class Consultor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;

    public Consultor(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
