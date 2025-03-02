package model;
import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codProduto;
    private String nome;
    private String tipo;

    public Produto(int codProduto, String nome, String tipo) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getCodProduto() { return codProduto; }
    public void setCodProduto(int codProduto) { this.codProduto = codProduto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Produto{" + "codProduto=" + codProduto + ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' + '}';
    }
}
