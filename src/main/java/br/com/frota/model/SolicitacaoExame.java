package br.com.frota.model;

import java.util.Date;

public class SolicitacaoExame extends GenericModel{
    private String nomePrescrito;
    private int consultaMedicaId;
    private Date dtSolicitacao;
    private int habilitacaoExameId;
    private int exameId;

    public SolicitacaoExame(int id, String nomePrescrito, int consultaMedicaId, Date dtSolicitacao, int habilitacaoExameId, int exameId) {
        this.setId(id);
        this.nomePrescrito = nomePrescrito;
        this.consultaMedicaId = consultaMedicaId;
        this.dtSolicitacao = dtSolicitacao;
        this.habilitacaoExameId = habilitacaoExameId;
        this.exameId = exameId;
    }

    public SolicitacaoExame(String nomePrescrito, int consultaMedicaId, Date dtSolicitacao, int habilitacaoExameId, int exameId) {
        super();
        this.nomePrescrito = nomePrescrito;
        this.consultaMedicaId = consultaMedicaId;
        this.dtSolicitacao = dtSolicitacao;
        this.habilitacaoExameId = habilitacaoExameId;
        this.exameId = exameId;
    }

    public String getNomePrescrito() {
        return nomePrescrito;
    }

    public void setNomePrescrito(String nomePrescrito) {
        this.nomePrescrito = nomePrescrito;
    }

    public int getConsultaMedicaId() {
        return consultaMedicaId;
    }

    public void setConsultaMedicaId(int consultaMedicaId) {
        this.consultaMedicaId = consultaMedicaId;
    }

    public Date getDtSolicitacao() {
        return dtSolicitacao;
    }

    public void setDtSolicitacao(Date dtSolicitacao) {
        this.dtSolicitacao = dtSolicitacao;
    }

    public int getHabilitacaoExameId() {
        return habilitacaoExameId;
    }

    public void setHabilitacaoExameId(int habilitacaoExameId) {
        this.habilitacaoExameId = habilitacaoExameId;
    }

    public int getExameId() {
        return exameId;
    }

    public void setExameId(int exameId) {
        this.exameId = exameId;
    }

    @Override
    public String toString() {
        return "Solicitacao_exame{" +
                "nome_prescrito='" + nomePrescrito + '\'' +
                ", consulta_medica_id=" + consultaMedicaId +
                ", dt_solicitacao=" + dtSolicitacao +
                ", habilitacao_exame_id=" + habilitacaoExameId +
                ", exame_id=" + exameId +
                '}';
    }
}
