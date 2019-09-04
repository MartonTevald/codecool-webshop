package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.user.implentation.SessionUtil;
import com.codecool.shop.user.implentation.UserDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductDao productDataStore = ProductDaoMem.getInstance();
        List<Product> pod = Cart.getInstance().getProducts();
        SessionUtil sessionUtil = new SessionUtil();
        String value = sessionUtil.readFromSession(req, "userID");

        context.setVariable("user", value);

        context.setVariable("numberOfProducts", Cart.getInstance().getNumberOfProducts());
        context.setVariable("cartHash", Cart.getInstance().getCart());
        context.setVariable("cart", pod);
        context.setVariable("products", productDataStore.getAll());

        context.setVariable("sum", Cart.getInstance().getSumOfPrice());

        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = new ProductDaoJdbc();

        Cart cart = Cart.getInstance();




        String addToCart = req.getParameter("addToCartButton");
        if (addToCart != null) {
            int productID = Integer.parseInt(addToCart);
            Product prod = productDataStore.find(productID);
            cart.addToCart(prod);
            resp.sendRedirect(req.getContextPath() + "/");
        }

        String addMoreToCart = req.getParameter("add");
        if (addMoreToCart != null) {
            int id = Integer.parseInt(addMoreToCart);
            Product product = productDataStore.find(id);
            cart.addToCart(product);
        }

            String removeFromToCart = req.getParameter("remove");
        if (removeFromToCart != null) {
            int id = Integer.parseInt(removeFromToCart);
            Product product = productDataStore.find(id);
            cart.removeFromCart(product);
        }

        resp.sendRedirect("/cart");
    }
}