package org.alura.one.tienda.dao;

import org.alura.one.tienda.modelo.Pedido;
import org.alura.one.tienda.vo.RelatorioVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Pedido pedido) {
        em.persist(pedido);
    }

    public Pedido consultaId(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<Pedido> consultarTodos() {
        String jpql = "SELECT p FROM Pedido AS p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido AS p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public Double valorPromedioVendido() {
        String jpql = "SELECT AVG(p.valorTotal) FROM Pedido AS p";
        return em.createQuery(jpql, Double.class).getSingleResult();
    }

    public List<Object[]> relatorioVentas() {
        String jpql = "SELECT producto.nombre, SUM(item.cantidad), MAX(pedido.fecha) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return em.createQuery(jpql, Object[].class).getResultList();
    }

    public List<RelatorioVenta> relatorioVentasVO() {
        String jpql = "SELECT new org.alura.one.tienda.vo.RelatorioVenta(producto.nombre, SUM(item.cantidad), MAX(pedido.fecha)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return em.createQuery(jpql, RelatorioVenta.class).getResultList();
    }

    public Pedido consultarPedidoConCliente(Long id) {
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id=:id";
        return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
    }
}
