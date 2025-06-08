package br.com.estacio.server.controller;

import br.com.estacio.server.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PessoaJpaController {
    private EntityManagerFactory emf;

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Pessoa findPessoa(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }
}