package dao.UserServiceImpl;

import java.util.List;

import dao.UserService;
import entities.User;

public class UserServiceProxy implements UserService{
    UserService implementation;
    
    protected UserServiceProxy(UserService implementation){
        this.implementation = implementation;
    }

    @Override
    public List<User> getAll() {
        return implementation.getAll();
    }

    @Override
    public User getUser(int id) {
        return implementation.getUser(id);
    }

    @Override
    public User getUser(String name) {
        return implementation.getUser(name);
    }

    @Override
    public void persistUser() {
        implementation.persistUser();
        
    }

    @Override
    public void createUser(User user) {
        implementation.createUser(user);
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub
        
    }
}
