package Dao;

import entities.Zoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ZooDao {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
    public static void insert(Zoo zoo){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(zoo);

        em.getTransaction().commit();
        em.close();
    }
    public static void selectById(int zid){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Zoo> query = em.createQuery("select z from Zoo z where z.zooId= :id", Zoo.class);
        query.setParameter("id", zid);
        List<Zoo> list = query.getResultList();
        for(Zoo z: list){
            System.out.println(z);
        }

        em.getTransaction().commit();
        em.close();

    }
    public static void update(int uid, String ucity){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Zoo zoo = em.find(Zoo.class, uid);
        zoo.setZooCity(ucity);
        em.merge(zoo);

        em.getTransaction().commit();
        em.close();

    }
    public static void delete(int did){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Zoo zoo = em.find(Zoo.class, did);
        em.remove(zoo);

        em.getTransaction().commit();
        em.close();

    }
    public static void selectAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Zoo> query = em.createQuery("from Zoo", Zoo.class);
        List<Zoo> list = query.getResultList();
        for(Zoo z: list){
            System.out.println(z);
        }

        em.getTransaction().commit();
        em.close();
    }
}
