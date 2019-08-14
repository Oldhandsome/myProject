package test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.NoteService;

public class ParentClass {
    public ApplicationContext ac;
    public void ParentClass() {
        ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
    }
}
