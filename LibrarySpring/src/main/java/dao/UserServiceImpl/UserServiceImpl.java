package dao.UserServiceImpl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import dao.UserService;
import entities.User;

@Stateful
public class UserServiceImpl implements UserService{
    
    protected UserServiceImpl(){};
    
    @PersistenceContext(unitName="LibrarySpring")
    private EntityManager entityManager;
    
    @Override
    public List<User> getAll() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        List <User> user = entitymanager.createQuery(
                "SELECT u FROM User u")
                .getResultList(); 
        entitymanager.close( );
        emfactory.close( );
        return user;
    }

    @Override
    public User getUser(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        User user = (User)entitymanager.createQuery(
                "SELECT u FROM User u WHERE u.id = :userId")
                .setParameter("userId", id)
                .getSingleResult(); 
        int i = user.getTransaction().size();
        entitymanager.close( );
        emfactory.close( );
        return user;
    }
    @Override
    public void persistUser(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        User user = new User("user3", "ssn1", "address", true); 
        
        entitymanager.persist( user );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );
    }

    @Override
    public User getUser(String name) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        

        User user = (User)entitymanager.createQuery(
                "SELECT u FROM User u WHERE u.name = :userName")
                .setParameter("userName", name)
                .getSingleResult(); 
        int i = user.getTransaction().size();
        entitymanager.close( );
        emfactory.close( );
        return user;
    }

    @Override
    public void createUser(User user) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        
        entitymanager.getTransaction( ).begin( );
        entitymanager.persist( user );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );
        
    }

    @Override
    public void deleteUser(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        User user = (User)entitymanager.createQuery(
                "SELECT u FROM User u WHERE u.id = :userId")
                .setParameter("userId", id)
                .getSingleResult();
        entitymanager.remove( user );
        entitymanager.getTransaction( ).commit( );
        entitymanager.close( );
        emfactory.close( );
    }

    @Override
    public void updateUser(User user) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LibrarySpring");
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        User fetchedUser = (User)entitymanager.createQuery(
                "SELECT u FROM User u WHERE u.id = :userId")
                .setParameter("userId", user.getId())
                .getSingleResult();
        fetchedUser.setAddress(user.getAddress());
        fetchedUser.setIsAdmin(user.getIsAdmin());
        fetchedUser.setName(user.getName());
        fetchedUser.setSsn(user.getSsn());
        entitymanager.getTransaction( ).commit( );
        entitymanager.close( );
        emfactory.close( );
        
    }
    
}
