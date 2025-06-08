package br.com.estacio.client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

public class CadastroClientV2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor!");

            // Login
            System.out.print("Login: ");
            String login = teclado.readLine();
            System.out.print("Senha: ");
            String senha = teclado.readLine();
            out.writeObject(login);
            out.writeObject(senha);
            String resposta = (String) in.readObject();
            System.out.println("Resposta do servidor: " + resposta);
            if (!"OK".equals(resposta)) {
                System.out.println("Falha na autenticação. Encerrando...");
                return;
            }

            // Após autenticação bem-sucedida
            SaidaFrame frame = new SaidaFrame();
            frame.setVisible(true);
            ThreadClient threadLeitura = new ThreadClient(in, frame.texto);
            threadLeitura.start();

            // Menu principal
            while (true) {
                System.out.println("\nComandos disponíveis:");
                System.out.println("L - Listar produtos");
                System.out.println("E - Entrada de produto");
                System.out.println("S - Saída de produto");
                System.out.println("X - Sair");
                System.out.print("Digite o comando: ");
                String comando = teclado.readLine().toUpperCase();
                out.writeObject(comando);
                if (comando.equals("X"))
                    break;
                if (comando.equals("L")) {
                    // Receber e exibir lista de produtos (implementar depois)
                } else if (comando.equals("E") || comando.equals("S")) {
                    // Entrada/Saída de produto
                    System.out.print("Id da pessoa: ");
                    int idPessoa = Integer.parseInt(teclado.readLine());
                    System.out.print("Id do produto: ");
                    int idProduto = Integer.parseInt(teclado.readLine());
                    System.out.print("Quantidade: ");
                    int quantidade = Integer.parseInt(teclado.readLine());
                    System.out.print("Valor unitário: ");
                    double valorUnitario = Double.parseDouble(teclado.readLine());
                    out.writeObject(idPessoa);
                    out.writeObject(idProduto);
                    out.writeObject(quantidade);
                    out.writeObject(valorUnitario);
                    // Receber resposta do servidor (implementar depois)
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}