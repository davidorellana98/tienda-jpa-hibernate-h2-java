package org.alura.one.tienda.dao;

import org.alura.one.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        return em.createNamedQuery("Producto.consultaPrecio", BigDecimal.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
    }

    public List<Producto> consultarPorParametros(String nombre, BigDecimal precio, LocalDate fechaRegistro) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE 1=1 ");
        if (nombre != null && !nombre.trim().isEmpty()) {
            jpql.append("AND p.nombre=:nombre ");
        }
        if (precio != null && !precio.equals(new BigDecimal(0))) {
            jpql.append("AND p.precio=:precio ");
        }
        if (fechaRegistro != null) {
            jpql.append("AND p.fechaRegistro=:fechaRegistro");
        }

        TypedQuery<Producto> query = em.createQuery(jpql.toString(), Producto.class);

        if (nombre != null && !nombre.trim().isEmpty()) {
            query.setParameter("nombre", nombre);
        }
        if (precio != null && !precio.equals(new BigDecimal(0))) {
            query.setParameter("precio", precio);
        }
        if (fechaRegistro != null) {
            query.setParameter("fechaRegistro", fechaRegistro);
        }

        return query.getResultList();
    }

    public List<Producto> consultarPorParametrosConAPICriteria(String nombre, BigDecimal precio,LocalDate fechaRegistro){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
        Root<Producto> from = query.from(Producto.class);

        Predicate filtro = builder.and();
        if(nombre != null && !nombre.trim().isEmpty()) {
            filtro=builder.and(filtro,builder.equal(from.get("nombre"), nombre));
        }
        if(precio != null && !precio.equals(new BigDecimal(0))) {
            filtro=builder.and(filtro,builder.equal(from.get("precio"), precio));
        }
        if(fechaRegistro != null) {
            filtro=builder.and(filtro,builder.equal(from.get("fechaRegistro"), fechaRegistro));
        }

        query = query.where(filtro);
        return em.createQuery(query).getResultList();
    }
}
