package br.com.frota.DAO;

import br.com.frota.model.Composicao;
import br.com.frota.model.ComposicaoExame;
import br.com.frota.model.ConsultaMedica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaMedicaDAO extends ConexaoDB{
    private static final String INSERT_CONSULTA_MEDICA_SQL = "INSERT INTO consulta_medica (dt_consulta, medico_id, paciente_id, nm_atendimento) VALUES (?, ?, ?, ?);";
    private static final String SELECT_CONSULTA_MEDICA_BY_ID = "SELECT id, dt_consulta, medico_id, paciente_id, nm_atendimento FROM composicao WHERE id = ?";
    private static final String SELECT_ALL_CONSULTA_MEDICA = "SELECT * FROM consulta_medica;";
    private static final String DELETE_CONSULTA_MEDICA_SQL = "DELETE FROM consulta_medica WHERE id = ?;";
    // private static final String BUSCAR_POR_DESCRICAO_COMPOSICAO_SQL = "DELETE FROM composicao WHERE descricao = ?;";
    private static final String UPDATE_CONSULTA_MEDICA_SQL = "UPDATE consulta_medica SET dt_consulta = ?, medico_id = ? paciente_id = ? nm_atendimento = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM consulta_medica;";

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

    public void insertConsultaMedica(ConsultaMedica entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CONSULTA_MEDICA_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setTimestamp(1, new Timestamp(entidade.getDtConsulta().getTime()));
            preparedStatement.setInt(2, entidade.getMedicoId());
            preparedStatement.setInt(3, entidade.getPacienteId());
            preparedStatement.setString(4, entidade.getNomeAtendimento());


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

    public ConsultaMedica findById(int id) {
        ConsultaMedica entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CONSULTA_MEDICA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Date dtConsulta = new Date(rs.getTimestamp("dt_consulta").getTime());
                int medicoId = rs.getInt("medico_id");
                String nomeAtendimento = rs.getString("nm_atendimento");
                String valorReferenciaComposicaoExameId = rs.getString("valor_referencia_composicao_exame_id");
                int pacienteId = rs.getInt("paciente_id");
                entidade = new ConsultaMedica(id, medicoId, pacienteId, dtConsulta, nomeAtendimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ConsultaMedica> selectAllConsultaMedica() {
        List<ConsultaMedica> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CONSULTA_MEDICA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int medicoId = rs.getInt("medico_id");
                int pacienteId = rs.getInt("paciente_id");
                Date dtConsulta = new Date(rs.getTimestamp("dt_consulta").getTime());
                String nomeAtendimento = rs.getString("nm_atendimento");
                entidades.add(new ConsultaMedica(id, medicoId, pacienteId, dtConsulta, nomeAtendimento));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteConsultaMedica(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_CONSULTA_MEDICA_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateConsultaMedica(ConsultaMedica entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_CONSULTA_MEDICA_SQL)) {
            statement.setTimestamp(1, new Timestamp(entidade.getDtConsulta().getTime()));
            statement.setInt(2, entidade.getMedicoId());
            statement.setInt(3, entidade.getPacienteId());
            statement.setString(4, entidade.getNomeAtendimento());
            statement.setInt(5, entidade.getId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
