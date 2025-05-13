package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pj = null;
        String sql = "SELECT * FROM PessoaJuridica WHERE id = ?";
        try (Connection conn = ConectorBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pj = new PessoaJuridica(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("logradouro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("cnpj"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pj;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> lista = new ArrayList<>();
        String sql = "SELECT * FROM PessoaJuridica";
        try (Connection conn = ConectorBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PessoaJuridica pj = new PessoaJuridica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj"));
                lista.add(pj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void incluir(PessoaJuridica pj) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPJ = "INSERT INTO PessoaJuridica (id, nome, logradouro, cidade, estado, telefone, email, cnpj) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            int idGerado = -1;
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
                stmtPessoa.setString(1, pj.getNome());
                stmtPessoa.setString(2, pj.logradouro);
                stmtPessoa.setString(3, pj.cidade);
                stmtPessoa.setString(4, pj.estado);
                stmtPessoa.setString(5, pj.telefone);
                stmtPessoa.setString(6, pj.getEmail());
                stmtPessoa.executeUpdate();
                try (ResultSet rs = stmtPessoa.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGerado = rs.getInt(1);
                    }
                }
            }
            try (PreparedStatement stmtPJ = conn.prepareStatement(sqlPJ)) {
                stmtPJ.setInt(1, idGerado);
                stmtPJ.setString(2, pj.getNome());
                stmtPJ.setString(3, pj.logradouro);
                stmtPJ.setString(4, pj.cidade);
                stmtPJ.setString(5, pj.estado);
                stmtPJ.setString(6, pj.telefone);
                stmtPJ.setString(7, pj.getEmail());
                stmtPJ.setString(8, pj.getCnpj());
                stmtPJ.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pj) {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPJ = "UPDATE PessoaJuridica SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=?, cnpj=? WHERE id=?";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
                stmtPessoa.setString(1, pj.getNome());
                stmtPessoa.setString(2, pj.logradouro);
                stmtPessoa.setString(3, pj.cidade);
                stmtPessoa.setString(4, pj.estado);
                stmtPessoa.setString(5, pj.telefone);
                stmtPessoa.setString(6, pj.getEmail());
                stmtPessoa.setInt(7, pj.getId());
                stmtPessoa.executeUpdate();
            }
            try (PreparedStatement stmtPJ = conn.prepareStatement(sqlPJ)) {
                stmtPJ.setString(1, pj.getNome());
                stmtPJ.setString(2, pj.logradouro);
                stmtPJ.setString(3, pj.cidade);
                stmtPJ.setString(4, pj.estado);
                stmtPJ.setString(5, pj.telefone);
                stmtPJ.setString(6, pj.getEmail());
                stmtPJ.setString(7, pj.getCnpj());
                stmtPJ.setInt(8, pj.getId());
                stmtPJ.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPJ = "DELETE FROM PessoaJuridica WHERE id=?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPJ = conn.prepareStatement(sqlPJ)) {
                stmtPJ.setInt(1, id);
                stmtPJ.executeUpdate();
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