package br.com.frota.DAO;

import br.com.frota.model.MaterialExame;
import br.com.frota.model.Medico;
import br.com.frota.model.ResponsavelTecnico;
import br.com.frota.model.ResponsavelTecnicoHasLaboratorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelTecnicoHasLaboratorioDAO extends ConexaoDB{
    private static final String INSERT_RESPONSAVEL_TECNICO_HAS_LABORATORIO_SQL = "INSERT INTO responsavel_tecnico_has_laboratorio (responsavel_tecnico_id, laboratorio_id) VALUES (?, ?);";
    private static final String SELECT_RESPONSAVEL_TECNICO_HAS_LABORATORIO_BY_ID = "SELECT id, responsavel_tecnico_id, laboratorio_id FROM medico WHERE laboratorio_id = ?";
    private static final String SELECT_ALL_RESPONSAVEL_TECNICO_HAS_LABORATORIO = "SELECT * FROM responsavel_tecnico_id;";
    private static final String DELETE_RESPONSAVEL_TECNICO_HAS_LABORATORIO_SQL = "DELETE FROM responsavel_tecnico_id WHERE id = ?;";
    private static final String UPDATE_RESPONSAVEL_TECNICO_HAS_LABORATORIO_SQL = "UPDATE responsavel_tecnico_id SET responsavel_tecnico_id = ?, laboratorio_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM medico;";

    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public void insertResponsavelTecnicoHasLaboratorio(ResponsavelTecnicoHasLaboratorio entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_RESPONSAVEL_TECNICO_HAS_LABORATORIO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getCrm());

            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                entidade.setId(result.getInt(1));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public MaterialExame findById(int id) {
        MaterialExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_RESPONSAVEL_TECNICO_HAS_LABORATORIO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String material = rs.getString("material");
                String observacao = rs.getString("observacacao");

                entidade = new MaterialExame(id, material, observacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<MaterialExame> selectAllMaterialExame() {
        List<MaterialExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_RESPONSAVEL_TECNICO_HAS_LABORATORIO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String material = rs.getString("material");
                String observacao = rs.getString("observacacao");


                entidades.add(new MaterialExame(id, material, observacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteLaboratorio(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_RESPONSAVEL_TECNICO_HAS_LABORATORIO_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMaterialExame(MaterialExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_RESPONSAVEL_TECNICO_HAS_LABORATORIO_SQL)) {

            statement.setString(1, entidade.getMaterial());
            statement.setString(2, entidade.getObservacao());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
