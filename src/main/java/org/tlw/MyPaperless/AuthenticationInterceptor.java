package org.tlw.MyPaperless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tlw.MyPaperless.models.User;
import org.tlw.MyPaperless.models.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException{

        List<String> authorizedPages = Arrays.asList("/dashboard","/removeExperiment","/experiment/addIntro","/experiment/addIntro/save","/experiment/addReagent","/experiment/procedure",
                "/experiment/procedure/save","/experiment/observations","/experiment/observations/save",
                "/experiment/conclusion","/experiment/conclusion/save");

        if(authorizedPages.contains(request.getRequestURI())){

            boolean isLoggedIn = false;
            User user;
            Integer userId = (Integer)(request.getSession().getAttribute("id"));

            if(userId != null) {
               user = userDao.findOne(userId);

               if(user !=null){
                   isLoggedIn = true;
               }

            }

            if(!isLoggedIn){
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
