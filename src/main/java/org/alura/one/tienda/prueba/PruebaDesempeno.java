package org.alura.one.tienda.prueba;

import org.alura.one.tienda.dao.PedidoDao;
import org.alura.one.tienda.modelo.Pedido;
import org.alura.one.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class PruebaDesempeno {

    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.cargarRegistros();

        EntityManager em = JPAUtils.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2L);
        em.close();
        System.out.println(pedidoConCliente.getCliente().getNombre());
    }
}
