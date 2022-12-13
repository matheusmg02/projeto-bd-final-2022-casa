package br.com.frota.model;

public class GenericModel {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GenericModel{" +
                "id=" + id +
                '}';
    }
}
