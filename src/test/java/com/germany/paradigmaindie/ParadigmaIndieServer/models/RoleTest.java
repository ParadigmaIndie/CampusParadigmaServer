package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

class RoleTest {
    @Mock
    Collection<Privilege> privileges;
    @InjectMocks
    Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetId() {
        role.setId(Long.valueOf(1));
    }

    @Test
    void testSetName() {
        role.setName("name");
    }

    @Test
    void testSetPrivileges() {
        role.setPrivileges(Arrays.asList(new Privilege("name")));
    }
}

