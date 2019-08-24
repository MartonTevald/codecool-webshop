package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoJdbcTest {

    SupplierDao supplierDao = new SupplierDaoJdbc();
    Supplier test1 = new Supplier(1, "Lenovo", "Hardware");
    Supplier test2 = new Supplier(1, "Apple", "Hardware");
    TestUtil testUtil = new TestUtil();


    @AfterEach
    void setup() {
        testUtil.setupDatabase();
        testUtil.removeAll(testUtil.product);
        testUtil.removeAll(testUtil.prodcat);
        testUtil.removeAll(testUtil.supplier);
        testUtil.create(testUtil.queryCategory);
        testUtil.create(testUtil.querySupplier);
        testUtil.create(testUtil.queryProduct);
    }

    @Test
    void add() {
        supplierDao.add(test1);
        assertEquals(test1.getName(), supplierDao.find(1).getName());

    }

    @Test
    void find() {
        supplierDao.add(test1);
        assertEquals(test1.getName(), supplierDao.find(1).getName());
    }

    @Test
    void getAll() {
        supplierDao.add(test1);
        supplierDao.add(test2);
        assertEquals(2, supplierDao.getAll().size());
    }

    @Test
    void remove() {
        supplierDao.add(test1);
        supplierDao.add(test2);
        supplierDao.remove(2);
        assertEquals(1, supplierDao.getAll().size());
    }
}