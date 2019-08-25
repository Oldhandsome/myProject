package service;

import entity.User;

public interface UserService {
    User checkUser(String id,String password);
    User register(User user);
    User updatePassword(User user);
    User updateInfo(User user);
}
