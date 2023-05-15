package org.alura.one.tienda.prueba;

import org.alura.one.tienda.dao.CategoriaDao;
import org.alura.one.tienda.dao.ClienteDao;
import org.alura.one.tienda.dao.PedidoDao;
import org.alura.one.tienda.dao.ProductoDao;
import org.alura.one.tienda.modelo.*;
import org.alura.one.tienda.utils.JPAUtils;
import org.alura.one.tienda.vo.RelatorioVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroPedido {

    public static void main(String[] args) {
        registroProducto();
        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaId(1L);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Cliente cliente = new Cliente("Juan", "000000");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemsPedido(5, producto, pedido));

        em.getTransaction().begin();
        clienteDao.guardar(cliente);
        pedidoDao.guardar(pedido);
        em.getTransaction().commit();

        BigDecimal valorTotal = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total: " + valorTotal);

        List<RelatorioVenta> relatorio = pedidoDao.relatorioVentasVO();
        relatorio.forEach(System.out::println);
    }

    private static void registroProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Samsung", "Telefono usado", new BigDecimal("1000"), celulares);

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        em.getTransaction().begin();
        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
