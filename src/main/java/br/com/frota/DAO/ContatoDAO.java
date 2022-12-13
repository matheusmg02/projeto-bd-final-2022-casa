package br.com.frota.DAO;
import br.com.frota.model.ComposicaoExame;
import br.com.frota.model.ConsultaMedica;
import br.com.frota.model.Contato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends ConexaoDB{
    private static final String INSERT_CONTATO_SQL = "INSERT INTO contato (telefone, laboratorio_id) VALUES (?, ?);";
    private static final String SELECT_CONTATO_BY_ID = "SELECT id, descricao, unidade_medida_id FROM composicao_exame WHERE id = ?";
    private static final String SELECT_ALL_CONTATO = "SELECT * FROM contato;";
    private static final String DELETE_CONTATO_SQL = "DELETE FROM contato WHERE id = ?;";
    //    private static final String BUSCAR_POR_DESCRICAO_MARCA_SQL = "DELETE FROM marca WHERE descricao = ?;";
    private static final String UPDATE_CONTATO_SQL = "UPDATE marca SET telefone = ?, laboratorio_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM contato;";

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

    public void insertContato(Contato entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CONTATO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getTelefone());
            preparedStatement.setInt(2, entidade.getLaboratorioId());

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

    public Contato findById(int id) {
        Contato entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CONTATO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String telefone = rs.getString("telefone");
                int laboratorioId = rs.getInt("laboratorio_id");
                entidade = new Contato(id, telefone, laboratorioId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Contato> selectAllContato() {
        List<Contato> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CONTATO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String telefone = rs.getString("telefone");
                int laboratorioId = rs.getInt("unidade_medida_id");
                entidades.add(new Contato(id, telefone, laboratorioId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteContato(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_CONTATO_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateContato(Contato entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_CONTATO_SQL)) {

            statement.setString(1, entidade.getTelefone());
            statement.setInt(2, entidade.getLaboratorioId());
            statement.setInt(3, entidade.getId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
