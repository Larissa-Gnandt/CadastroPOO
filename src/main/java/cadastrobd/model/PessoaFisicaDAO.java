package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    public PessoaFisica getPessoa(int id) {
        PessoaFisica pf = null;
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf " +
                "FROM PessoaFisica pf JOIN Pessoa p ON pf.id = p.id WHERE pf.id = ?";
        try (Connection conn = ConectorBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pf = new PessoaFisica(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("logradouro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("cpf"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pf;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf " +
                "FROM PessoaFisica pf JOIN Pessoa p ON pf.id = p.id";
        try (Connection conn = ConectorBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PessoaFisica pf = new PessoaFisica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf"));
                lista.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void incluir(PessoaFisica pf) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPF = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            int idGerado = -1;
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
                stmtPessoa.setString(1, pf.nome);
                stmtPessoa.setString(2, pf.logradouro);
                stmtPessoa.setString(3, pf.cidade);
                stmtPessoa.setString(4, pf.estado);
                stmtPessoa.setString(5, pf.telefone);
                stmtPessoa.setString(6, pf.email);
                stmtPessoa.executeUpdate();
                try (ResultSet rs = stmtPessoa.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGerado = rs.getInt(1);
                    }
                }
            }
            try (PreparedStatement stmtPF = conn.prepareStatement(sqlPF)) {
                stmtPF.setInt(1, idGerado);
                stmtPF.setString(2, pf.getCpf());
                stmtPF.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pf) {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPF = "UPDATE PessoaFisica SET cpf=? WHERE id=?";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
                stmtPessoa.setString(1, pf.nome);
                stmtPessoa.setString(2, pf.logradouro);
                stmtPessoa.setString(3, pf.cidade);
                stmtPessoa.setString(4, pf.estado);
                stmtPessoa.setString(5, pf.telefone);
                stmtPessoa.setString(6, pf.email);
                stmtPessoa.setInt(7, pf.id);
                stmtPessoa.executeUpdate();
            }
            try (PreparedStatement stmtPF = conn.prepareStatement(sqlPF)) {
                stmtPF.setString(1, pf.getCpf());
                stmtPF.setInt(2, pf.id);
                stmtPF.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPF = "DELETE FROM PessoaFisica WHERE id=?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPF = conn.prepareStatement(sqlPF)) {
                stmtPF.setInt(1, id);
                stmtPF.executeUpdate();
            }
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
                stmtPessoa.setInt(1, id);
                stmtPessoa.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}