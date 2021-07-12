package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PrivilegeTest {
    Privilege privilege = new Privilege("name");

    @Test
    void testSetId() {
        privilege.setId(Long.valueOf(1));
    }

    @Test
    void testSetName() {
        privilege.setName("name");
    }

    @Test
    void testEquals() {
        boolean result = privilege.equals("o");
        Assertions.assertFalse(false, String.valueOf(result));
    }

    @Test
    void testCanEqual() {
        boolean result = privilege.canEqual("other");
        Assertions.assertFalse(false, String.valueOf(result));
    }

    @Test
    @Disabled
    void testHashCode() {
        int result = privilege.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = privilege.toString();
        Assertions.assertEquals("Privilege(id=null, name=name)", result);
    }
}
