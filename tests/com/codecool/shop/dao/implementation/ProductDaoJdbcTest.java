package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest extends DatabaseConnection {

    TestUtil testUtil = new TestUtil();
    private ProductDao prodtest;
    private SupplierDao suppli;
    private ProductCategoryDao prodCat;

    @BeforeEach
    void setup(){
        testUtil.removeAll(testUtil.product);
        testUtil.removeAll(testUtil.prodcat);
        testUtil.removeAll(testUtil.supplier);
        testUtil.create(testUtil.queryCategory);
        testUtil.create(testUtil.querySupplier);
        testUtil.create(testUtil.queryProduct);
        prodtest = new ProductDaoJdbc();
        suppli = new SupplierDaoJdbc();
        prodCat = new ProductCategoryDaoJdbc();
        suppli.add(sup1);
        suppli.add(sup2);
        prodCat.add(cat1);
        prodCat.add(cat2);
    }

    @AfterEach
    void clearDb(){
        //removeAll();
    }


    ProductCategory cat1 = new ProductCategory(1,"Tablet","Hardware","very nice");
    ProductCategory cat2 = new ProductCategory(2,"Laptop","Hardware","very nice");
    Supplier sup1 = new Supplier(1, "sup1", "supDesc1");
    Supplier sup2 = new Supplier(2, "sup2", "supDesc2");
    Product prod1 = new Product(1, "name1", 1, "USD", "desc1", cat1, sup1);
    Product prod2 = new Product(2, "name2", 2, "USD", "desc2", cat2, sup2);


    @Test
    void testAddSingleProductJDBC() {
        prodtest.add(prod1);
        assertSame(prodtest.find(1),prod1);
    }

    @Test
    void getSupplierId() {
    }

    @Test
    void getProdCatId() {
    }

    @Test
    void find() {
    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getBy() {
    }

    @Test
    void getBy1() {
    }
}