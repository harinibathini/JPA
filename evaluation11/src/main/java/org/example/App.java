package org.example;

import dao.AccountDao;
import dao.BankDao;
import dao.UserDao;
import entities.Account;
import entities.Bank;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
            System.out.println("____________________________");
            System.out.println("1 BANK");
            System.out.println("2 USER");
            System.out.println("3 ACCOUNT");
            System.out.println("4 QUERY1");
            System.out.println("5 QUERY2");
            System.out.println("6 QUERY3");
            System.out.println("0 EXIT");
            System.out.println("____________________________");
            System.out.println("Enter choice");
            int choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1:
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.println("1 BANK");
                        System.out.println("____________________________");
                        System.out.println("1 CREATE");
                        System.out.println("2 UPDATE");
                        System.out.println("3 DELETE");
                        System.out.println("4 SELECT ALL");
                        System.out.println("5 SELECT BY ID");
                        System.out.println("0 EXIT");
                        System.out.println("____________________________");
                        System.out.println("Enter choice");
                        int choice1 = Integer.parseInt(br.readLine());
                        switch (choice1) {
                            case 1:
                                System.out.println("CREATE BANK");
                                System.out.println("Enter Bname");
                                String bName11 = br.readLine();
                                System.out.println("Enter BIFSC");
                                int bifsc11 = Integer.parseInt(br.readLine());
                                Bank bank11 = new Bank();
                                bank11.setBankName(bName11);
                                bank11.setBankIFSC(bifsc11);
                                BankDao.create(bank11);
                                break;
                            case 2:
                                System.out.println(" UPDATE");
                                System.out.println("Enter bank id to update");
                                int bId12 = Integer.parseInt(br.readLine());
                                BankDao.update(bId12);
                                break;
                            case 3:
                                System.out.println("DELETE");
                                System.out.println("Enter bank Id to delete");
                                int bId13 = Integer.parseInt(br.readLine());
                                BankDao.delete(bId13);
                                break;
                            case 4:
                                System.out.println("SELECT ALL Bank");
                                BankDao.selectAll();
                                break;
                            case 5:
                                System.out.println("SELECT BY ID");
                                System.out.println("Enter bank id to select");
                                int bId15 = Integer.parseInt(br.readLine());
                                BankDao.selectById(bId15);
                                break;
                            case 0:
                                flag1=false;
                                System.out.println("exit");
                                break;
                            default:
                                System.out.println("Enter valid choice");
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean flag2 = true;
                    while (flag2) {
                        System.out.println("2 USER");
                        System.out.println("____________________________");
                        System.out.println("1 CREATE");
                        System.out.println("2 UPDATE");
                        System.out.println("3 DELETE");
                        System.out.println("4 SELECT ALL");
                        System.out.println("5 SELECT BY ID");
                        System.out.println("0 EXIT");
                        System.out.println("____________________________");
                        System.out.println("Enter choice");
                        int choice2 = Integer.parseInt(br.readLine());
                        switch (choice2) {
                            case 1:
                                EntityManager em21 = emf.createEntityManager();
                                em21.getTransaction().begin();
                                System.out.println(" INSERT User");

                                System.out.println("Enter name");
                                String name21 = br.readLine();
                                System.out.println("Enter Aadhaar");
                                long aadhaar21 = Long.parseLong(br.readLine());
                                System.out.println("Enter Pan");
                                long pan21 = Long.parseLong(br.readLine());
                                System.out.println("Enter address");
                                String address21 = (br.readLine());
                                System.out.println("Enter Balance");
                                int balance21 = Integer.parseInt(br.readLine());
                                System.out.println("Enter phone");
                                int phone21 = Integer.parseInt(br.readLine());
                                System.out.println("Enter status: prime/np");
                                String status21 = br.readLine();
                                System.out.println("Enter accountType: savings/current/joint");
                                String aType21 = br.readLine();
                                String firmName21 = "NA";
                                //String gst = 0;
                                //int gstId21 = Integer.parseInt(gst);
                                int gstId21 = 0;
                                boolean statusType = false;
                                if(aType21.equalsIgnoreCase("current")){
                                    System.out.println("Enter firmName");
                                    firmName21 = br.readLine();
                                    System.out.println("Enter GSTId");
                                    gstId21 = Integer.parseInt(br.readLine());
                                    statusType = true;
                                }
                                System.out.println("Enter Bank id to insert in bank");
                                int bId21 = Integer.parseInt(br.readLine());
                                Bank bank21 = em21.find(Bank.class, bId21);
                                if(bank21 != null) {
                                    User user21 = new User();
                                    user21.setName(name21);
                                    user21.setAadhaar(aadhaar21);
                                    user21.setPan(pan21);
                                    user21.setAddress(address21);
                                    user21.setBalance(balance21);
                                    user21.setPhone(phone21);
                                    user21.setStatus(status21);
                                    if (statusType == true) {
                                        user21.setFirmName(firmName21);
                                        user21.setGstId(gstId21);
                                    }
                                    user21.setBank(bank21);
                                    UserDao.create(user21);
                                    em21.getTransaction().commit();
                                }
                                break;
                            case 2:
                                System.out.println(" UPDATE");
                                System.out.println("Enter user id to update");
                                int uId22 = Integer.parseInt(br.readLine());
                                UserDao.update(uId22);

                                break;
                            case 3:
                                System.out.println("DELETE");
                                System.out.println("Enter user Id to delete");
                                int uId23 = Integer.parseInt(br.readLine());
                                UserDao.delete(uId23);
                                break;
                            case 4:
                                System.out.println("SELECT ALL User");
                                UserDao.selectAll();
                                break;
                            case 5:
                                System.out.println("SELECT BY ID");
                                System.out.println("Enter user id to select");
                                int uId25 = Integer.parseInt(br.readLine());
                                UserDao.selectById(uId25);
                                break;
                            case 0:
                                flag2=false;
                                System.out.println("exit");
                                break;
                            default:
                                System.out.println("Enter valid choice");
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean flag3 = true;
                    while (flag3) {
                        System.out.println("3 ACCOUNT");
                        System.out.println("____________________________");
                        System.out.println("1 CREATE");
                        System.out.println("2 UPDATE");
                        System.out.println("3 DELETE");
                        System.out.println("4 SELECT ALL");
                        System.out.println("5 SELECT BY ID");
                        System.out.println("0 EXIT");
                        System.out.println("____________________________");
                        System.out.println("Enter choice");
                        int choice3 = Integer.parseInt(br.readLine());
                        switch (choice3) {
                            case 1:
                                EntityManager em31 = emf.createEntityManager();
                                em31.getTransaction().begin();
                                System.out.println(" INSERT Account");

                                System.out.println("Enter Account type:  current/savings/joint");
                                String aType31 = br.readLine();
                                System.out.println("Enter account accountNo:");
                                int aNo31 = Integer.parseInt(br.readLine());
                                System.out.println("Enter account Date");
                                //Date aDate31 = ;
                                Date aDate31 = new Date();
                                System.out.println("Enter Bank id to insert in bank");
                                int bId31 = Integer.parseInt(br.readLine());

                                System.out.println("Enter user id to link with account");
                                int uId31 = Integer.parseInt(br.readLine());

                                Bank bank31 = em31.find(Bank.class, bId31);
                                User user31 = em31.find(User.class, uId31);
                                if(bank31 != null && user31 != null){
                                    Account account31 = new Account();
                                    account31.setAccountType(aType31);
                                    account31.setAccountNo(aNo31);
                                    account31.setAccountDate(aDate31);
                                    account31.setBank(bank31);
                                    List<User> list31 = new ArrayList<>();
                                    list31.add(user31);
                                    account31.setUsers(list31);

                                    AccountDao.create(account31);
                                    em31.getTransaction().commit();
                                }


                                break;
                            case 2:
                                System.out.println(" UPDATE");
                                System.out.println("Enter account id to update");
                                int aId32 = Integer.parseInt(br.readLine());
                                AccountDao.update(aId32);
                                break;
                            case 3:
                                System.out.println("DELETE");
                                System.out.println("Enter account Id to delete");
                                int aId33 = Integer.parseInt(br.readLine());
                                AccountDao.delete(aId33);
                                break;
                            case 4:
                                System.out.println("SELECT ALL User");
                                AccountDao.selectAll();
                                break;
                            case 5:
                                System.out.println("SELECT BY ID");
                                System.out.println("Enter account id to select");
                                int aId35 = Integer.parseInt(br.readLine());
                                AccountDao.selectById(aId35);
                                break;

                            case 0:
                                flag3=false;
                                System.out.println("exit");
                                break;
                            default:
                                System.out.println("Enter valid choice");
                                break;
                        }
                    }
                    break;
                case 4:
                    EntityManager em = emf.createEntityManager();
                    CriteriaBuilder cb = em.getCriteriaBuilder();
                    CriteriaQuery<User> query = cb.createQuery(User.class);
                    Root<User> userRoot = query.from(User.class);
                    Join<User, Account> accountJoin = userRoot.join("accounts");

                    LocalDateTime targetDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
                    Date targetDate = java.sql.Timestamp.valueOf(targetDateTime);

                    query.select(userRoot)
                            .where(cb.lessThan(accountJoin.get("accountDate"), targetDate));

                    TypedQuery<User> typedQuery = em.createQuery(query);
                    List<User> users = typedQuery.getResultList();
                    System.out.println("Number of users with accounts before 1st Jan 2023: " + users.size());

                    for (User user : users) {
                        System.out.println("User: " + user.getName());
                        for (Account account : user.getAccounts()) {
                            System.out.println("Account Date: " + account.getAccountDate());
                        }
                    }

                    em.close();

//                    EntityManager em4 = emf.createEntityManager();
//                    em4.getTransaction().begin();
//
//                    System.out.println("4 QUERY1");
//                    System.out.println("Fetch acc date before 1 jan 23");
//
//                    LocalDateTime targetDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
//                    Date targetDate = java.sql.Timestamp.valueOf(targetDateTime);
//
//                    List<User> users = em4.createQuery("SELECT u FROM User u JOIN u.accounts a WHERE a.accountDate > :targetDate", User.class)
//                            .setParameter("targetDate", targetDate)
//                            .getResultList();
//
//                    System.out.println("Number of users with accounts before 1st Jan 2023: " + users.size());
//
//                    for (User user : users) {
//                        System.out.println("User: " + user.getName());
//                        for (Account account : user.getAccounts()) {
//                            System.out.println("Account Date: " + account.getAccountDate());
//                        }
//                    }
//
//                        em4.getTransaction().commit();
//                    em4.close();

                    break;
                case 5:
                    EntityManager em5 = emf.createEntityManager();
                    em5.getTransaction().begin();

                    CriteriaBuilder cb5 = em5.getCriteriaBuilder();
                    CriteriaUpdate<User> updateQuery = cb5.createCriteriaUpdate(User.class);
                    Root<User> userRoot5 = updateQuery.from(User.class);

                    updateQuery.set(userRoot5.get("status"), "prime")
                            .where(cb5.greaterThan(userRoot5.get("balance"), 100000));

                    int updatedCount5 = em5.createQuery(updateQuery).executeUpdate();

                    System.out.println("Number of users updated to prime: " + updatedCount5);

//                    System.out.println("5 QUERY2");
//                    System.out.println("bal>100000 to prime");
//
//                    List<User> usersToUpdate5 = em5.createQuery("SELECT u FROM User u WHERE u.balance > 100000", User.class).getResultList();
//
//                    for (User user : usersToUpdate5) {
//                        user.setStatus("prime");
//                        em5.merge(user);
//                    }

                    em5.getTransaction().commit();
                    em5.close();

                    break;
                case 6:
                    System.out.println("6 QUERY3");
                    System.out.println("i current - <100000 bal-250");
                    System.out.println("ii savings - <100000 bal-150");


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
