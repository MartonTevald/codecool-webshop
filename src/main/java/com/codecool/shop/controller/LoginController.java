package com.codecool.shop.controller;

import com.codecool.shop.model.User;
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
            User checkUser = userJdbc.find(username);
            if (checkUser != null) {
                hashedPwd = checkUser.getPassword();
            } else {
                request.setAttribute("errorMessage", "Invalid password or username. You might try again or Register ");
                request.getRequestDispatcher("/error").forward(request, response);
            }
        }

        boolean passwordMatch = PasswordUtils.verifyUserPassword(password, hashedPwd, salt);

        if (passwordMatch) {
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("userID", username);
            response.sendRedirect("/");

        } else {
            request.setAttribute("errorMessage", "Invalid password or username. You might try again or Register ");
            request.getRequestDispatcher("/error").forward(request, response);

        }
        response.sendRedirect("/");
    }
}


