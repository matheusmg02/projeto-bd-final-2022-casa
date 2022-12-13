package br.com.frota.DAO;

import br.com.frota.model.SiglaFormacao;
import br.com.frota.model.TipoExame;

import java.awt.image.TileObserver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoExameDAO extends ConexaoDB{
    private static final String INSERT_TIPO_EXAME_SQL = "INSERT INTO tipo_exame (descricao, observacacao) VALUES (?);";
    private static final String SELECT_TIPO_EXAME_BY_ID = "SELECT descricao, observacacao FROM tipo_exame WHERE id = ?";
    private static final String SELECT_ALL_TIPO_EXAME = "SELECT * FROM tipo_exame;";
    private static final String DELETE_TIPO_EXAME_SQL = "DELETE FROM tipo_exame WHERE id = ?;";
    private static final String UPDATE_TIPO_EXAME_SQL = "UPDATE tipo_exame SET nome = ?, dt_nascimento = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tipo_exame;";

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

    public void insertTipoExame(TipoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIPO_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getDescricao());
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

    public TipoExame findById(int id) {
        TipoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIPO_EXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String observacao = rs.getString("observacacao");


                entidade = new TipoExame(id, descricao, observacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<TipoExame> selectAllTipoExame() {
        List<TipoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIPO_EXAME)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                String observacao = rs.getString("observacacao");

                entidades.add(new TipoExame(id, descricao, observacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTipoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_TIPO_EXAME_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTipoExame(TipoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_TIPO_EXAME_SQL)) {

            statement.setString(1, entidade.getDescricao());
            statement.setString(2, entidade.getObservacao());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
