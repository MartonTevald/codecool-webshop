package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderStatus;
import com.codecool.shop.model.Product;
import com.codecool.shop.user.implentation.SessionUtil;
import com.codecool.shop.user.implentation.UserDao;
import com.codecool.shop.user.implentation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userJdbc = new UserDaoJdbc();
        SessionUtil sessionUtil = new SessionUtil();
        String userName = sessionUtil.readFromSession(req, "userID");
        OrderDaoJdbc orderDaoJBDC = new OrderDaoJdbc();
        Cart cart = Cart.getInstance();

        int userId = userJdbc.getUserId(userName);
        int userActiveOrderId = orderDaoJBDC.getOrderIdByUserId(userId, OrderStatus.NEW.toString());

        for (Map.Entry<Product, Integer> map : cart.getCart().entrySet()) {
            int prod_id = map.getKey().getId();
            Integer quantity = map.getValue();
            orderDaoJBDC.addOrderItem(prod_id, quantity, userActiveOrderId);
        }

        if (cart.getCart().size() > 0) {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("user", userName);
            engine.process("product/checkout.html", context, resp.getWriter());
        } else if (cart.getCart().size() == 0) {
            resp.sendRedirect("/");
        }
    }

}