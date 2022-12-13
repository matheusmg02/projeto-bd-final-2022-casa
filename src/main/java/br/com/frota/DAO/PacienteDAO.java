package br.com.frota.DAO;

import br.com.frota.model.MaterialExame;
import br.com.frota.model.Medico;
import br.com.frota.model.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteDAO extends ConexaoDB{
    private static final String INSERT_PACIENTE_SQL = "INSERT INTO paciente (nome, dt_nascimento) VALUES (?, ?);";
    private static final String SELECT_PACIENTE_BY_ID = "SELECT id, nome, dt_nascimento FROM paciente WHERE id = ?";
    private static final String SELECT_ALL_PACIENTE = "SELECT * FROM paciente;";
    private static final String DELETE_PACIENTE_SQL = "DELETE FROM paciente WHERE id = ?;";
    private static final String UPDATE_PACIENTE_SQL = "UPDATE paciente SET nome = ?, dt_nascimento = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM paciente;";

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

    public void insertMedico(Paciente entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_PACIENTE_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setTimestamp(2, new Timestamp(entidade.getDtNascimento().getTime()));

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

    public Paciente findById(int id) {
        Paciente entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_PACIENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                Date dtNascimento = new Date(rs.getTimestamp("dt_nascimento").getTime());

                entidade = new Paciente(id, nome, dtNascimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Paciente> selectAllPaciente() {
        List<Paciente> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_PACIENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("material");
                Date dtConsulta = new Date(rs.getTimestamp("dt_consulta").getTime());

                entidades.add(new Paciente(id, nome, dtConsulta));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deletePaciente(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_PACIENTE_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updatePaciente(Paciente entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_PACIENTE_SQL)) {

            statement.setString(1, entidade.getNome());
            statement.setTimestamp(1, new Timestamp(entidade.getDtNascimento().getTime()));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
