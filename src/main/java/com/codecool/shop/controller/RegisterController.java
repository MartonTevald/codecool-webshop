package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.PasswordUtils;
import com.codecool.shop.dao.implementation.RegisterJdbc;
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
        String check = req.getParameter("register");
        if(check.equals("true")) {

            String salt = PasswordUtils.getSalt(30);
            String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

            RegisterJdbc regJdbc = new RegisterJdbc();
            User user = new User(fullname, username, email, mySecurePassword);
            regJdbc.addUser(user);
            resp.sendRedirect("/");

        }else {
            resp.sendRedirect("/reg");
        }



    }
}