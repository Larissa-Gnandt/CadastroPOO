package br.com.estacio.client;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.util.List;

public class ThreadClient extends Thread {
    private ObjectInputStream entrada;
    private JTextArea textArea;

    public ThreadClient(ObjectInputStream entrada, JTextArea textArea) {
        this.entrada = entrada;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = entrada.readObject();
                if (obj instanceof String) {
                    String msg = (String) obj;
                    SwingUtilities.invokeLater(() -> textArea.append(msg + "\n"));
                } else if (obj instanceof List) {
                    List<?> lista = (List<?>) obj;
                    for (Object item : lista) {
                        // Supondo que seja Produto
                        try {
                            var metodoGetNome = item.getClass().getMethod("getNome");
                            var metodoGetQuantidade = item.getClass().getMethod("getQuantidade");
                            String nome = (String) metodoGetNome.invoke(item);
                            Integer qtd = (Integer) metodoGetQuantidade.invoke(item);
                            String linha = String.format("Produto: %s, Quantidade: %d", nome, qtd);
                            SwingUtilities.invokeLater(() -> textArea.append(linha + "\n"));
                        } catch (Exception e) {
                            // Ignora se não for Produto
                        }
                    }
                }
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> textArea.append("Conexão encerrada.\n"));
        }
    }
}
