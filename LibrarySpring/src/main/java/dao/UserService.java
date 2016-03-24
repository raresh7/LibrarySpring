package dao;

import java.util.List;

import entities.User;

public interface UserService {
    public List<User> getAll();
    public User getUser(int id);
    public User getUser(String name);
    public void persistUser();
    public void createUser(User user);
    public void deleteUser(int id);
    public void updateUser(User user);
}
