package test;


import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SHTestCase {
    ApplicationContext ctx;
    SessionFactory factory;
    @Before
    public void init(){
        String[] cfg = {"conf/spring-orm.xml"};
        ctx = new ClassPathXmlApplicationContext(cfg);
        factory = ctx.getBean("sessionFactory",SessionFactory.class);
    }
    @Test
    public void testSession(){
        Session session = factory. openSession();
        System.out.println(session);
        session.close();
    }

    @Test
    public void testHibernate(){
        //会自动关闭连接，关闭session，关闭transaction,比sesssion更加方便
        HibernateTemplate hibernateTemplate = ctx.getBean("hibernateTemplate",HibernateTemplate.class);
        User user = new User();
        user.setUserId("33333");
        user.setUserName("匆匆");
        user.setPassword("123456");
        user.setAuthoritativeCode("11111111");
        user.setExplaination("这个人很懒");
        hibernateTemplate.save(user);
    }
}
