package service;

import entity.User;
import util.NoteResult;

public interface UserService {
	public NoteResult<User> checkLogin(String id,String password);
	public NoteResult<String> register(User user);
	public NoteResult checkPwd(String user_id,String pwd);
	public NoteResult changePwd(String user_id,String password);
}
