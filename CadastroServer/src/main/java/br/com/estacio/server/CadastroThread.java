package br.com.estacio.server;

import br.com.estacio.server.controller.ProdutoJpaController;
import br.com.estacio.server.controller.UsuarioJpaController;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class CadastroThread extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private Socket s1;

    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s1.getInputStream());

            // Recebe login e senha
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            // Autentica usuário
            Object usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                out.writeObject("ERRO: Usuário ou senha inválidos");
                s1.close();
                return;
            } else {
                out.writeObject("OK");
            }

            // Loop de comandos
            while (true) {
                String comando = (String) in.readObject();
                if (comando == null || comando.equalsIgnoreCase("S")) {
                    break;
                }
                if (comando.equalsIgnoreCase("L")) {
                    var produtos = ctrl.findProdutoEntities();
                    out.writeObject(produtos);
                } else {
                    out.writeObject("Comando inválido");
                }
            }
            s1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}