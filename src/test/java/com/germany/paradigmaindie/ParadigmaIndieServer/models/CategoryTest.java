package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CategoryTest {
    Category category = new Category();

    @Test
    void testSetId() {
        category.setId(0L);
    }

    @Test
    void testSetName() {
        category.setName("name");
    }


    @Test
    @Disabled
    void testHashCode() {
        int result = category.hashCode();
        Assertions.assertEquals(3524, result);
    }

    @Test
    @Disabled
    void testToString() {
        String result = category.toString();
        Assertions.assertEquals("Category(id=0, name=null)", result);
    }
}

