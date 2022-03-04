import app.entities.AddressBook;
import app.entities.BuddyInfo;
import org.junit.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class JPATest {
//    @Test
//    public void buddyTest() {
//        // Creating objects representing some products
//        BuddyInfo buddy1 = new BuddyInfo("carl", "5 main street", "1234567");
//
//        BuddyInfo buddy2 = new BuddyInfo("alice", "5 main street", "1234567");
//
//        // Connecting to the database through EntityManagerFactory
//        // connection details loaded from persistence.xml
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
//
//        EntityManager em = emf.createEntityManager();
//
//        // Creating a new transaction
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        // Persisting the product entity objects
//        em.persist(buddy1);
//        em.persist(buddy2);
//
//        tx.commit();
//
//        // Querying the contents of the database using JPQL query
//        Query q = em.createQuery("SELECT p FROM BuddyInfo p");
//
//        @SuppressWarnings("unchecked")
//        List<BuddyInfo> results = q.getResultList();
//
//        System.out.println("List of BuddyInfos\n----------------");
//
//        for (BuddyInfo b : results) {
//            System.out.println("name: " + b.getName() + ", address: " + b.getAddress() + ", phonenum: " + b.getPhoneNumber());
//        }
//
//        // Closing connection
//        em.close();
//
//        emf.close();
//    }
//
//    @Test
//    public void addressTest() {
//        // Creating objects representing some products
//        BuddyInfo buddy1 = new BuddyInfo("carl", "5 main street", "1234567");
//        BuddyInfo buddy2 = new BuddyInfo("alice", "5 main street", "1234567");
//
//        AddressBook book = new AddressBook();
//        ArrayList<BuddyInfo> list = new ArrayList<>();
//        list.add(buddy1);
//        list.add(buddy2);
//        book.setBuddies(list);
//
//        // Connecting to the database through EntityManagerFactory
//        // connection details loaded from persistence.xml
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
//
//        EntityManager em = emf.createEntityManager();
//
//        // Creating a new transaction
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        // Persisting the product entity objects
//        em.persist(book);
//
//        tx.commit();
//
//        // Querying the contents of the database using JPQL query
//        Query q = em.createQuery("SELECT p FROM AddressBook p");
//
//        @SuppressWarnings("unchecked")
//        List<AddressBook> results = q.getResultList();
//
//        System.out.println("List of AddressBooks\n----------------");
//
//        for (AddressBook b : results) {
//            System.out.println("entities.AddressBook: " + b.getBuddies());
//        }
//
//        // Closing connection
//        em.close();
//
//        emf.close();
//    }
}