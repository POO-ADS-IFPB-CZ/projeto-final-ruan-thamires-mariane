package model;
import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;

    private Cliente cliente;
    private int codVenda;
    private double valor;
    private String descricao;
    private Date dataRegistro;

    public Venda(Cliente cliente, int codVenda, double valor, String descricao, Date dataRegistro) {
        this.cliente = cliente;
        this.codVenda = codVenda;
        this.valor = valor;
        this.descricao = descricao;
        this.dataRegistro = (dataRegistro != null) ? dataRegistro : new Date(); // Se null, usa a data atual
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public int getCodVenda() { return codVenda; }
    public void setCodVenda(int codVenda) { this.codVenda = codVenda; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Date getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(Date dataRegistro) { this.dataRegistro = dataRegistro; }

    @Override
    public String toString() {
        return "Venda{" + "codVenda=" + codVenda + ", cliente=" + cliente.getNome() +
                ", valor=" + valor + ", descricao='" + descricao + '\'' +
                ", dataRegistro=" + dataRegistro + '}';
    }
}
