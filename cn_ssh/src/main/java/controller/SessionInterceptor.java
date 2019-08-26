package controller;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SessionInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String,Object> map = actionInvocation.getInvocationContext().getSession();
        if(actionInvocation instanceof JsonAction){
            ((JsonAction) actionInvocation).setSession(map);
        }
//        System.out.println(ServletActionContext.getRequest().getRequestURL());
        String result = actionInvocation.invoke();
        return result;
    }
}
