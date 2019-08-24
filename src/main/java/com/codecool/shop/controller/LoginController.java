package com.codecool.shop.controller;

import com.codecool.shop.dao.PasswordUtils;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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

            newSession.setAttribute("userID", userId);

            response.sendRedirect("/");
        }
        response.sendRedirect("/");
    }
}


