package controller;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;
import service.UserService;
import util.NoteResult;
import util.NoteUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name="userServiceImpl")
	UserService us;
	
	@RequestMapping("/tologin.do")
	public String tologin() {
		return "tologin";
	}
	@RequestMapping("/toregister.do")
	public String toRegister(){
		return "toregister";
	}
	@ResponseBody
	@RequestMapping("/register.do")
	public NoteResult<String> register(User user){
		NoteResult<String> result = us.register(user);
		return result;
	}
	@RequestMapping("/index.do")
	public String toindex(){
		return "index";
	}

	@ResponseBody
	@RequestMapping("/checkpwd.do")
	public NoteResult checkPWD(String user_id,String pwd){
		NoteResult result = us.checkPwd(user_id, pwd);
		return result;
	}
	@ResponseBody
    @RequestMapping("/changepwd.do")
    public NoteResult changePwd(String user_id,String password){
	    NoteResult result = us.changePwd(user_id, NoteUtil.md5(password));
	    return result;
    }
	@ResponseBody
	@RequestMapping("/login.do")
	public NoteResult<User> login(User user,HttpServletRequest req,HttpServletResponse res) {
		NoteResult<User> result = us.checkLogin(user.getUser_id(), user.getPassword());
		if(result.getStatus() == 0) {
			req.getSession().setAttribute("user_id", result.getData().getUser_id());
			Cookie cookie = new Cookie("user_id", result.getData().getUser_id());
			Cookie cookie2 = new Cookie("user_name", result.getData().getUser_name());
			cookie.setMaxAge(1800);
			cookie2.setMaxAge(1800);
			//*****非常重要，cookie的保存路径不同，
			//导致有的请求不发送全部cookie，ajax请求
			//例如/cloud/note 就不能发送/cloud_note/user下的cookie
			cookie.setPath("/cloud_note");
			cookie2.setPath("/cloud_note");
			res.addCookie(cookie);
			res.addCookie(cookie2);
		}
		System.out.println(result);
		return result;
	}
	
}
