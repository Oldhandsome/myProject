package test;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

import javax.xml.transform.Result;

public class UserServiceTest extends ParentTest {
    private UserService userService;
    @Before
    public void init(){
        userService  = getApplicationContext().getBean("userServiceImpl",UserService.class);
    }
    @Test
    public void testCheckUser(){
        String id = "79519";
        String password= "123456";
        userService.checkUser(id,password);
    }
    @Test
    public void tsetRegister(){
        User user = new User();
        user.setUserId("11111");
        user.setUserName("小周");
        user.setPassword("123456");
        user.setAuthoritativeCode("11111111");
        user.setExplaination("这个人很漂亮");
        System.out.println(userService.register(user));
    }
    @Test
    public void testUpdatePassword(){
        User user = new User();
        user.setUserId("11111");
        user.setUserName("小周");
        user.setPassword("1234567890");
        System.out.println(userService.updatePassword(user));;
    }
    @Test
    public void testUpdateInfo(){
        User user = new User();
        user.setUserId("11111");
        user.setUserName("小周");
        user.setExplaination("这个人不仅漂亮，还很能干");
        System.out.println(userService.updateInfo(user));;
    }
}
