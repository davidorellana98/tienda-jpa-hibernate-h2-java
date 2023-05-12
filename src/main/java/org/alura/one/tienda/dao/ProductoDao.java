package org.alura.one.tienda.dao;

import org.alura.one.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductoDao {

    private final EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto) {
        em.persist(producto);
    }

    public Producto consultaId(Long id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> consultarTodos() {
        String jpql = "SELECT p FROM Producto AS p";
        return em.createQuery(jpql, Producto.class).getResultList();
    }

    public List<Producto> consultaNombre(String nombre) {
        String jpql = "SELECT p FROM Producto AS p WHERE p.nombre=:nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaNombreCategoria(String nombreCategoria) {
        String jpql = "SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombreCategoria).getResultList();
    }

    public BigDecimal consultarPrecioNombreProducto(String nombre) {
        String jpql = "SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
    }
}
