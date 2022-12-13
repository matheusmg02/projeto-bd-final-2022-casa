package br.com.frota.model;

public class UnidadeMedida extends GenericModel{
    private String descricao;

    private int unidadeMedidaId;

    public UnidadeMedida(int id, String descricao, int unidadeMedidaId) {
        this.setId(id);
        this.descricao = descricao;
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public UnidadeMedida(String descricao, int unidadeMedidaId) {
        super();
        this.descricao = descricao;
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    public void setUnidadeMedidaId(int unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    @Override
    public String toString() {
        return "UnidadeMedida{" +
                "descricao='" + descricao + '\'' +
                ", unidadeMedidaId=" + unidadeMedidaId +
                '}';
    }
}