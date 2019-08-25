package controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.HashMap;
import java.util.Map;

public abstract class JsonAction extends ActionSupport
        implements SessionAware, ApplicationAware, RequestAware {
    protected Map<String,Object> session ;
    protected Map<String,Object> request;
    protected Map<String,Object> application;
    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    //定义全局返回值json和SUCCESS,ERROR一样的作用。
    public static final String JSON = "json";

    protected Map<String,Object> result = new HashMap<String,Object>();
    public Map<String, Object> getResult() {
        return result;
    }
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
    /**
     * 正确返回值
     * @param object
     */
    protected void setResult (Object object){
        result.put("status",0);
        result.put("msg", "");
        result.put("data",object);
    }

    /**
     * 错误信息展示给用户
     * @param error
     */
    protected void setResult(String error){
        result.put("status",0);
        result.put("msg", error);
        result.put("data",null);
    }

    /**
     * 异常信息展示给用户
     * @param e
     */
    protected void setResult(Throwable e){
        this.setResult(e.getMessage());
    }
}
