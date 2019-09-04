package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.OrderStatus;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
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
import java.util.List;

@WebServlet(urlPatterns = {"/confirm"})
public class ConfirmationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionUtil sessionUtil = new SessionUtil();
        String value = sessionUtil.readFromSession(req, "userID");
        Cart cart = Cart.getInstance();

        UserDao userDaoJDBC = new UserDaoJdbc();
        int userId = userDaoJDBC.getUserId(value);
        OrderDaoJdbc orderDaoJBDC = new OrderDaoJdbc();
        orderDaoJBDC.updateOrderStatus(userId, OrderStatus.PAYED.toString(), OrderStatus.CHECKED.toString());

        cart.clearCartItems();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Order> order = OrderDaoMem.getInstance().getAll();
        Order ord = order.get(0);
        context.setVariable("user", value);
        context.setVariable("order", ord);
        engine.process("product/confirmation.html", context, resp.getWriter());
    }
}
