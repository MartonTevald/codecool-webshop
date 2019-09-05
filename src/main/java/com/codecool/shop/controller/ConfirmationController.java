package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderStatus;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.user.implentation.SessionUtil;
import com.codecool.shop.user.implentation.UserDao;
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

@WebServlet(urlPatterns = {"/confirm"})
public class ConfirmationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionUtil sessionUtil = new SessionUtil();
        String value = sessionUtil.readFromSession(req, "userID");
        UserDao userDaoJDBC = new UserDaoJdbc();
        OrderDaoJdbc orderDaoJBDC = new OrderDaoJdbc();
        Cart cart = Cart.getInstance();

        int userId = userDaoJDBC.getUserId(value);
        orderDaoJBDC.updateOrderStatus(userId, OrderStatus.PAID.toString(), OrderStatus.CHECKED.toString());

        int orderId = orderDaoJBDC.getOrderIdByStatusAndUserID(userId, OrderStatus.PAID.toString());


        cart.clearCartItems();
        HashMap<Product, Integer> orderedItems = orderDaoJBDC.getAllOrderItems(orderId);
        Order ord = new Order(orderedItems);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("user", value);
        context.setVariable("sumTotal", ord.sumTotal());
        engine.process("product/confirmation.html", context, resp.getWriter());
    }
}
