package model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codCliente;
    private String nome;
    private String endereco;
    private String telefone;

    public Cliente(int codCliente, String nome, String endereco, String telefone) {
        this.codCliente = codCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getCodCliente() { return codCliente; }
    public void setCodCliente(int codCliente) { this.codCliente = codCliente; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Cliente{" + "codCliente=" + codCliente + ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' + ", telefone='" + telefone + '\'' + '}';
    }
}
