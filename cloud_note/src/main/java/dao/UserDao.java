package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entity.User;

@Repository("userDaoImpl")
public interface UserDao {
	public List<String> getIds();
	public List<User> findAll();
	public List<User> findByName(String name);
	public User findById(String id);
	public void insert(User user);
	public void update(User user);
	public void delete(String id);
}
