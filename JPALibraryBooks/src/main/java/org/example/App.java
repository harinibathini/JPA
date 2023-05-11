package org.example;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.BookDao;
import dao.LibraryDao;
import dao.QueriesDao;
import entities.Book;
import entities.Library;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println("Hello World");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rt");

        boolean flag = true;
        while (flag){
            System.out.println("____________________________");
            System.out.println("1 INSERT LIBRARY");
            System.out.println("2 INSERT BOOK");
            System.out.println("3 UPDATE LIBRARY");
            System.out.println("4 UPDATE BOOK");
            System.out.println("5 DELETE LIBRARY");
            System.out.println("6 DELETE BOOK");
            System.out.println("7 SELECT ALL LIBRARY");
            System.out.println("8 SELECT ALL BOOK");
            System.out.println("9 SELECT BY ID LIBRARY");
            System.out.println("10 SELECT BY ID BOOK");
            System.out.println("11 GET BOOKS WHOSE PRICE >= 400");
            System.out.println("12 GET BOOKS AS THEIR PUBLISHER");
            System.out.println("0 EXIT");
            System.out.println("____________________________");
            System.out.println("Enter choice");
            int choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1:
                    System.out.println("1 INSERT LIBRARY");
                    System.out.println("Enter Library name ");
                    String lName = br.readLine();
                    System.out.println("Enter LCity");
                    String lCity = br.readLine();
                    Library library1 = new Library(lName,lCity);
                    LibraryDao.create(library1);
                    break;
                case 2:
                    EntityManager em2 = emf.createEntityManager();
                    em2.getTransaction().begin();
                    System.out.println("2 INSERT BOOK");

                    System.out.println("Enter Book name");
                    String bName = br.readLine();
                    System.out.println("Enter BAuthor");
                    String bAuthor = br.readLine();
                    System.out.println("Enter BPublisher");
                    String bPublisher = br.readLine();
                    System.out.println("Enter BPrice");
                    int bPrice = Integer.parseInt(br.readLine());
                    System.out.println("Enter Date");
                    Date date = new Date();
                    System.out.println("Enter Library id to insert in book");
                    int lId2 = Integer.parseInt(br.readLine());
                    Library library2 = em2.find(Library.class, lId2);
                    if(library2 != null){
                        Book book2 = new Book(bName,bAuthor,bPublisher,bPrice,date,library2);
                        BookDao.create(book2);
                        em2.getTransaction().commit();
                    }
                    break;
                case 3:
                    System.out.println("3 UPDATE LIBRARY");
                    System.out.println("Enter library id to update");
                    int lId3 = Integer.parseInt(br.readLine());
                    LibraryDao.update(lId3);

                    break;
                case 4:
                    System.out.println("4 UPDATE BOOK");
                    System.out.println("Enter bookId to update");
                    int bId4 = Integer.parseInt(br.readLine());
                    BookDao.update(bId4);

                    break;
                case 5:
                    System.out.println("5 DELETE LIBRARY");
                    System.out.println("Enter lIbraryId to delete");
                    int lId5 = Integer.parseInt(br.readLine());
                    LibraryDao.delete(lId5);

                    break;
                case 6:
                    System.out.println("6 DELETE BOOK");
                    System.out.println("Enter bookId to delete");
                    int bId6 = Integer.parseInt(br.readLine());
                    BookDao.delete(bId6);

                    break;
                case 7:
                    System.out.println("7 SELECT ALL LIBRARY");
                    LibraryDao.selectAll();
                    break;
                case 8:
                    System.out.println("8 SELECT ALL BOOK");
                    BookDao.selectAll();
                    break;
                case 9:
                    System.out.println("9 SELECT BY ID LIBRARY");
                    System.out.println("Enter Library id to select");
                    int lId9 = Integer.parseInt(br.readLine());
                    LibraryDao.selectById(lId9);
                    break;
                case 10:
                    System.out.println("10 SELECT BY ID BOOK");
                    System.out.println("Enter bookId to select");
                    int bId9 = Integer.parseInt(br.readLine());
                    BookDao.selectById(bId9);
                    break;
                case 11:
                    System.out.println("11 GET BOOKS WHOSE PRICE >= 400");
                    QueriesDao.query1();
                    break;
                case 12:
                    System.out.println("12 GET BOOKS AS THEIR PUBLISHER");
                    QueriesDao.query2();
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
