package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParentTest {

    public ApplicationContext getApplicationContext() {
        String[] conf = {"conf/spring-orm.xml","conf/spring-web.xml"};
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext(conf);
        return applicationContext;
    }
}
