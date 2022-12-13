package br.com.frota.model;

public class Composicao extends GenericModel{
    private String exameId;
    private String composicaoExameId;
    private String valorReferenciaComposicaoExameId;

    public Composicao(int id, String exameId, String composicaoExameId, String valorReferenciaComposicaoExameId) {
        this.setId(id);
        this.exameId = exameId;
        this.composicaoExameId = composicaoExameId;
        this.valorReferenciaComposicaoExameId = valorReferenciaComposicaoExameId;
    }

    public Composicao(String exameId, String composicaoExameId, String valorReferenciaComposicaoExameId) {
        super();
        this.exameId = exameId;
        this.composicaoExameId = composicaoExameId;
        this.valorReferenciaComposicaoExameId = valorReferenciaComposicaoExameId;
    }

    public String getExameId() {
        return exameId;
    }

    public void setExameId(String exameId) {
        this.exameId = exameId;
    }

    public String getComposicaoExameId() {
        return composicaoExameId;
    }

    public void setComposicaoExameId(String composicaoExameId) {
        this.composicaoExameId = composicaoExameId;
    }

    public String getValorReferenciaComposicaoExameId() {
        return valorReferenciaComposicaoExameId;
    }

    public void setValorReferenciaComposicaoExameId(String valorReferenciaComposicaoExameId) {
        this.valorReferenciaComposicaoExameId = valorReferenciaComposicaoExameId;
    }

    @Override
    public String toString() {
        return "Composicao [exame_id=" + exameId + ", composicao_exame_id=" + composicaoExameId + ", valor_referencia_composicao_exame_id=" + valorReferenciaComposicaoExameId;
    }
}