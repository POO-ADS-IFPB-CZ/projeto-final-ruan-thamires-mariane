package model;

import java.io.Serializable;
import java.util.Date;

public class Unidade implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoBarras;
    private int id;
    private Date dataValidade;

    public Unidade(String codigoBarras, int id, Date dataValidade) {
        this.codigoBarras = codigoBarras;
        this.id = id;
        this.dataValidade = dataValidade;
    }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDataValidade() { return dataValidade; }
    public void setDataValidade(Date dataValidade) { this.dataValidade = dataValidade; }
}
