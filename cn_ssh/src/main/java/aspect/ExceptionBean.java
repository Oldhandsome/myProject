package aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class ExceptionBean {
    @AfterThrowing(throwing = "e",pointcut = "within(service..*)")
    public void execute(Exception e){
        try{
            FileWriter fw = new FileWriter("D:\\Program Files\\intellij_workspace\\cloud_note\\error.log");
            PrintWriter pw = new PrintWriter(fw);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);
            pw.println("************************************");
            pw.println("异常类型"+e);
            pw.println("异常时间"+time);
            pw.println("**************详细信息**************");
            e.printStackTrace(pw);
            pw.close();
            fw.close();
        }catch (IOException ex) {
            System.out.println("记录类型失败！");
        }
    }
}
