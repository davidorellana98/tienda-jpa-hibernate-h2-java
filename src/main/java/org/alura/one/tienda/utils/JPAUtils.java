package org.alura.one.tienda.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda");
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
