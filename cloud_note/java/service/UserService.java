package service;

import entity.User;
import util.NoteResult;

public interface UserService {
	
	public NoteResult<User> checkLogin(String id,String password);
	public NoteResult<String> register(User user);
}
