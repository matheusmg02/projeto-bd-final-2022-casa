package br.com.frota.model;

import java.util.Date;

public class Laudo extends GenericModel{
    private String assinaturaDigital;
    private Date dtResultado;
    private String codigo;
    private int solicitacaoExameId;

    public Laudo(int id, String assinaturaDigital, Date dtResultado, String codigo, int solicitacaoExameId) {
        this.setId(id);
        this.assinaturaDigital = assinaturaDigital;
        this.dtResultado = dtResultado;
        this.codigo = codigo;
        this.solicitacaoExameId = solicitacaoExameId;
    }

    public Laudo(String assinaturaDigital, Date dtResultado, String codigo, int solicitacaoExameId) {
        super();
        this.assinaturaDigital = assinaturaDigital;
        this.dtResultado = dtResultado;
        this.codigo = codigo;
        this.solicitacaoExameId = solicitacaoExameId;
    }

    public String getAssinaturaDigital() {
        return assinaturaDigital;
    }

    public void setAssinaturaDigital(String assinaturaDigital) {
        this.assinaturaDigital = assinaturaDigital;
    }

    public Date getDtResultado() {
        return dtResultado;
    }

    public void setDtResultado(Date dtResultado) {
        this.dtResultado = dtResultado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getSolicitacaoExameId() {
        return solicitacaoExameId;
    }

    public void setSolicitacaoExameId(int solicitacaoExameId) {
        this.solicitacaoExameId = solicitacaoExameId;
    }

    @Override
    public String toString() {
        return "Laudo{" +
                "assinatura_digital='" + assinaturaDigital + '\'' +
                ", dt_resultado=" + dtResultado +
                ", codigo='" + codigo + '\'' +
                ", solicitacao_exame_id=" + solicitacaoExameId +
                '}';
    }
}
