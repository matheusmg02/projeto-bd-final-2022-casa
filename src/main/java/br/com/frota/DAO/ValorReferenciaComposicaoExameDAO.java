package br.com.frota.DAO;

import br.com.frota.model.TipoExame;
import br.com.frota.model.ValorReferenciaComposicaoExame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValorReferenciaComposicaoExameDAO extends ConexaoDB{
    private static final String INSERT_VALOR_REFERENCIA_COMPOSICAO_EXAME_SQL = "INSERT INTO valor_referencia_composicao_exame (valor_minimo, valor_maximo, limitador_minimo, limitador_maximo, unidade_medida_id) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_VALOR_REFERENCIA_COMPOSICAO_EXAME_BY_ID = "SELECT valor_minimo, valor_maximo, limitador_minimo, limitador_maximo, unidade_medida_id FROM valor_referencia_composicao_exame WHERE id = ?";
    private static final String SELECT_ALL_VALOR_REFERENCIA_COMPOSICAO_EXAME = "SELECT * FROM valor_referencia_composicao_exame;";
    private static final String DELETE_VALOR_REFERENCIA_COMPOSICAO_EXAME_SQL = "DELETE FROM valor_referencia_composicao_exame WHERE id = ?;";
    private static final String UPDATE_VALOR_REFERENCIA_COMPOSICAO_EXAME_SQL = "UPDATE valor_referencia_composicao_exame SET valor_minimo = ?, valor_maximo = ?, limitador_minimo = ?, limitador_maximo, unidade_medida_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM valor_referencia_composicao_exame;";

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

    public void insertValorReferenciaComposicaoExame(ValorReferenciaComposicaoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_VALOR_REFERENCIA_COMPOSICAO_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getValorMinimo());
            preparedStatement.setString(2, entidade.getValorMaximo());
            preparedStatement.setString(3, entidade.getLimitadorMinimo());
            preparedStatement.setString(4, entidade.getLimitadorMaximo());
            preparedStatement.setInt(5, entidade.getUnidadeMedidaId());

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

    public ValorReferenciaComposicaoExame findById(int id) {
        ValorReferenciaComposicaoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_VALOR_REFERENCIA_COMPOSICAO_EXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String valorMinimo = rs.getString("valor_minimo");
                String valorMaximo = rs.getString("valor_maximo");
                String limitadorMinimo = rs.getString("limitador_minimo");
                String limitadorMaximo = rs.getString("limitador_maximo");
                int unidadeMedidaId = rs.getInt("unidade_medida_id");

                entidade = new ValorReferenciaComposicaoExame(id, valorMinimo, valorMaximo, limitadorMinimo, limitadorMaximo, unidadeMedidaId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ValorReferenciaComposicaoExame> selectAllValorReferenciaComposicaoExame() {
        List<ValorReferenciaComposicaoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_VALOR_REFERENCIA_COMPOSICAO_EXAME)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String valorMinimo = rs.getString("valor_minimo");
                String valorMaximo = rs.getString("valor_maximo");
                String limitadorMinimo = rs.getString("limitador_minimo");
                String limitadorMaximo = rs.getString("limitador_maximo");
                int unidadeMedidaId = rs.getInt("unidade_medida_id");

                entidades.add(new ValorReferenciaComposicaoExame(id, valorMinimo, valorMaximo, limitadorMinimo, limitadorMaximo, unidadeMedidaId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteValorReferenciaComposicaoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_VALOR_REFERENCIA_COMPOSICAO_EXAME_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateValorReferenciaComposicaoExame(ValorReferenciaComposicaoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_VALOR_REFERENCIA_COMPOSICAO_EXAME_SQL)) {

            statement.setString(1, entidade.getValorMinimo());
            statement.setString(2, entidade.getValorMaximo());
            statement.setString(1, entidade.getLimitadorMinimo());
            statement.setString(2, entidade.getLimitadorMaximo());
            statement.setInt(1, entidade.getUnidadeMedidaId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
