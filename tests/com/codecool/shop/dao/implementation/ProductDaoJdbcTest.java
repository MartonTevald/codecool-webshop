package com.codecool.shop.dao.implementation;

import com.codecool.shop.database.DatabaseConnection;
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
    private ProductDao prodtest = new ProductDaoJdbc();
    private SupplierDao suppli = new SupplierDaoJdbc();
    private ProductCategoryDao prodCat = new ProductCategoryDaoJdbc();


    @BeforeEach
    void setup(){
        testUtil.setupDatabase();

        suppli.add(sup1);
        suppli.add(sup2);
        prodCat.add(cat1);
        prodCat.add(cat2);
    }

    @AfterEach
    void clearDb(){
        testUtil.removeAll(testUtil.product);
        testUtil.removeAll(testUtil.prodcat);
        testUtil.removeAll(testUtil.supplier);
        testUtil.create(testUtil.queryCategory);
        testUtil.create(testUtil.querySupplier);
        testUtil.create(testUtil.queryProduct);
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
        assertEquals(prodtest.find(1).getName(),prod1.getName());
    }

//    @Test
//    void getSupplierId() {
//        prodtest.add(prod1);
//
//    }
//
//    @Test
//    void getProdCatId() {
//        prodtest.add(prod1);
//
//    }

    @Test
    void testFindSingleProduct() {
        prodtest.add(prod1);
        prodtest.add(prod2);
        assertEquals(prodtest.find(2).getName(),prod2.getName());

    }

    @Test
    void testRemoveSingleProduct() {
        prodtest.add(prod1);
        prodtest.add(prod2);
        prodtest.remove(1);
        assertEquals(1, prodtest.getAll().size());

    }

    @Test
    void testGetAllProducts() {
        prodtest.add(prod1);
        prodtest.add(prod2);
        assertEquals(2, prodtest.getAll().size());

    }

    @Test
    void testGetBySupplier() {
        prodtest.add(prod1);
        prodtest.add(prod2);
        assertEquals(1, prodtest.getBy(sup1).size());

    }

    @Test
    void testGetByProductCategory() {
        prodtest.add(prod1);
        prodtest.add(prod2);
        assertEquals(1, prodtest.getBy(cat2).size());

    }
}