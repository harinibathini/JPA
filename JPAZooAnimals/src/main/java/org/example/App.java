package org.example;

import Dao.AnimalDao;
import Dao.ZooDao;
import entities.Animal;
import entities.Zoo;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");

        boolean flag = true;
        while (flag){
            System.out.println("-------------------------");
            System.out.println("Enter 1 to INSERT ZOO");
            System.out.println("Enter 2 to INSERT ANIMAL");
            System.out.println("Enter 3 to UPDATE ANIMAL");
            System.out.println("Enter 4 to UPDATE ZOO");
            System.out.println("Enter 5 to DELETE ANIMAL");
            System.out.println("Enter 6 to DELETE ZOO");
            System.out.println("Enter 7 to get all animals whose age>4");
            System.out.println("Enter 8 to get category wise animals");
            System.out.println("Enter 9 to get all animals age in range (3-8)");
            System.out.println("Enter 10 to get z.zooName like 'Z%'");
            System.out.println("Enter 11 to SELECT ALL ZOO");
            System.out.println("Enter 12 to SELECT ALL ANIMALS");
            System.out.println("Enter 13 to SELECT ZOO BY ID");
            System.out.println("Enter 14 to SELECT ANIMAL BY ID");
            System.out.println("Enter 15 to get a.animalName like 'D%'");
            System.out.println("Enter 0 to EXIT");
            System.out.println("-------------------------");

            System.out.println("ENTER YOUR CHOICE:");
            int choice = Integer.parseInt(br.readLine());

            switch (choice){
                case 1:
                    System.out.println("INSERT INTO ZOO");

                    System.out.println("Enter zoo name");
                    String zName = br.readLine();
                    System.out.println("Enter zoo city");
                    String zCity = br.readLine();

                    Zoo zoo1 = new Zoo();
                    zoo1.setZooName(zName);
                    zoo1.setZooCity(zCity);
                    ZooDao.insert(zoo1);

                    break;
                case 2:
                    EntityManager em2 = emf.createEntityManager();
                    em2.getTransaction().begin();
                    System.out.println("INSERT ANIMAL");

                    System.out.println("Enter animal name");
                    String aName = br.readLine();
                    System.out.println("Enter animal age");
                    int aAge = Integer.parseInt(br.readLine());
                    System.out.println("Enter animal type: 'Domestic/ Wild'");
                    String aType = br.readLine();
                    System.out.println("Enter Zoo id to insert animal in zoo");
                    int zId = Integer.parseInt(br.readLine());
                    Zoo zoo2 = em2.find(Zoo.class,zId);   //fetch zoo by id

                    if(zoo2 != null){
                        Animal animal2 = new Animal();
                        animal2.setAnimalName(aName);
                        animal2.setAnimalAge(aAge);
                        animal2.setAnimalType(aType);
                        animal2.setZoo(zoo2);
                        AnimalDao.insert(animal2);
                        em2.getTransaction().commit();
                    }
                    break;
                case 3:
//                    EntityManager em3 = emf.createEntityManager();
//                    em3.getTransaction().begin();
                    System.out.println("UPDATE ANIMAL");

                    System.out.println("Enter animal id to be update");
                    int aId3 = Integer.parseInt(br.readLine());
                    System.out.println("Enter the updated age");
                    int aAge3 = Integer.parseInt(br.readLine());

                    AnimalDao.update(aId3, aAge3);
//
//                    em3.getTransaction().commit();
//                    em3.close();
                    break;
                case 4:
                    System.out.println("UPDATE ZOO");

                    System.out.println("Enter zoo id to update");
                    int zId4 = Integer.parseInt(br.readLine());
                    System.out.println("Enter the update city");
                    String zCity4 = br.readLine();

                    ZooDao.update(zId4, zCity4);

                    break;
                case 5:
                    System.out.println("DELETE ANIMAL");

                    System.out.println("Enter animal id to delete");
                    int aId5 = Integer.parseInt(br.readLine());

                    AnimalDao.delete(aId5);

                    break;
                case 6:
                    System.out.println("DELETE ZOO");

                    System.out.println("Enter zoo id to delete");
                    int zId6 = Integer.parseInt(br.readLine());

                    ZooDao.delete(zId6);

                    break;
                case 7:
                    EntityManager em7 = emf.createEntityManager();
                    em7.getTransaction().begin();
                    System.out.println("QUERY 1 - ANIMAL AGE>4");

//                    Query query7 = em7.createQuery("SELECT a FROM ANIMAL a where a.animalAge>4");  //not working
//                    List<Animal> list7 = query7.getResultList();
//                    for(Animal animal:list7){
//                        System.out.println(animal);
//                    }

                    TypedQuery<Animal> query7 = em7.createQuery("SELECT a FROM Animal a where a.animalAge>4", Animal.class);
                    List<Animal> list7 = query7.getResultList();
                    for(Animal a: list7){
                        System.out.println(a);
                    }

                    em7.getTransaction().commit();
                    em7.close();
                    break;
                case 8:
                    EntityManager em8 = emf.createEntityManager();
                    em8.getTransaction().begin();
                    System.out.println("QUERY 2 - CATEGORY WISE ANIMALS");

                    System.out.println("Enter the category you want to fetch");
                    String aType8 = br.readLine();
//                    Query query8 = em8.createQuery("SELECT a FROM ANIMAL a where a.animalType = :aType8 ");   //not working
//                    query8.setParameter("animalType", aType8);
//                    List<Animal> list8 = query8.getResultList();
//                    for(Animal animal:list8){
//                        System.out.println(animal);
//                    }

                    TypedQuery<Animal> query8 = em8.createQuery("Select a from Animal a where a.animalType = :aType ", Animal.class);
                    query8.setParameter("aType",aType8 );
                    List<Animal> list8 = query8.getResultList();
                    for(Animal a:list8){
                        System.out.println(a);
                    }

                    em8.getTransaction().commit();
                    em8.close();

                    break;
                case 9:
                    EntityManager em9 = emf.createEntityManager();
                    em9.getTransaction().begin();
                    System.out.println("Get age range between 3 and 8");

                    CriteriaBuilder cb9 = em9.getCriteriaBuilder();
                    CriteriaQuery<Animal> cq9 = cb9.createQuery(Animal.class);
                    Root<Animal> root9 = cq9.from(Animal.class);
                    cq9.where(cb9.between(root9.<Comparable>get("animalAge"), 3, 8));
                    List<Animal> list9 = em9.createQuery(cq9).getResultList();
                    for(Animal a: list9){
                        System.out.println(a);
                    }

                    em9.getTransaction().commit();
                    em9.close();
                    break;
                case 10:
                    EntityManager em10 = emf.createEntityManager();
                    em10.getTransaction().begin();
                    System.out.println("name like Zoo");

                    CriteriaBuilder cb10 = em10.getCriteriaBuilder();
                    CriteriaQuery<Zoo> cq10 = cb10.createQuery(Zoo.class);
                    Root<Zoo> root10 = cq10.from(Zoo.class);
                    cq10.where(cb10.like(root10.<String>get("zooName"), "Z%"));
                    List<Zoo> list10 = em10.createQuery(cq10).getResultList();
                    for(Zoo z: list10){
                        System.out.println(z);
                    }

                    em10.getTransaction().commit();
                    em10.close();
                    break;
                case 11:
//                    EntityManager em11 = emf.createEntityManager();
//                    em11.getTransaction().begin();
//                    System.out.println("Select all Zoos");
//                    TypedQuery<Zoo> query11 = em11.createQuery("from Zoo", Zoo.class);
//                    List<Zoo> list11 = query11.getResultList();
//                    for(Zoo z: list11){
//                        System.out.println(z);
//                    }
                    System.out.println("Select all Zoos");

                    ZooDao.selectAll();
                    break;
                case 12:
//                    EntityManager em12 = emf.createEntityManager();
//                    em12.getTransaction().begin();
//                    System.out.println("Select all Animals");
//
//                    TypedQuery<Animal> query12 = em12.createQuery("from Animal", Animal.class);
//                    List<Animal> list12 = query12.getResultList();
//                    for(Animal a: list12){
//                        System.out.println(a);
//                    }
                    System.out.println("Select all Animals");
                    AnimalDao.selectAll();

                    break;
                case 13:
                    System.out.println("Select zoo by id");

                    System.out.println("Enter id to be fetched");
                    int zId13 = Integer.parseInt(br.readLine());

                    ZooDao.selectById(zId13);
                    break;
                case 14:
                    System.out.println("Select Animal by id");

                    System.out.println("Enter id to be fetched");
                    int aId14 = Integer.parseInt(br.readLine());

                    AnimalDao.selectById(aId14);
                    break;
                case 15:
                    EntityManager em15 = emf.createEntityManager();
                    em15.getTransaction().begin();
                    System.out.println("name like animalName");

                    CriteriaBuilder cb15 = em15.getCriteriaBuilder();
                    CriteriaQuery<Animal> cq15 = cb15.createQuery(Animal.class);
                    Root<Animal> root15 = cq15.from(Animal.class);
                    cq15.where(cb15.like(root15.<String>get("animalName"), "D%"));
                    List<Animal> list15 = em15.createQuery(cq15).getResultList();
                    for(Animal a: list15){
                        System.out.println(a);
                    }

                    em15.getTransaction().commit();
                    em15.close();
                    break;
                case 0:
                    System.out.println("--EXIT--");
                    flag = false;
                    break;
                default:
                    System.out.println("Enter valid choice");
                    break;
            }

        }

    }
}
