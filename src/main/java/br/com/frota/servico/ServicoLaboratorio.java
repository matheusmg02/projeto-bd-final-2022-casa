package br.com.frota.servico;

import br.com.frota.DAO.LaboratorioDAO;
import br.com.frota.model.Laboratorio;

import java.sql.SQLException;

public class ServicoLaboratorio {
    private LaboratorioDAO laboratorio = new LaboratorioDAO();
    public void salvar(Laboratorio entidade) {
        laboratorio.insertLaboratorio(entidade);
    }

    public Laboratorio buscarPorId(int id) {
        return laboratorio.findById(id);
    }

    public void update(Laboratorio lab) throws SQLException {
        laboratorio.updateLaboratorio(lab);
    }

    public void remover(int id) throws SQLException {
        laboratorio.deleteLaboratorio(id);
    }
}
