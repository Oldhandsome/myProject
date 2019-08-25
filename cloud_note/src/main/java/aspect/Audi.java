package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class Audi {

    @Around("within(service..*)")
    public Object process(ProceedingJoinPoint point) throws Throwable{
        System.out.println("切面开始-1");
        long oldtime = new Date().getTime();
        Object obj = point.proceed();
        long newtime = new Date().getTime();
        System.out.println("切面结束-2");
        System.out.println("耗时"+(newtime - oldtime));
        return obj;
    }
}
