package org.alura.one.tienda.prueba;

import org.alura.one.tienda.dao.CategoriaDao;
import org.alura.one.tienda.dao.ProductoDao;
import org.alura.one.tienda.modelo.Categoria;
import org.alura.one.tienda.modelo.CategoriaId;
import org.alura.one.tienda.modelo.Producto;
import org.alura.one.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegistroProducto {

    public static void main(String[] args) {
        registroProducto();
        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaId(1L);
        System.out.println(producto.getNombre());

        BigDecimal precio = productoDao.consultarPrecioNombreProducto("Samsung");
        System.out.println(precio);

        Categoria find = em.find(Categoria.class, new CategoriaId("CELULARES", "123"));
        System.out.println(find.getNombre());
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
