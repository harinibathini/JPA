package dao;

import entities.Book;
import entities.Library;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class BookDao {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
    public static void create(Book book){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(book);

        em.getTransaction().commit();
        em.close();
    }
    public static void update(int uid){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = em.find(Book.class, uid);
        System.out.println("Enter date to be updated");
        Date uDate = new Date();
        book.setPublishedDate(uDate);
        em.merge(book);

        em.getTransaction().commit();
        em.close();
    }
    public static void delete(int did){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = em.find(Book.class, did);
        em.remove(book);

        em.getTransaction().commit();
        em.close();
    }
    public static void selectAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Book> query = em.createQuery("from Book", Book.class);
        List<Book> list = query.getResultList();
        for(Book b : list){
            System.out.println(b);
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void selectById(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = em.find(Book.class, id);
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.bookId = :bid", Book.class);
        query.setParameter("bid", id);
        List<Book> list = query.getResultList();
        for(Book b : list){
            System.out.println(b);
        }

        em.getTransaction().commit();
        em.close();
    }
}
