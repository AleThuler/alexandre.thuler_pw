package com.etec.alethuler.repository;

import com.etec.alethuler.dto.FuncionarioRequest;
import com.etec.alethuler.exception.ErroBancoException;
import com.etec.alethuler.model.Funcionario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FuncionarioRepository {

    private final DataSource dataSource;

    public FuncionarioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Funcionario> listarTodos() {
        String sql = "SELECT id, nome, funcao, salario, created_at FROM funcionarios ORDER BY id DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Funcionario> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao listar funcionários", e);
        }
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        String sql = "SELECT id, nome, funcao, salario, created_at FROM funcionarios WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id); 

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao buscar o funcionário " + id, e);
        }
    }

    public Funcionario inserir(FuncionarioRequest dados) {
        String sql = "INSERT INTO funcionarios (nome, funcao, salario) VALUES (?, ?, ?)";
        long novoId;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dados.nome().trim());
            ps.setString(2, dados.funcao().trim());
            ps.setBigDecimal(3, dados.salario().setScale(2, RoundingMode.HALF_UP));
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                chaves.next();
                novoId = chaves.getLong(1);
            }

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao inserir funcionário", e);
        }

        return buscarPorId(novoId).orElseThrow(
                () -> new ErroBancoException("Funcionário inserido, mas não encontrado em seguida", null));
    }

    public int atualizar(Long id, FuncionarioRequest dados) {
        String sql = "UPDATE funcionarios SET nome = ?, funcao = ?, salario = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dados.nome().trim());
            ps.setString(2, dados.funcao().trim());
            ps.setBigDecimal(3, dados.salario().setScale(2, RoundingMode.HALF_UP));
            ps.setLong(4, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao atualizar o funcionário " + id, e);
        }
    }

    public int deletar(Long id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao excluir o funcionário " + id, e);
        }
    }

    private Funcionario mapear(ResultSet rs) throws SQLException {
        Timestamp criadoEm = rs.getTimestamp("created_at");

        return new Funcionario(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("funcao"),
                rs.getBigDecimal("salario"),
                criadoEm == null ? null : criadoEm.toLocalDateTime());
    }
}
