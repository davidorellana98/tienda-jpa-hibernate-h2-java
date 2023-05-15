package org.alura.one.tienda.dao;

import org.alura.one.tienda.modelo.Cliente;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDao {

    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Cliente cliente) {
        em.persist(cliente);
    }

    public Cliente consultaId(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> consultarTodos() {
        String jpql = "SELECT c FROM Cliente AS c";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public List<Cliente> consultaNombre(String nombre) {
        String jpql = "SELECT c FROM Cliente AS c WHERE c.datosPersonales.nombre=:nombre";
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Cliente> consultaNombreCategoria(String nombreCategoria) {
        String jpql = "SELECT c FROM Cliente AS c WHERE c.datosPersonales.nombre=:nombre";
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombreCategoria).getResultList();
    }

    public BigDecimal consultarPrecioNombreProducto(String nombre) {
        String jpql = "SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
    }
}
