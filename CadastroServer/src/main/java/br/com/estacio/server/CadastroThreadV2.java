package br.com.estacio.server;

import br.com.estacio.server.controller.ProdutoJpaController;
import br.com.estacio.server.controller.UsuarioJpaController;
import br.com.estacio.server.controller.MovimentoJpaController;
import br.com.estacio.server.controller.PessoaJpaController;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class CadastroThreadV2 extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private MovimentoJpaController ctrlMov;
    private PessoaJpaController ctrlPessoa;
    private Socket s1;

    public CadastroThreadV2(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, MovimentoJpaController ctrlMov,
            PessoaJpaController ctrlPessoa, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.ctrlMov = ctrlMov;
        this.ctrlPessoa = ctrlPessoa;
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
            var usuario = ctrlUsu.findUsuario(login, senha);
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
                if (comando == null || comando.equalsIgnoreCase("X")) {
                    break;
                }
                if (comando.equalsIgnoreCase("L")) {
                    var produtos = ctrl.findProdutoEntities();
                    out.writeObject(produtos);
                } else if (comando.equalsIgnoreCase("E") || comando.equalsIgnoreCase("S")) {
                    // Entrada ou Saída de produto
                    int idPessoa = (Integer) in.readObject();
                    int idProduto = (Integer) in.readObject();
                    int quantidade = (Integer) in.readObject();
                    double valorUnitario = (Double) in.readObject();

                    // Cria movimento
                    br.com.estacio.server.model.Movimento mov = new br.com.estacio.server.model.Movimento();
                    mov.setIdUsuario(((br.com.estacio.server.model.Usuario) usuario).getIdUsuario());
                    mov.setTipo(comando.toUpperCase());
                    mov.setIdPessoa(idPessoa);
                    mov.setIdProduto(idProduto);
                    mov.setQuantidade(quantidade);
                    mov.setValorUnitario(valorUnitario);
                    ctrlMov.create(mov);

                    // Atualiza quantidade do produto
                    var produto = ctrl.findProduto(idProduto);
                    if (produto != null) {
                        int novaQuantidade = produto.getQuantidade();
                        if (comando.equalsIgnoreCase("E")) {
                            novaQuantidade += quantidade;
                        } else if (comando.equalsIgnoreCase("S")) {
                            novaQuantidade -= quantidade;
                            if (novaQuantidade < 0)
                                novaQuantidade = 0;
                        }
                        produto.setQuantidade(novaQuantidade);
                        ctrl.updateProduto(produto);
                    }
                    out.writeObject("Movimento registrado com sucesso!");
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