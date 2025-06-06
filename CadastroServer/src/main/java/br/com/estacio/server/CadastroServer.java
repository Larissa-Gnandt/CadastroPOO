package br.com.estacio.server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroServer {
    private static final int PORT = 12345;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "Larissa";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Recebe login e senha
            String login = in.readLine();
            String senha = in.readLine();
            System.out.println("Login recebido: [" + login + "] Senha recebida: [" + senha + "]");

            // Valida credenciais
            if (!validarCredenciais(login, senha)) {
                out.println("ERRO: Credenciais inválidas");
                return;
            }

            out.println("OK: Credenciais válidas");

            // Loop principal de processamento
            while (true) {
                String comando = in.readLine();
                if (comando == null)
                    break;

                switch (comando.toUpperCase()) {
                    case "L":
                        List<String> produtos = listarProdutos();
                        out.println(produtos.size());
                        for (String produto : produtos) {
                            out.println(produto);
                        }
                        break;
                    default:
                        out.println("ERRO: Comando inválido");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar conexão com o cliente: " + e.getMessage());
            }
        }
    }

    private static boolean validarCredenciais(String login, String senha) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT COUNT(*) FROM Usuario WHERE login = ? AND senha = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, login);
                stmt.setString(2, senha);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao validar credenciais: " + e.getMessage());
        }
        return false;
    }

    private static List<String> listarProdutos() {
        List<String> produtos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT idProduto, nome, quantidade, precoVenda FROM Produto WHERE deletado = 0";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String produto = String.format("%d,%s,%d,%.2f",
                            rs.getInt("idProduto"),
                            rs.getString("nome"),
                            rs.getInt("quantidade"),
                            rs.getDouble("precoVenda"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }
}