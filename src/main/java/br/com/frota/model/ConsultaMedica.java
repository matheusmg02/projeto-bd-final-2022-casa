package br.com.frota.model;

import java.util.Date;

public class ConsultaMedica extends GenericModel{
    private int medicoId;
    private int pacienteId;

    private Date dtConsulta;
    private String nomeAtendimento;

    public ConsultaMedica(int id, int medicoId, int pacienteId, Date dtConsulta, String nomeAtendimento) {
        this.setId(id);
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.dtConsulta = dtConsulta;
        this.nomeAtendimento = nomeAtendimento;
    }

    public ConsultaMedica(int medicoId, int pacienteId, Date dtConsulta, String nomeAtendimento) {
        super();
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.dtConsulta = dtConsulta;
        this.nomeAtendimento = nomeAtendimento;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Date getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(Date dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    public String getNomeAtendimento() {
        return nomeAtendimento;
    }

    public void setNomeAtendimento(String nomeAtendimento) {
        this.nomeAtendimento = nomeAtendimento;
    }

    @Override
    public String toString() {
        return "Consulta_medica{" +
                "medico_id=" + medicoId +
                ", paciente_id=" + pacienteId +
                ", dt_consulta=" + dtConsulta +
                ", nome_atendimento='" + nomeAtendimento + '\'' +
                '}';
    }
}
