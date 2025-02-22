package model;
import java.io.Serializable;
import java.util.Date;

class RegistroFinanceiro implements Serializable {
    private int codigo;
    private double valor;
    private String descricao;
    private Date dataRegistro;

    public RegistroFinanceiro(int codigo, double valor, String descricao, Date dataRegistro) {
        this.codigo = codigo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(Date dataRegistro) { this.dataRegistro = dataRegistro; }

    @Override
    public String toString() {
        return "RegistroFinanceiro{" + "codigo=" + codigo + ", valor=" + valor + ", descricao='" + descricao + '\'' + ", dataRegistro=" + dataRegistro + '}';
    }
}
