package dao;


import entity.User;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Resource
    HibernateTemplate hibernateTemplate;
    @Override
    public int addUser(User user) {
        Object id = hibernateTemplate.save(user);
        if(id != null){
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteUser(User user) {
        hibernateTemplate.delete(user);
        return 0;
    }

    @Override
    public int updateUser(User user){
        User user1 = hibernateTemplate.get(User.class,user.getUserId());
        user1.setUserName(user.getUserName());
        user1.setExplaination(user.getExplaination());
        return 0;
    }
    @Override
    public int updatePassword(User user){
        User user1 = hibernateTemplate.get(User.class,user.getUserId());
        user1.setPassword(user.getPassword());
        return 0;
    }

    @Override
    public User findUserById(String id) {
        return hibernateTemplate.get(User.class,id);
    }

    /**
     * 用于查找是否有相同的name
     * @param name
     * @return 1
     */
    @Override
    public int findUserByName(String name) {
        String hql = "from User where user_name = ?";
        List<User> users =  hibernateTemplate.find(hql,name);
        if(users.isEmpty()){
            return 0;
        }
        return 1;
    }
}
