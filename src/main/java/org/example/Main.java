package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Product;
import org.example.persistence.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new PersistenceUnitInfo(),new HashMap<>());
        EntityManager entityManager = emf.createEntityManager();//represent the context
        try {
            entityManager.getTransaction().begin();

            Product product = new Product();

            product.setId(2L);
            product.setName("Orange");

            entityManager.persist(product);


            /*-----------
            -------------
            entityManager.persist();  -> Adding an entity in the context
            entityManager.find();     -> Finds by primary key. Get from DB and add it to the context if it dosen't already exists.
            entityManager.remove();   -> Marking an entity to removal.
            entityManager.merge();    -> Merge an entity from outside the context to the context.
            entityManager.refresh();  -> Mirror the context from the database.
            entityManager.detach();   -> Taking the entity out of the context.
            -------------
            -------------
            */

            
            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }
}