package br.com.estacio.cadastroee.controller;

import jakarta.ejb.Stateless;

@Stateless
public class TesteBean {
    public String ping() {
        return "pong";
    }
}