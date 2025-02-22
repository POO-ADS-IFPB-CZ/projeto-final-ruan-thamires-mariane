package model;
import java.io.Serializable;
import java.util.Date;

public class Parcela implements Serializable {
    private int codigo;
    private boolean status;
    private Date diaPagamento;
    private double valorParcela;

    public Parcela(int codigo, boolean status, Date diaPagamento, double valorParcela) {
        this.codigo = codigo;
        this.status = status;
        this.diaPagamento = diaPagamento;
        this.valorParcela = valorParcela;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public Date getDiaPagamento() { return diaPagamento; }
    public void setDiaPagamento(Date diaPagamento) { this.diaPagamento = diaPagamento; }
    public double getValorParcela() { return valorParcela; }
    public void setValorParcela(double valorParcela) { this.valorParcela = valorParcela; }

    @Override
    public String toString() {
        return "Parcela{" + "codigo=" + codigo + ", status=" + status + ", diaPagamento=" + diaPagamento + ", valorParcela=" + valorParcela + '}';
    }
}
