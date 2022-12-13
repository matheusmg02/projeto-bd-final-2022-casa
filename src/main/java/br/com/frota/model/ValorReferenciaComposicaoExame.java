package br.com.frota.model;

public class ValorReferenciaComposicaoExame extends GenericModel{
    private String valorMinimo;
    private String valorMaximo;
    private String limitadorMinimo;
    private String limitadorMaximo;
    private int unidadeMedidaId;

    public ValorReferenciaComposicaoExame(int id, String valorMinimo, String valorMaximo, String limitadorMinimo, String limitadorMaximo, int unidadeMedidaId) {
        this.setId(id);
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.limitadorMinimo = limitadorMinimo;
        this.limitadorMaximo = limitadorMaximo;
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public ValorReferenciaComposicaoExame(String valorMinimo, String valorMaximo, String limitadorMinimo, String limitadorMaximo, int unidadeMedidaId) {
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.limitadorMinimo = limitadorMinimo;
        this.limitadorMaximo = limitadorMaximo;
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public String getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(String valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(String valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public String getLimitadorMinimo() {
        return limitadorMinimo;
    }

    public void setLimitadorMinimo(String limitadorMinimo) {
        this.limitadorMinimo = limitadorMinimo;
    }

    public String getLimitadorMaximo() {
        return limitadorMaximo;
    }

    public void setLimitadorMaximo(String limitadorMaximo) {
        this.limitadorMaximo = limitadorMaximo;
    }

    public int getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    public void setUnidadeMedidaId(int unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    @Override
    public String toString() {
        return "Valor_referencia_composicao_exame{" +
                "valor_minimo='" + valorMinimo + '\'' +
                ", valor_maximo='" + valorMaximo + '\'' +
                ", limitador_minimo='" + limitadorMinimo + '\'' +
                ", limitador_maximo='" + limitadorMaximo + '\'' +
                ", unidade_medida_id=" + unidadeMedidaId +
                '}';
    }
}
