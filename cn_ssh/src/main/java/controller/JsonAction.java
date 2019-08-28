package controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.Servlet;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonAction extends ActionSupport
        implements SessionAware, ApplicationAware, RequestAware, CookiesAware {
    protected Map<String,Object> session ;
    protected Map<String,Object> request;
    protected Map<String,Object> application;
    protected Map<String,String> cookies;

    @Override
    public void setCookiesMap(Map<String, String> var1) {
        this.cookies = var1;
    }
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

    /**
     * 添加session
     * @param key
     * @param value
     */
    public void addSession(String key,String value){
        ServletActionContext.getRequest().getSession().setAttribute(key,value);
    }

    /**
     * 添加cookie
     * @param key
     * @param value
     */
    public void addCookie(String key,String value){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        ServletActionContext.getResponse().addCookie(cookie);
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
     * 信息展示给用户
     * @param msg
     */
    protected void setResult(String msg,int status){
        result.put("status",status);
        result.put("msg", msg);
        result.put("data",null);
    }

    /**
     * 异常信息展示给用户
     * @param e
     */
    protected void setResult(Throwable e){
        this.setResult(e.getMessage(),1);
    }

}
