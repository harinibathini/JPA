package org.example;

import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        Student student = new Student();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rt");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Transaction transaction = (Transaction) entityManager.getTransaction();
        transaction.begin();
        //entityManager.getTransaction().begin();

       // student.setId(2);
        student.setName("Haarini");

        entityManager.persist(student);
       // entityManager.merge(student);

       transaction.commit();
        //entityManager.getTransaction().commit();

        entityManager.close();

        System.out.println(student);
    }
}
