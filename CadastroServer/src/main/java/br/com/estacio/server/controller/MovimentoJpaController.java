package br.com.estacio.server.controller;

import br.com.estacio.server.model.Movimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MovimentoJpaController {
    private EntityManagerFactory emf;

    public MovimentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void create(Movimento movimento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}