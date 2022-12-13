package br.com.frota.DAO;

import br.com.frota.model.Laudo;
import br.com.frota.model.MaterialExame;
import br.com.frota.model.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaudoDAO extends ConexaoDB{
    private static final String INSERT_LAUDO_SQL = "INSERT INTO laudo (assinatura_digital, dt_resultado, codigo, solicitacao_exame_id) VALUES (?, ?, ?, ?   );";
    private static final String SELECT_LAUDO_BY_ID = "SELECT id, assinatura_digital, dt_resultado, codigo, solicitacao_exame_id FROM laudo WHERE id = ?";
    private static final String SELECT_ALL_LAUDO = "SELECT * FROM laudo;";
    private static final String DELETE_LAUDO_SQL = "DELETE FROM laudo WHERE id = ?;";
    private static final String UPDATE_LAUDO_SQL = "UPDATE laudo SET assinatura_digital = ?, dt_resultado = ?, codigo = ?, solicitacao_exame_id = ? WHERE id = ?;";
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

    public void insertLaudo(Laudo entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_LAUDO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getAssinaturaDigital());
            preparedStatement.setTimestamp(2, new Timestamp(entidade.getDtResultado().getTime()));
            preparedStatement.setString(3, entidade.getCodigo());
            preparedStatement.setInt(4, entidade.getSolicitacaoExameId());

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

    public Laudo findById(int id) {
        Laudo entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_LAUDO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String assinaturaDigital = rs.getString("assinatura_digital");
                Date dtResultado = new Date(rs.getTimestamp("dt_resultado").getTime());
                String codigo = rs.getString("codigo");
                int solicitacaoExameId = rs.getInt("solicitacaoExameId");

                entidade = new Laudo(id, assinaturaDigital, dtResultado, codigo, solicitacaoExameId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Laudo> selectAllLaudo() {
        List<Laudo> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_LAUDO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String assinaturaDigital = rs.getString("assinatura_digital");
                Date dtResultado = new Date(rs.getTimestamp("dt_resultado").getTime());
                String codigo = rs.getString("codigo");
                int solicitacaoExameId = rs.getInt("solicitacaoExameId");

                entidades.add(new Laudo(id, assinaturaDigital, dtResultado, codigo,solicitacaoExameId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteLaudo(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_LAUDO_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteLaudo(Laudo entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_LAUDO_SQL)) {

            statement.setString(1, entidade.getAssinaturaDigital());
            statement.setTimestamp(2, new Timestamp(entidade.getDtResultado().getTime()));
            statement.setString(3, entidade.getCodigo());
            statement.setInt(4, entidade.getSolicitacaoExameId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
