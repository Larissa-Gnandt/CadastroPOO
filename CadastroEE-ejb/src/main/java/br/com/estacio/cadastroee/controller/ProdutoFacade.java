package br.com.estacio.cadastroee.controller;

import br.com.estacio.cadastroee.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProdutoFacade extends AbstractFacade<Produto> implements ProdutoFacadeLocal {
    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }

    @Override
    public void remove(Produto entity) {
        entity = getEntityManager().merge(entity);
        entity.setDeletado(true);
        getEntityManager().merge(entity);
    }

    @Override
    public java.util.List<Produto> findAll() {
        return getEntityManager().createQuery("SELECT p FROM Produto p WHERE p.deletado = false", Produto.class)
                .getResultList();
    }
}