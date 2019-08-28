package service;

import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.RandomUtil;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @Override
    @Transactional
    public User checkUser(String id,String password) throws RuntimeException{
        User user = userDao.findUserById(id);
        if (user == null){
            throw new RuntimeException(id+"账号不存在");
        }else{
            if(password.equals(user.getPassword()))
                return user;
        }
        throw new RuntimeException("密码错误");
    }

    @Override
    @Transactional
    public User register(User user) throws RuntimeException{
        String id = null;
        do {
            id = RandomUtil.random();
        }while(userDao.findUserById(id) != null);
        user.setUserId(id);
        int i = userDao.addUser(user);
        if(i == 0)
            return user;
        throw new RuntimeException("用户未成功创建");
    }

    @Override
    @Transactional
    public User updatePassword(User user) throws RuntimeException{
        if(userDao.findUserById(user.getUserId()) != null){
            int i = userDao.updatePassword(user);
            if(i == 0){
                return user;
            }
        }
        throw new RuntimeException("密码修改失败");
    }

    @Override
    @Transactional
    public User updateInfo(User user) throws RuntimeException{
        if(userDao.findUserById(user.getUserId()) != null){
            int i = userDao.updateUser(user);
            if(i == 0){
                return user;
            }
        }
        throw new RuntimeException("用户信息未成功修改");
    }
}
