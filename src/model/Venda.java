package model;

import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private Date data;
    private double valorTotal;

    public Venda(int codigo, Date data, double valorTotal) {
        this.codigo = codigo;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
}
