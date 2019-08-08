package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductDao productDataStore = ProductDaoMem.getInstance();
        List<Product> pod = Cart.getInstance().getProducts();

        context.setVariable("numberOfProducts", Cart.getInstance().getNumberOfProducts());
        context.setVariable("cartHash", Cart.getInstance().getCart());
        context.setVariable("cart", pod);
        context.setVariable("products", productDataStore.getAll());

        context.setVariable("sum", Cart.getInstance().getSumOfPrice());

        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        List<Product> products = productDataStore.getAll();

        Cart cart = Cart.getInstance();
        int productID = Integer.parseInt(req.getParameter("addToCartButton"));
        Product prod = productDataStore.find(productID);

        cart.addToCart(prod);


        resp.sendRedirect(req.getContextPath() + "/");


    }
}