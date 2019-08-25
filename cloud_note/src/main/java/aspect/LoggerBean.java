package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerBean {
    @Before("within(service..*)")
    public void logController(){
        System.out.println("aop功能注入到Service中！");
    }
}
