package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderStatus;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.user.implentation.SessionUtil;
import com.codecool.shop.user.implentation.UserDaoJdbc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = Cart.getInstance();
        SessionUtil sessionUtil = new SessionUtil();
        String userName = sessionUtil.readFromSession(req, "userID");
        float sum = cart.getSumOfPrice();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("sum", sum);
        context.setVariable("user", userName);

        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        doGet(req, resp);
        Cart cart = Cart.getInstance();
        SessionUtil sessionUtil = new SessionUtil();
        UserDaoJdbc userDaoJDBC = new UserDaoJdbc();
        OrderDaoJdbc orderDaoJBDC = new OrderDaoJdbc();
        String userName = sessionUtil.readFromSession(req, "userID");


        int userId = userDaoJDBC.getUserId(userName);
        orderDaoJBDC.updateOrderStatus(userId, OrderStatus.CHECKED.toString(), OrderStatus.NEW.toString());

        HashMap<Product, Integer> c = cart.getCart();

        String name = req.getParameter("firstname");
        String email = req.getParameter("email");

        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String phone = req.getParameter("phone");

        userDaoJDBC.addUserDetails(phone, address, city, state, zip, userId);
    }
}