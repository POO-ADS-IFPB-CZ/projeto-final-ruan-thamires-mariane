package model;
import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable {
    private int identificador;
    private Date dataRealizacao;
    private int quantidadeItens;
    private double valorTotal;

    public Pedido(int identificador, Date dataRealizacao, int quantidadeItens, double valorTotal) {
        this.identificador = identificador;
        this.dataRealizacao = dataRealizacao;
        this.quantidadeItens = quantidadeItens;
        this.valorTotal = valorTotal;
    }

    public int getIdentificador() { return identificador; }
    public void setIdentificador(int identificador) { this.identificador = identificador; }
    public Date getDataRealizacao() { return dataRealizacao; }
    public void setDataRealizacao(Date dataRealizacao) { this.dataRealizacao = dataRealizacao; }
    public int getQuantidadeItens() { return quantidadeItens; }
    public void setQuantidadeItens(int quantidadeItens) { this.quantidadeItens = quantidadeItens; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return "Pedido{" + "identificador=" + identificador + ", dataRealizacao=" + dataRealizacao + ", quantidadeItens=" + quantidadeItens + ", valorTotal=" + valorTotal + '}';
    }
}
