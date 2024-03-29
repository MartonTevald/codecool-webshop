package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.user.implentation.SessionUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = new ProductDaoJdbc();
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc();
        SupplierDao supplierDataStore = new SupplierDaoJdbc();
        SessionUtil sessionUtil = new SessionUtil();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String value = sessionUtil.readFromSession(req, "userID");
        context.setVariable("user", value);

        context.setVariable("category", productCategoryDataStore.getAll());
        context.setVariable("products", productDataStore.getAll());
        context.setVariable("supplier", supplierDataStore.getAll());
        context.setVariable("categoryFilter", productCategoryDataStore.getAll());
        context.setVariable("supplierFilter", supplierDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = new ProductDaoJdbc();
        SupplierDao supplierDataStore = new SupplierDaoJdbc();
        ProductCategoryDao productCatDataStore = new ProductCategoryDaoJdbc();
        SessionUtil sessionUtil = new SessionUtil();
        List<Product> products = null;
        String value = sessionUtil.readFromSession(req, "userID");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("user", value);
        context.setVariable("categoryFilter", productCatDataStore.getAll());
        context.setVariable("supplierFilter", supplierDataStore.getAll());


        int catId = Integer.parseInt(req.getParameter("catId"));
        int supId = Integer.parseInt(req.getParameter("supId"));
        String search = req.getParameter("search");

        if (!search.equals("")) {
            products = productDataStore.findBySearch(search);
            context.setVariable("category", productCatDataStore.getAll());
        } else {

            if (catId != -1 & supId == -1) {
                ProductCategory category = productCatDataStore.find(catId);
                products = productDataStore.getAll();
                context.setVariable("category", category);
            }
            if (catId == -1 & supId != -1) {
                Supplier supplier = supplierDataStore.find(supId);
                products = productDataStore.getBy(supplier);
                context.setVariable("category", productCatDataStore.getAll());
            }
            if (catId != -1 & supId != -1) {
                ProductCategory category = productCatDataStore.find(catId);
                Supplier supplier = supplierDataStore.find(supId);
                products = productDataStore.getBy(supplier);
                context.setVariable("category", category);

            }
            if (catId == -1 & supId == -1) {
                resp.sendRedirect("/");
            }
        }
        context.setVariable("products", products);
        engine.process("product/index.html", context, resp.getWriter());
    }
}
