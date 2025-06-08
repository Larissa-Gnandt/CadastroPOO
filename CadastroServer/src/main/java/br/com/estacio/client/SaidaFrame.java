package br.com.estacio.client;

import javax.swing.*;
import java.awt.*;

public class SaidaFrame extends JDialog {
    public JTextArea texto;

    public SaidaFrame() {
        setTitle("Saída do Servidor");
        setBounds(100, 100, 400, 300);
        setModal(false);
        texto = new JTextArea();
        texto.setEditable(false);
        add(new JScrollPane(texto), BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}