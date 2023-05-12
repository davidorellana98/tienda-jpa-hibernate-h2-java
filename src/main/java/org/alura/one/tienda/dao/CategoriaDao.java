package org.alura.one.tienda.dao;

import org.alura.one.tienda.modelo.Categoria;
import org.alura.one.tienda.modelo.Producto;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private final EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Categoria categoria) {
        em.persist(categoria);
    }

    public void actualizar(Categoria categoria) {
        em.merge(categoria);
    }

    public void remover(Categoria categoria)  {
        categoria = em.merge(categoria);
        em.remove(categoria);
    }
}
