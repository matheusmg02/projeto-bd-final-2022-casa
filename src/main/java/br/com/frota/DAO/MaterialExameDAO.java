package br.com.frota.DAO;

import br.com.frota.model.HabilitacaoExame;
import br.com.frota.model.Laboratorio;
import br.com.frota.model.MaterialExame;
import br.com.frota.model.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialExameDAO extends ConexaoDB{
    private static final String INSERT_MATERIAL_EXAME_SQL = "INSERT INTO material_exame (material, observacacao) VALUES (?, ?);";
    private static final String SELECT_MATERIAL_EXAME_BY_ID = "SELECT id, material, observacacao FROM material_exame WHERE id = ?";
    private static final String SELECT_ALL_MATERIAL_EXAME = "SELECT * FROM material_exame;";
    private static final String DELETE_MATERIAL_EXAME_SQL = "DELETE FROM material_exame WHERE id = ?;";
    private static final String UPDATE_MATERIAL_EXAME_SQL = "UPDATE material_exame SET tipo_exame_id = ?, descricao = ?, material_exame_id = ?, metodo = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM especialidade;";

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

    public void insertMaterialExame(MaterialExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MATERIAL_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getMaterial());
            preparedStatement.setString(2, entidade.getObservacao());

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

    public Medico findById(int id) {
        Medico entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MATERIAL_EXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");

                entidade = new Medico(id, crm, nome);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Medico> selectAllMaterialExame() {
        List<Medico> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MATERIAL_EXAME)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");


                entidades.add(new Medico(id, crm, nome));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedico(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MATERIAL_EXAME_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMedico(Medico entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MATERIAL_EXAME_SQL)) {

            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getCrm());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
