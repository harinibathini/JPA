package dao;

import entities.Bank;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class BankDao {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
    public static void create(Bank bank){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(bank);

        em.getTransaction().commit();
        em.close();
    }
    public static void update(int uid) throws IOException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Bank bank = em.find(Bank.class, uid);
        System.out.println("Enter bank IFSC to be updated");
        int bIFSC = Integer.parseInt(br.readLine());
        bank.setBankIFSC(bIFSC);
        em.merge(bank);

        em.getTransaction().commit();
        em.close();
    }
    public static void delete(int did){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Bank bank = em.find(Bank.class, did);
        em.remove(bank);

        em.getTransaction().commit();
        em.close();
    }
    public static void selectAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Bank> query = em.createQuery("from Bank", Bank.class);
        List<Bank> list = query.getResultList();
        for(Bank b : list){
            System.out.println(b);
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void selectById(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Bank bank = em.find(Bank.class, id);
        TypedQuery<Bank> query = em.createQuery("select b from Bank b where b.bankId = :bid", Bank.class);
        query.setParameter("bid", id);
        List<Bank> list = query.getResultList();
        for(Bank b : list){
            System.out.println(b);
        }

        em.getTransaction().commit();
        em.close();
    }
}
