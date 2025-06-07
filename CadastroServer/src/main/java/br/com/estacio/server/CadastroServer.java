package br.com.estacio.server;

import br.com.estacio.server.controller.ProdutoJpaController;
import br.com.estacio.server.controller.UsuarioJpaController;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServer {
    public static void main(String[] args) {
        final int PORT = 4321;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");
        ProdutoJpaController ctrl = new ProdutoJpaController(emf);
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                CadastroThread thread = new CadastroThread(ctrl, ctrlUsu, clientSocket);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }
}