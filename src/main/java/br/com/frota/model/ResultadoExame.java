package br.com.frota.model;

import java.util.Date;

public class ResultadoExame extends GenericModel{
    private Date dtExame;
    private String valor;
    private int composicaoId;
    private int laudoId;

    public ResultadoExame(int id, Date dtExame, String valor, int composicaoId, int laudoId) {
        this.setId(id);
        this.dtExame = dtExame;
        this.valor = valor;
        this.composicaoId = composicaoId;
        this.laudoId = laudoId;
    }

    public ResultadoExame(Date dtExame, String valor, int composicaoId, int laudoId) {
        super();
        this.dtExame = dtExame;
        this.valor = valor;
        this.composicaoId = composicaoId;
        this.laudoId = laudoId;
    }

    public Date getDtExame() {
        return dtExame;
    }

    public void setDtExame(Date dtExame) {
        this.dtExame = dtExame;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getComposicaoId() {
        return composicaoId;
    }

    public void setComposicaoId(int composicaoId) {
        this.composicaoId = composicaoId;
    }

    public int getLaudoId() {
        return laudoId;
    }

    public void setLaudoId(int laudoId) {
        this.laudoId = laudoId;
    }

    @Override
    public String toString() {
        return "Resultado_exame{" +
                "dt_exame=" + dtExame +
                ", valor=" + valor +
                ", composicao_id=" + composicaoId +
                ", laudo_id=" + laudoId +
                '}';
    }
}
