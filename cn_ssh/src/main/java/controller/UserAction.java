package controller;

import entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UserService;
import util.NoteUtil;

import javax.annotation.Resource;

//多线程接受，单线程保存
@Controller
@Scope("prototype")
public class UserAction extends JsonAction {
    @Resource(name = "userServiceImpl")
    private UserService userService;
    private String username;
    private String password;
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String toLogin(){
        return  SUCCESS;
    }
    public String execute(){
        System.out.println(username + " " + password);
        System.out.println(NoteUtil.md5(password));
        User user = userService.checkUser(username, NoteUtil.md5(password));
        addCookie("user_id",user.getUserId());
        addSession("user_id",user.getUserId());
        addSession("user_name",user.getUserName());
        setResult(user);
        return JSON;
    }
    public String toIndex(){
        return SUCCESS;
    }
}
