package br.com.frota.model;

public class Contato extends GenericModel{
    private String telefone;
    private int laboratorioId;

    public Contato(int id,String telefone, int laboratorioId) {
        this.setId(id);
        this.telefone = telefone;
        this.laboratorioId = laboratorioId;
    }

    public Contato(String telefone, int laboratorioId) {
        super();
        this.telefone = telefone;
        this.laboratorioId = laboratorioId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(int laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "telefone='" + telefone + '\'' +
                ", laboratorio_id=" + laboratorioId +
                '}';
    }
}
