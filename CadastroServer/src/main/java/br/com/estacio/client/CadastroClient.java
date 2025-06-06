package br.com.estacio.client;

import java.io.*;
import java.net.*;

public class CadastroClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor!");

            // Envia login e senha
            System.out.print("Login: ");
            String login = userInput.readLine();
            System.out.print("Senha: ");
            String senha = userInput.readLine();

            out.println(login);
            out.println(senha);

            // Recebe resposta da autenticação
            String response = in.readLine();
            System.out.println("Resposta do servidor: " + response);

            if (!response.startsWith("OK")) {
                System.out.println("Falha na autenticação. Encerrando...");
                return;
            }

            // Loop principal
            while (true) {
                System.out.println("\nComandos disponíveis:");
                System.out.println("L - Listar produtos");
                System.out.println("S - Sair");
                System.out.print("Digite o comando: ");

                String comando = userInput.readLine().toUpperCase();
                if (comando.equals("S")) {
                    break;
                }

                out.println(comando);

                if (comando.equals("L")) {
                    int numProdutos = Integer.parseInt(in.readLine());
                    System.out.println("\nProdutos encontrados: " + numProdutos);
                    for (int i = 0; i < numProdutos; i++) {
                        String produto = in.readLine();
                        String[] partes = produto.split(",");
                        System.out.printf("ID: %s, Nome: %s, Preço: R$ %.2f%n",
                                partes[0], partes[1], Double.parseDouble(partes[2]));
                    }
                } else {
                    response = in.readLine();
                    System.out.println("Resposta do servidor: " + response);
                }
            }

        } catch (IOException e) {
            System.err.println("Erro na comunicação com o servidor: " + e.getMessage());
        }
    }
}