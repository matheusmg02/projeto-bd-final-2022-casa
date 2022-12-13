package br.com.frota.model;

public class ResponsavelTecnicoHasLaboratorio extends GenericModel{
    private int responsavelTecnicoId;
    private int laboratorioId;

    public ResponsavelTecnicoHasLaboratorio(int id, int responsavelTecnicoId, int laboratorioId) {
        this.setId(id);
        this.responsavelTecnicoId = responsavelTecnicoId;
        this.laboratorioId = laboratorioId;
    }

    public ResponsavelTecnicoHasLaboratorio(int responsavelTecnicoId, int laboratorioId) {
        super();
        this.responsavelTecnicoId = responsavelTecnicoId;
        this.laboratorioId = laboratorioId;
    }

    public int getResponsavelTecnicoId() {
        return responsavelTecnicoId;
    }

    public void setResponsavelTecnicoId(int responsavelTecnicoId) {
        this.responsavelTecnicoId = responsavelTecnicoId;
    }

    public int getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(int laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    @Override
    public String toString() {
        return "Responsavel_tecnico_has_laboratorio{" +
                "responsavel_tecnico_id=" + responsavelTecnicoId +
                ", laboratorio_id=" + laboratorioId +
                '}';
    }
}
