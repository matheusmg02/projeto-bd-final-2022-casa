package br.com.frota.model;

public class Medico extends GenericModel{
    private String crm;
    private String nome;

    public Medico(int id, String crm, String nome) {
        this.setId(id);
        this.crm = crm;
        this.nome = nome;
    }

    public Medico(String crm, String nome) {
        super();
        this.crm = crm;
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", pessoa='" + nome + '\'' +
                '}';
    }
}
