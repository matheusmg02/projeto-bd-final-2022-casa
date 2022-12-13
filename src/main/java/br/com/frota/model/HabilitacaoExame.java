package br.com.frota.model;

public class HabilitacaoExame extends GenericModel{
    private String observacao;
    private Double custo;
    private int laboratorioId;
    private int tipoExameId;

    public HabilitacaoExame(int id, String observacao, Double custo, int laboratorioId, int tipoExameId) {
        this.setId(id);
        this.observacao = observacao;
        this.custo = custo;
        this.laboratorioId = laboratorioId;
        this.tipoExameId = tipoExameId;
    }

    public HabilitacaoExame(String observacao, Double custo, int laboratorioId, int tipoExameId) {
        super();
        this.observacao = observacao;
        this.custo = custo;
        this.laboratorioId = laboratorioId;
        this.tipoExameId = tipoExameId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public int getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(int laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public int getTipoExameId() {
        return tipoExameId;
    }

    public void setTipoExameId(int tipoExameId) {
        this.tipoExameId = tipoExameId;
    }

    @Override
    public String toString() {
        return "Habilitacao_exame{" +
                "observacao='" + observacao + '\'' +
                ", custo=" + custo +
                ", laboratorio_id=" + laboratorioId +
                ", tipo_exame_id=" + tipoExameId +
                '}';
    }
}
