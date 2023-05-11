package Dao;

import entities.Animal;
import entities.Zoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AnimalDao {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
   public static void insert(Animal animal){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();

       em.persist(animal);

       em.getTransaction().commit();
       em.close();
   }
   public static void selectById(int aid){
       EntityManager em = emf.createEntityManager();;
       em.getTransaction().begin();

       TypedQuery<Animal> query = em.createQuery("select a from Animal a where a.animalId= :id", Animal.class);
       query.setParameter("id", aid);
       List<Animal> list = query.getResultList();
       for(Animal a: list){
           System.out.println(a);
       }

       em.getTransaction().commit();
       em.close();
   }
   public static void update(int uid, int uage){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();

       Animal animal = em.find(Animal.class, uid);
       animal.setAnimalAge(uage);
       em.merge(animal);

       em.getTransaction().commit();
       em.close();
   }
   public static void delete(int did){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();

       Animal animal = em.find(Animal.class, did);
       em.remove(animal);

       em.getTransaction().commit();
       em.close();
   }
   public static void selectAll(){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();

       TypedQuery<Animal> query = em.createQuery("from Animal", Animal.class);
       List<Animal> list = query.getResultList();
       for(Animal a: list){
           System.out.println(a);
       }

       em.getTransaction().commit();
       em.close();
   }
}
