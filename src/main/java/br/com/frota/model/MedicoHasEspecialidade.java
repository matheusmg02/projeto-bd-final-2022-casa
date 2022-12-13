package br.com.frota.model;

public class MedicoHasEspecialidade extends GenericModel{
    private int medicoId;
    private int especialidadeId;

    public MedicoHasEspecialidade(int id, int medicoId, int especialidadeId) {
        this.setId(id);
        this.medicoId = medicoId;
        this.especialidadeId = especialidadeId;
    }

    public MedicoHasEspecialidade(int medicoId, int especialidadeId) {
        super();
        this.medicoId = medicoId;
        this.especialidadeId = especialidadeId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(int especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    @Override
    public String toString() {
        return "Medico_has_especialidade{" +
                "medico_id=" + medicoId +
                ", especialidade_id=" + especialidadeId +
                '}';
    }
}
