package br.com.frota.DAO;

import br.com.frota.model.Exame;
import br.com.frota.model.ResultadoExame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultadoExameDAO extends ConexaoDB{
    private static final String INSERT_RESULTADO_EXAME_SQL = "INSERT INTO resultado_exame (dt_exame, valor, composicao_id, laudo_id) VALUES (?, ?, ?, ?);";
    private static final String SELECT_RESULTADO_EXAME_BY_ID = "SELECT id, dt_exame, valor, composicao_id, laudo_id FROM resultado_exame WHERE id = ?";
    private static final String SELECT_ALL_RESULTADO_EXAME = "SELECT * FROM resultado_exame;";
    private static final String DELETE_RESULTADO_EXAME_SQL = "DELETE FROM resultado_exame WHERE id = ?;";
    private static final String UPDATE_RESULTADO_EXAME_SQL = "UPDATE resultado_exame SET dt_exane = ?, valor = ?, composicao_id = ?, laudo_id = ? WHERE id = ?;";
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

    public void insertResultadoExame(ResultadoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_RESULTADO_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setTimestamp(1, new Timestamp(entidade.getDtExame().getTime()));
            preparedStatement.setString(2, entidade.getValor());
            preparedStatement.setInt(3, entidade.getComposicaoId());
            preparedStatement.setInt(4, entidade.getLaudoId());

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

    public ResultadoExame findById(int id) {
        ResultadoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_RESULTADO_EXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Date dtExame = new Date(rs.getTimestamp("dt_exame").getTime());
                String valor = rs.getString("valor");
                int composicaoId = rs.getInt("composicao_id");
                int laudoId = rs.getInt("laudo_id");
                entidade = new ResultadoExame(id, dtExame, valor, composicaoId, laudoId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ResultadoExame> selectAllResultadoExame() {
        List<ResultadoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_RESULTADO_EXAME)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date dtExame = new Date(rs.getTimestamp("dt_exame").getTime());
                String valor = rs.getString("valor");
                int composicaoId = rs.getInt("composicao_id");
                int laudoId = rs.getInt("laudo_id");
                entidades.add(new ResultadoExame(id, dtExame, valor, composicaoId, laudoId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean resultadoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_RESULTADO_EXAME_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateResultadoExame(ResultadoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_RESULTADO_EXAME_SQL)) {

            statement.setTimestamp(1, new Timestamp(entidade.getDtExame().getTime()));
            statement.setString(2, entidade.getValor());
            statement.setInt(3, entidade.getComposicaoId());
            statement.setInt(4, entidade.getLaudoId());
            statement.setInt(5, entidade.getId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
