package aspect;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AudiBean {
    /**
     * 功能:Component：将该bean对象添加进bean容器，Aspect开启AOP注解标记
     * Around表示什么时候切入，within表示切谁！
     * 用point对象去调用别的对象，固定写法
     * @param point
     * @return
     */
    @Around("within(service..*)")
    public Object audit(ProceedingJoinPoint point){
        Object obj = null;
        try{
            long start = System.currentTimeMillis();
            //下面这一句可以看成是Service的方法的执行 “过程”;每个serice方法都会在proceed方法中执行
            obj = point.proceed();
            long end = System.currentTimeMillis();
            //获得Service的名称
            String str = point.getSignature().toString();
            System.out.println(str);
            System.out.println("耗时  "+(end-start));
        }catch (Throwable e){
            e.printStackTrace();
        }
        return obj;
    }
}
