package br.com.frota.DAO;

import br.com.frota.model.Paciente;
import br.com.frota.model.SiglaFormacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SiglaFormacaoDAO extends ConexaoDB{
    private static final String INSERT_SIGLA_FORMACAO_SQL = "INSERT INTO sigla_formacao (sigla) VALUES (?);";
    private static final String SELECT_SIGLA_FORMACAO_BY_ID = "SELECT sigla FROM sigla_formacao WHERE id = ?";
    private static final String SELECT_ALL_SIGLA_FORMACAO = "SELECT * FROM sigla_formacao;";
    private static final String DELETE_SIGLA_FORMACAO_SQL = "DELETE FROM sigla_formacao WHERE id = ?;";
    private static final String UPDATE_SIGLA_FORMACAO_SQL = "UPDATE sigla_formacao SET nome = ?, dt_nascimento = ? WHERE id = ?;";
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

    public void insertSiglaFormacao(SiglaFormacao entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_SIGLA_FORMACAO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getSigla());

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

    public SiglaFormacao findById(int id) {
        SiglaFormacao entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_SIGLA_FORMACAO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String sigla = rs.getString("sigla");


                entidade = new SiglaFormacao(id, sigla);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<SiglaFormacao> selectAllSiglaFormacao() {
        List<SiglaFormacao> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_SIGLA_FORMACAO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String siglaFormacao = rs.getString("sigla_formacao");

                entidades.add(new SiglaFormacao(id, siglaFormacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteSiglaFormacao(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_SIGLA_FORMACAO_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateSiglaFormacao(SiglaFormacao entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_SIGLA_FORMACAO_SQL)) {

            statement.setString(1, entidade.getSigla());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
