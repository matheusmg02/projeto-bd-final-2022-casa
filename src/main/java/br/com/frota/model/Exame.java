package br.com.frota.model;

public class Exame extends GenericModel{
    private int tipoExameId;
    private String descricao;
    private int materialExameId;
    private String metodo;

    public Exame(int id, int tipoExameId, String descricao, int materialExameId, String metodo) {
        this.setId(id);
        this.tipoExameId = tipoExameId;
        this.descricao = descricao;
        this.materialExameId = materialExameId;
        this.metodo = metodo;
    }

    public Exame(int tipoExameId, String descricao, int materialExameId, String metodo) {
        super();
        this.tipoExameId = tipoExameId;
        this.descricao = descricao;
        this.materialExameId = materialExameId;
        this.metodo = metodo;
    }

    public int getTipoExameId() {
        return tipoExameId;
    }

    public void setTipoExameId(int tipoExameId) {
        this.tipoExameId = tipoExameId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getMaterialExameId() {
        return materialExameId;
    }

    public void setMaterialExameId(int materialExameId) {
        this.materialExameId = materialExameId;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "tipo_exame_id=" + tipoExameId +
                ", descricao='" + descricao + '\'' +
                ", material_exame_id=" + materialExameId +
                ", metodo='" + metodo + '\'' +
                '}';
    }
}
