package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.PasswordUtils;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/register.html", context, resp.getWriter());

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