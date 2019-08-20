package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ProductDao prodJdbc = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductDaoJdbc prodJdbc = new ProductDaoJdbc();
        SupplierDaoJdbc supplierJdbc = new SupplierDaoJdbc();
        ProductCategoryDaoJdbc prodCatJdbc = new ProductCategoryDaoJdbc();


        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierJdbc.add(amazon);
//        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierJdbc.add(lenovo);
//        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Computers and Digital content");
        supplierJdbc.add(apple);
//        supplierDataStore.add(apple);
        Supplier java = new Supplier("Mustang", "accessories");
        supplierJdbc.add(java);
//        supplierDataStore.add(java);

        //setting up a new product category

        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        prodCatJdbc.add(tablet);
//        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop computer, sometimes called a notebook computer by manufacturers, is a battery- or AC-powered personal computer generally smaller than a briefcase that can easily" +
                " be transported and conveniently used in temporary spaces such as on airplanes, in libraries, temporary offices, and at meetings.");
        prodCatJdbc.add(laptop);
        //        productCategoryDataStore.add(laptop);
        ProductCategory accessories = new ProductCategory("Accessories", "Hardware", "Other products, accessories.");
        prodCatJdbc.add(accessories);
//        productCategoryDataStore.add(accessories);

        //setting up products and printing it

        prodJdbc.add(new Product("Amazon Fire", 49, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        prodJdbc.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        prodJdbc.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        prodJdbc.add(new Product("Apple - MacBook Air 13.3\"", 999, "USD", "\n" +
                "The latest MacBook Air features a stunning Retina display with True Tone technology, Touch ID, the latest Apple-designed keyboard, and a Force Touch trackpad - all housed in a thin and light iconic wedge design made from 100 percent recycled aluminum. And with 12-hour battery life, it's a do-it-all notebook that goes all day long.", laptop, apple));
        prodJdbc.add(new Product("Lenovo ThinkPad Yoga 370 Touch Laptop", 750, "USD", "Combining power, performance, and portability, the stylish yet durable ThinkPad Yoga 370 gives you the freedom to work/play anywhere. Enjoy using it in different ways-from laptop to tablet and anything in between. Save time by writing," +
                " highlighting, or drawing directly on screen. Take delight in seeing your visuals, presentations, and movies come to life on the vibrant 13.3\" display. And then relax knowing the Yoga 370 has an all-day battery life and comes with a worldwide warranty.", laptop, lenovo));
        prodJdbc.add(new Product("Apple iPad Pro (12.9-inch, Wi-Fi, 256GB) ", 989, "USD", "mmensely powerful, portable, and capable, the 12.9-inch iPad Pro features a redesigned Retina display that is the most advanced on the planet.", tablet, apple));
        prodJdbc.add(new Product("Java 50", 9999, "USD", "The ultimate tool if it comes to change geoposition and you are a developer.", accessories, java));
    }
}
