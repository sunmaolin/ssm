package sml.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //请求之前调用的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //从session中获取用户名
        String userName = (String) session.getAttribute("userName");
        //判断是否存在用户名，如果不存在代表未登录
        if(StringUtils.isEmpty(userName)){
            //重定向到登陆请求
            response.sendRedirect(request.getContextPath()+"/login.form");
            //阻止继续运行
            return false;
        }
        //用户名存在，代表已登陆，放行，继续运行
        return true;
    }

    @Override
    //请求之后调用的方法
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    //请求完成调用的方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
