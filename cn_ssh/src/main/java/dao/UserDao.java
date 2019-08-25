package dao;

import entity.User;

public interface UserDao {
    int addUser(User user);
    int deleteUser(User user);
    int updateUser(User user);
    int updatePassword(User user);
    User findUserById(String id);
    int findUserByName(String name);
}
