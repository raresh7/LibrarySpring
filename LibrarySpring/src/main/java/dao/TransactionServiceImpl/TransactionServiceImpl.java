package dao.TransactionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import dao.TransactionService;
import entities.Transaction;

@Stateful
public class TransactionServiceImpl implements TransactionService {

//    protected TransactionServiceImpl(){}
    
    @PersistenceContext(unitName="LibrarySpring")
    private EntityManager entityManager;
    
    @Override
    public List<Transaction> returnActiveTrans(List<Transaction> trans) {
        for(int i=0;i<trans.size();i++){
            if(trans.get(i).getDateOfReturn() != null){
                trans.remove(i);
                i--;
            }
        }
        return trans;
    }    
    
    @Override
    public void returnBooks(List<Integer> ids, LocalDate dateOfReturn) {
        List <Transaction> trans = new ArrayList<Transaction>();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction().begin();
        trans = entitymanager.createQuery(
                "SELECT u FROM Transaction u "
                + "WHERE u.id IN (:ids)")
                .setParameter("ids", ids)
                .getResultList(); 
        for(int i = 0; i<trans.size();i++){
            trans.get(i).setDateOfReturn(dateOfReturn);
        }
        entitymanager.getTransaction().commit();
        entitymanager.close( );
        emfactory.close( );
        
    }

    @Override
    public List<Transaction> getAllActive() {
        List <Transaction> trans = new ArrayList<Transaction>();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        trans = entitymanager.createQuery(
                "SELECT u FROM Transaction u "
                + "WHERE u.dateOfReturn is null")
                .getResultList(); 
        entitymanager.close( );
        emfactory.close( );
        return trans;
    }

    @Override
    public List<Transaction> getAll() {
        List <Transaction> trans = new ArrayList<Transaction>();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        trans = entitymanager.createQuery(
                "SELECT u FROM Transaction u")
                .getResultList(); 
        entitymanager.close( );
        emfactory.close( );
        return trans;
    }

    @Override
    public void deleteTransaction(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction().begin();
        Transaction trans = entitymanager.find(Transaction.class, id);
        entitymanager.remove(trans);
        entitymanager.getTransaction().commit();
        entitymanager.close( );
        emfactory.close( );
        }

    @Override
    public Transaction getTransaction(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction().begin();
        Transaction trans = entitymanager.find(Transaction.class, id);
        entitymanager.getTransaction().commit();
        entitymanager.close( );
        emfactory.close( );
        return trans;
        }
    
    public void createTransaction(Transaction trans){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        
        entitymanager.getTransaction( ).begin( );
        entitymanager.persist( trans );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );
    }

    @Override
    public void updateTransaction(Transaction trans) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        Transaction fetchedTrans = (Transaction)entitymanager.createQuery(
                "SELECT u FROM Transaction u WHERE u.id = :transId")
                .setParameter("transId", trans.getId())
                .getSingleResult();
        fetchedTrans.setBook(trans.getBook());
        fetchedTrans.setUser(trans.getUser());
        fetchedTrans.setDateOfBorrow(trans.getDateOfBorrow());
        fetchedTrans.setExpectedDateOfReturn(trans.getExpectedDateOfReturn());
        fetchedTrans.setDateOfReturn(trans.getDateOfReturn());
        entitymanager.getTransaction( ).commit( );
        entitymanager.close( );
        emfactory.close( );
        
    } 

}
