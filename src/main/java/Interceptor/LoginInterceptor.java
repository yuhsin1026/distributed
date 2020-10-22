package Interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
       // System.out.println("The Interceptor is on.");
        if (request.getSession().getAttribute("Cur_User")!=null) {
            return true;
        }
      //  System.out.println("Unlogin, Login first");
         request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
     //  response.sendRedirect("login");
        return false;
    }
}