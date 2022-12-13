package br.com.frota.DAO;

import br.com.frota.model.SiglaFormacao;
import br.com.frota.model.UnidadeMedida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidaDAO extends ConexaoDB{
    private static final String INSERT_UNIDADE_MEDIDA_SQL = "INSERT INTO unidade_medida (descricao) VALUES (?);";
    private static final String SELECT_UNIDADE_MEDIDA_BY_ID = "SELECT descricao FROM unidade_medida WHERE id = ?";
    private static final String SELECT_ALL_UNIDADE_MEDIDA = "SELECT * FROM unidade_medida;";
    private static final String DELETE_SIGLA_UNIDADE_MEDIDA_SQL = "DELETE FROM unidade_medida WHERE id = ?;";
    private static final String UPDATE_SIGLA_UNIDADE_MEDIDA_SQL = "UPDATE unidade_medida SET descricao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM unidade_medida;";

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

    public void insertUnidadeMedida(UnidadeMedida entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_UNIDADE_MEDIDA_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getDescricao());

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

    public UnidadeMedida findById(int id) {
        UnidadeMedida entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_UNIDADE_MEDIDA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");

                entidade = new UnidadeMedida(id, descricao);
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
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_UNIDADE_MEDIDA)) {
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
        try (PreparedStatement statement = prepararSQL(DELETE_SIGLA_UNIDADE_MEDIDA_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateSiglaFormacao(SiglaFormacao entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_SIGLA_UNIDADE_MEDIDA_SQL)) {

            statement.setString(1, entidade.getSigla());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
