package controller;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.springframework.stereotype.Component;

@Component
public class ExceptionInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        try {
            //调用控制器,正常执行则会返回str
            String str = actionInvocation.invoke();
            return str;
        }catch(Exception e){
            //出现异常则会返回异常信息
            Object object = actionInvocation.getAction();
            //若是JsonAction的子类抛出的异常，
            if(object instanceof JsonAction){
                JsonAction action = (JsonAction) object;
                //调用自己的setResult方法写入异常信息。
                action.setResult(e);
                return JsonAction.JSON;
            }
            //若不是JsonAction的异常，则抛出
            throw e;
        }
    }
}
