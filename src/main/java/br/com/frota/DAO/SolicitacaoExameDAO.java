package br.com.frota.DAO;

import br.com.frota.model.Paciente;
import br.com.frota.model.SolicitacaoExame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitacaoExameDAO extends ConexaoDB{
    private static final String INSERT_SOLICITACAO_EXAME_SQL = "INSERT INTO solicitacao_exame (nm_prescrito, consulta_medica_id, dt_solicitacao, habilitacao_exame_id, exame_id) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_SOLICITACAO_EXAME_BY_ID = "SELECT id, nm_prescrito, consulta_medica_id FROM solicitacao_exame WHERE id = ?";
    private static final String SELECT_ALL_SOLICITACAO_EXAME = "SELECT * FROM solicitacao_exame;";
    private static final String DELETE_SOLICITACAO_EXAME_SQL = "DELETE FROM solicitacao_exame WHERE id = ?;";
    private static final String UPDATE_SOLICITACAO_EXAME_SQL = "UPDATE solicitacao_exame SET nome = ?, dt_nascimento = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM solicitacao_exame;";

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

    public void insertSolicitacaoExame(SolicitacaoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_SOLICITACAO_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getNomePrescrito());
            preparedStatement.setInt(2, entidade.getConsultaMedicaId());
            preparedStatement.setTimestamp(3, new Timestamp(entidade.getDtSolicitacao().getTime()));
            preparedStatement.setInt(4, entidade.getHabilitacaoExameId());
            preparedStatement.setInt(5, entidade.getExameId());

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

    public SolicitacaoExame findById(int id) {
        SolicitacaoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_SOLICITACAO_EXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nomePrescrito = rs.getString("nm_prescrito");
                int consultaMedicaId = rs.getInt("consulta_medica_id");
                Date dtSolicitacao = new Date(rs.getTimestamp("dt_solicitacao").getTime());
                int habilitacaoExameId = rs.getInt("habilitacao_exame_id");
                int exameId = rs.getInt("exame_id");
                entidade = new SolicitacaoExame(id, nomePrescrito, consultaMedicaId, dtSolicitacao, habilitacaoExameId, exameId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<SolicitacaoExame> selectAllPaciente() {
        List<SolicitacaoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_SOLICITACAO_EXAME)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomePrescrito = rs.getString("nm_prescrito");
                int consultaMedicaId = rs.getInt("consulta_medica_id");
                Date dtSolicitacao = new Date(rs.getTimestamp("dt_solicitacao").getTime());
                int habilitacaoExameId = rs.getInt("habilitacao_exame_id");
                int exameId = rs.getInt("exame_id");

                entidades.add(new SolicitacaoExame(id, nomePrescrito, consultaMedicaId, dtSolicitacao, habilitacaoExameId, exameId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteSolicitacaoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_SOLICITACAO_EXAME_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateSolicitacaoExame(SolicitacaoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_SOLICITACAO_EXAME_SQL)) {

            statement.setString(1, entidade.getNomePrescrito());
            statement.setInt(2, entidade.getConsultaMedicaId());
            statement.setTimestamp(3, new Timestamp(entidade.getDtSolicitacao().getTime()));
            statement.setInt(2, entidade.getHabilitacaoExameId());
            statement.setInt(2, entidade.getExameId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
