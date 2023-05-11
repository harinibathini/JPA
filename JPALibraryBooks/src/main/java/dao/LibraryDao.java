package dao;

import entities.Library;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LibraryDao {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");
    public static void create(Library library){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(library);

        em.getTransaction().commit();
        em.close();
    }
    public static void update(int uid) throws IOException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Library library = em.find(Library.class, uid);
        boolean flag = true;
        while(flag) {
            System.out.println("Enter property in library to be updated");
            System.out.println("-------------------------");
            System.out.println("1 to update libraryName");
            System.out.println("2 to update library City");
            System.out.println("0 to exit from update");
            System.out.println("-------------------------");
            System.out.println("enter choice:");
            int ch = Integer.parseInt(br.readLine());
            switch (ch) {
                case 1:
                    System.out.println("update libraryName: enter name");
                    String lName = br.readLine();
                    library.setLibraryName(lName);
                    em.merge(library);
                    break;
                case 2:
                    System.out.println("update libraryCity: enter City");
                    String lCity = br.readLine();
                    library.setLibraryCity(lCity);
                    em.merge(library);
                case 0:
                    System.out.println("exit");
                    flag = false;
                    break;
                default:
                    System.out.println("enter valid choice");
                    break;
            }
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void delete(int did){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Library library = em.find(Library.class, did);
        em.remove(library);

        em.getTransaction().commit();
        em.close();
    }
    public static void selectAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Library> query = em.createQuery("from Library", Library.class);
        List<Library> list = query.getResultList();
        for(Library l : list){
            System.out.println(l);
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void selectById(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Library library = em.find(Library.class, id);
        TypedQuery<Library> query = em.createQuery("select l from Library l where l.libraryId = :lid", Library.class);
        query.setParameter("lid", id);
        List<Library> list = query.getResultList();
        for(Library l : list){
            System.out.println(l);
        }

        em.getTransaction().commit();
        em.close();
    }
}
