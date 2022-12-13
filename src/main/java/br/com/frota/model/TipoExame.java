package br.com.frota.model;

public class TipoExame extends GenericModel{
    private String descricao;
    private String observacao;

    public TipoExame(int id, String descricao, String observacao) {
        this.setId(id);
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Tipo_exame{" +
                "descricao='" + descricao + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
