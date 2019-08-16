package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import util.NoteResult;
import util.NoteUtil;
import util.RandomUtil;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDaoImpl")
	private UserDao dao;

	@Override
	public NoteResult<User> checkLogin(String id, String password) {
		NoteResult<User> result = new NoteResult<User>();
		User user = dao.findById(id);
		if(user == null) {
			//1表示数据库中没有该账号
			result.setStatus(1);
			result.setMsg("账号不存在");
			return result;
		}
		String md5Password = NoteUtil.md5(password);
		if(!user.getPassword().equals(md5Password)) {
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		//账号和密码都正确
		result.setStatus(0);
		result.setMsg("登陆成功");
		result.setData(user);
		return result;
	}

	@Override
	public NoteResult checkPwd(String user_id,String pwd){
		NoteResult result = new NoteResult();
		User user = dao.findById(user_id);
		if(!NoteUtil.md5(pwd).equals(user.getPassword())){
			result.setStatus(1);
			result.setMsg("密码错误");
		}else{
			result.setStatus(0);
			result.setMsg("密码正确");
		}
		return result;
	}
	public NoteResult changePwd(String user_id,String password){
		NoteResult result = new NoteResult();
		int rows = dao.changePwd(user_id,password);
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("修改成功");
		}
		else{
			result.setStatus(1);
			result.setMsg("修改出现错误");
		}
		return result;
	}
	@Override
	public NoteResult<String> register(User user) {
		NoteResult<String> result = new NoteResult<String>();
		String id = null;
		List<String> ids = dao.getIds();
		do {
			id = RandomUtil.random();
		}while(ids.contains(id));
		user.setUser_id(id);
		String md5Password = NoteUtil.md5(user.getPassword());
		String md5Code = NoteUtil.md5(user.getAuthoritative_code());
		user.setAuthoritative_code(md5Code);
		user.setPassword(md5Password);
		dao.insert(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		result.setData(id);
		return result;
	}
}
