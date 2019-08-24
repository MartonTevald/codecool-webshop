package com.codecool.shop.controller;

import com.codecool.shop.user.implentation.PasswordUtils;
import com.codecool.shop.user.implentation.UserDao;
import com.codecool.shop.user.implentation.UserDaoJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {



    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        UserDao userJdbc = new UserDaoJdbc();


        String username = request.getParameter("login-username");
        String password = request.getParameter("login-password");
        String hashedPwd = null;

        String salt = PasswordUtils.getSalt();

        if (username != null) {
            hashedPwd = userJdbc.find(username).getPassword();
        }
        boolean passwordMatch = PasswordUtils.verifyUserPassword(password, hashedPwd, salt);


        if (passwordMatch) {
            HttpSession newSession = request.getSession(true);

            int userId = userJdbc.find(username).getId();

            newSession.setAttribute("userID", username);

            response.sendRedirect("/");
        }else {

                response.sendRedirect("/");
        }
    }
}


