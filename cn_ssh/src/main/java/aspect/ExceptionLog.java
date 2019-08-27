package aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Component
@Aspect
public class ExceptionLog {
    @AfterThrowing(throwing = "e",pointcut="within(service..*)")
    public void logException(Exception e) throws Throwable{
        try{
            FileWriter fw = new FileWriter("E:\\IntelliJ_workspace\\cloud_note\\error.log",true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("************************");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pw.println("time :"+sdf.format(new Date()));
            pw.println("the type of "+e);
            pw.println("detailed message：");
            e.printStackTrace(pw);

            fw.close();
            pw.close();
        }catch(Throwable ioe){
            ioe.printStackTrace();
            //将异常抛出方便后面的事务检测，回滚事务。
            throw ioe;
        }
    }
}
