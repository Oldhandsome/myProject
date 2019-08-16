package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	public int changePwd(@Param("user_id") String user_id, @Param("pwd") String pwd);
}
