package dao;

import entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class AccountDao {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
    public static void create(Account account){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(account);

        em.getTransaction().commit();
        em.close();
    }
    public static void update(int uid){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Account account = em.find(Account.class, uid);
        System.out.println("Enter date to be updated");
        Date uDate = new Date();
        account.setAccountDate(uDate);
        em.merge(account);

        em.getTransaction().commit();
        em.close();
    }
    public static void delete(int did){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Account account = em.find(Account.class, did);
        em.remove(account);

        em.getTransaction().commit();
        em.close();
    }
    public static void selectAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Account> query = em.createQuery("from Account", Account.class);
        List<Account> list = query.getResultList();
        for(Account a : list){
            System.out.println(a);
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void selectById(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Account account = em.find(Account.class, id);
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.accountId = :aid", Account.class);
        query.setParameter("aid", id);
        List<Account> list = query.getResultList();
        for(Account a : list){
            System.out.println(a);
        }

        em.getTransaction().commit();
        em.close();
    }
}
