package br.com.frota.DAO;

import br.com.frota.model.MaterialExame;
import br.com.frota.model.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends ConexaoDB{
    private static final String INSERT_MEDICO_SQL = "INSERT INTO medico (nome, crm) VALUES (?, ?);";
    private static final String SELECT_MEDICO_BY_ID = "SELECT id, nome, crm FROM medico WHERE id = ?";
    private static final String SELECT_ALL_MEDICO = "SELECT * FROM medico;";
    private static final String DELETE_MEDICO_SQL = "DELETE FROM medico WHERE id = ?;";
    private static final String UPDATE_MEDICO_SQL = "UPDATE material_exame SET nome = ?, crm = ? WHERE id = ?;";
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

    public void insertMedico(Medico entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICO_SQL,
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

    public Medico findById(int id) {
        Medico entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");

                entidade = new Medico(id, nome, crm);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<MaterialExame> selectAllMedico() {
        List<MaterialExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");


                entidades.add(new MaterialExame(id, nome, crm));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedico(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDICO_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMedico(Medico entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MEDICO_SQL)) {

            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getCrm());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
