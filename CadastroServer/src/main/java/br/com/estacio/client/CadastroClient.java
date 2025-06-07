package br.com.estacio.client;

import java.io.*;
import java.net.*;
import java.util.List;
import br.com.estacio.server.model.Produto;

public class CadastroClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 4321;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor!");

            // Envia login e senha
            System.out.print("Login: ");
            String login = userInput.readLine();
            System.out.print("Senha: ");
            String senha = userInput.readLine();

            out.writeObject(login);
            out.writeObject(senha);

            // Recebe resposta da autenticação
            String response = (String) in.readObject();
            System.out.println("Resposta do servidor: " + response);

            if (!response.equals("OK")) {
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

                out.writeObject(comando);

                if (comando.equals("L")) {
                    List<Produto> produtos = (List<Produto>) in.readObject();
                    System.out.println("\nProdutos encontrados: " + produtos.size());
                    for (Produto produto : produtos) {
                        System.out.printf("ID: %d, Nome: %s, Preço: R$ %.2f\n",
                                produto.getIdProduto(), produto.getNome(), produto.getPrecoVenda());
                    }
                } else {
                    response = (String) in.readObject();
                    System.out.println("Resposta do servidor: " + response);
                }
            }

        } catch (Exception e) {
            System.err.println("Erro na comunicação com o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}