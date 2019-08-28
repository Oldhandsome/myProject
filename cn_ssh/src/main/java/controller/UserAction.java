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
    private String user_id;
    private String user_name;
    private String password;
    private String authoritative_code;
    private String explaination;
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getAuthoritative_code() {
        return authoritative_code;
    }
    public void setAuthoritative_code(String authoritative_code) {
        this.authoritative_code = authoritative_code;
    }
    public String getExplaination() {
        return explaination;
    }
    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String toLogin(){
        return  SUCCESS;
    }
    public String execute(){
        System.out.println(NoteUtil.md5(password));
        User user = userService.checkUser(user_id, NoteUtil.md5(password));
        addCookie("user_id",user.getUserId());
        addSession("user_id",user.getUserId());
        addSession("user_name",user.getUserName());
        setResult("成功登陆",0);
        return JSON;
    }
    public String toIndex(){
        return SUCCESS;
    }

    public String checkPwd(){
        userService.checkUser(user_id, NoteUtil.md5(password));
        setResult("验证成功", 0);
        return JSON;
    }
    public String changePwd(){
        User user = new User();
        user.setUserId(user_id);
        user.setPassword(NoteUtil.md5(password));
        System.out.println(user);
        userService.updatePassword(user);
        setResult("修改成功",0);
        return JSON;
    }

    public String toRegister(){
        return SUCCESS;
    }

    public String register(){
        User user = new User();
        user.setUserName(user_name);
        user.setPassword(NoteUtil.md5(password));
        user.setAuthoritativeCode(authoritative_code);
        user.setExplaination(explaination);
        userService.register(user);
        System.out.println(user);
        setResult(user.getUserId());
        return JSON;
    }
}
