package br.com.estacio.server.model;

import jakarta.persistence.*;
import java.util.List;

public class JPATest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Listando todos os usuários:");
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getIdUsuario() + ", Login: " + u.getLogin() + ", Senha: " + u.getSenha());
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}