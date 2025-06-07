package br.com.estacio.server.controller;

import br.com.estacio.server.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class UsuarioJpaController {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("CadastroServerPU");
        em = emf.createEntityManager();
    }

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = emf.createEntityManager();
    }

    public Usuario findUsuario(String login, String senha) {
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}