package dao;

import entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class QueriesDao {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
    public static void query1() throws IOException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("11 GET BOOKS WHOSE PRICE >= 400");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.bookPrice>= :p", Book.class);
        System.out.println("Enter the price above which you want to fetch");
        int price = Integer.parseInt(br.readLine());
        query.setParameter("p",price );
        List<Book> list = query.getResultList();
        for(Book b : list){
            System.out.println(b);
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void query2() throws IOException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("12 GET BOOKS AS THEIR PUBLISHER");
        System.out.println("Enter pName to get his/her books");
        String pName = br.readLine();
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.bookPublisher = :pn", Book.class);
        query.setParameter("pn", pName);
        List<Book> list = query.getResultList();
        for(Book b:list){
            System.out.println(b);
        }

        em.getTransaction().commit();
        em.close();
    }
}
