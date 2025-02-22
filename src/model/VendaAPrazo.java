package model;

import java.util.Date;

public class VendaAPrazo extends Venda {
    private static final long serialVersionUID = 1L;

    private double acrescimoNoValor;
    private int numeroDeParcelas;

    public VendaAPrazo(int codigo, Date data, double valorTotal, double acrescimoNoValor, int numeroDeParcelas) {
        super(codigo, data, valorTotal);
        this.acrescimoNoValor = acrescimoNoValor;
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public double getAcrescimoNoValor() { return acrescimoNoValor; }
    public void setAcrescimoNoValor(double acrescimoNoValor) { this.acrescimoNoValor = acrescimoNoValor; }

    public int getNumeroDeParcelas() { return numeroDeParcelas; }
    public void setNumeroDeParcelas(int numeroDeParcelas) { this.numeroDeParcelas = numeroDeParcelas; }
}
