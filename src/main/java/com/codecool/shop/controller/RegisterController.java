package com.codecool.shop.controller;

import com.codecool.shop.user.implentation.PasswordUtils;
import com.codecool.shop.user.implentation.UserDaoJdbc;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/reg"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String fname = req.getParameter("firstname");
        String lname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String fullname = fname + " " + lname;
        String check = req.getParameter("registerValid");
        if (check.equals("true")) {

            String salt = PasswordUtils.getSalt();
            String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

            UserDaoJdbc regJdbc = new UserDaoJdbc();
            User user = new User(1, fullname, username, email, mySecurePassword);
            regJdbc.add(user);
            resp.sendRedirect("/");

        } else {
            resp.sendRedirect("/");
        }


    }
}