package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SupplierDaoMemTest {

    private SupplierDaoMem test;

    @BeforeEach
    void setup() {
        test = SupplierDaoMem.getInstance();
        test.getAll().clear();
    }

    Supplier test1 = new Supplier(1, "test1", "test description1");
    Supplier test2 = new Supplier(2, "test2", "test description2");


    @Test
    void testAddSupplier() {
        test.add(test1);
        test.add(test2);
        assertEquals(2, test.getAll().size());
    }

    @Test
    void testFindSupplierById() {
        test.add(test1);
        test.add(test2);
        assertEquals(test1, test.find(1));
    }


    @Test
    void testRemoveSupplierById() {
        test.add(test1);
        test.add(test2);
        test.remove(2);
        assertEquals(1, test.getAll().size());
    }

    @Test
    void testGetAllSupplier() {
        test.add(test1);
        test.add(test2);
        assertEquals(2, test.getAll().size());
    }
}