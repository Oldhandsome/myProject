package test;

import dao.UserDao;
import entity.User;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest extends ParentTest{
    private UserDao userDao;
    @Before
    public void init(){
        userDao = getApplicationContext().getBean("userDaoImpl",UserDao.class);
    }
    @Test
    public void testAdd(){
        User user = new User();
        user.setUserId("44444");
        user.setUserName("荏苒");
        user.setPassword("123456");
        user.setAuthoritativeCode("11111111");
        user.setExplaination("这个人很懒");
        int  i = userDao.addUser(user);
        System.out.println(i);
    }
    @Test
    public void testFindByName(){
        String name = "荏苒";
        System.out.println(userDao.findUserByName(name));
    }
    @Test
    public void testDelete(){
        String id = "44444";
        User user = userDao.findUserById(id);
        userDao.deleteUser(user);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId("44444");
        user.setUserName("过隙");
        user.setExplaination("任时光匆匆，我只在乎你");
        System.out.println(userDao.updateUser(user));
    }
    @Test
    public void testUpdatePassword(){
        User user = new User();
        user.setUserId("44444");
        user.setPassword("123");
        userDao.updatePassword(user);
    }
}
