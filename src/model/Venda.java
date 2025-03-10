package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int codVenda;
    private String cliente;
    private double valor;
    private String descricao;
    private LocalDate dataRegistro;

    public Venda(int codVenda, String cliente, double valor, String descricao, LocalDate dataRegistro) {
        this.codVenda = codVenda;
        this.cliente = cliente;
        this.valor = valor;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
    }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public int getCodVenda() { return codVenda; }
    public void setCodVenda(int codVenda) { this.codVenda = codVenda; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    @Override
    public String toString() {
        return "Venda{" + "codVenda=" + codVenda + ", cliente=" + cliente +
                ", valor=" + valor + ", descricao='" + descricao + '\'' +
                ", dataRegistro=" + dataRegistro + '}';
    }
}
