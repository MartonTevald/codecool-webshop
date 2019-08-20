package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
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
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCatDataStore = ProductCategoryDaoMem.getInstance();

        ProductDaoJdbc prodJdbc = new ProductDaoJdbc();
        ProductCategoryDaoJdbc prodCatJdbc = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc suppJdbc = new SupplierDaoJdbc();


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("category", productCategoryDataStore.getAll());
        context.setVariable("category", prodCatJdbc.getAll());
//        context.setVariable("products", productDataStore.getAll());
        context.setVariable("products", prodJdbc.getAll());
//        context.setVariable("supplier", supplierDataStore.getAll());
        context.setVariable("supplier", suppJdbc.getAll());
//        context.setVariable("categoryFilter", productCatDataStore.getAll());
        context.setVariable("categoryFilter", prodCatJdbc.getAll());
//        context.setVariable("supplierFilter", supplierDataStore.getAll());
        context.setVariable("supplierFilter", suppJdbc.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCatDataStore = ProductCategoryDaoMem.getInstance();
        List<Product> products = null;


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categoryFilter", productCatDataStore.getAll());
        context.setVariable("supplierFilter", supplierDataStore.getAll());

        int filterCat = Integer.parseInt(req.getParameter("filterCat"));
        int filterSup = Integer.parseInt(req.getParameter("filterSup"));

        ProductCategory category = productCatDataStore.find(filterCat);
        Supplier supplier = supplierDataStore.find(filterSup);


        if (filterSup != -1 || filterCat != -1) {
            if (category != null & supplier == null) {
                products = productDataStore.getBy(category);
                context.setVariable("category", category);

            } else if (supplier != null & category == null) {
                products = productDataStore.getBy(supplier);
                context.setVariable("category", ProductCategoryDaoMem.getInstance().getAll());

            } else if (category != null & supplier != null) {
                products = productDataStore.getBy(supplier);
                context.setVariable("category", category);
                context.setVariable("supplier", supplier);
            }
            context.setVariable("products", products);

        } else {
            resp.sendRedirect("/");

        }
        engine.process("product/index.html", context, resp.getWriter());


    }

}
