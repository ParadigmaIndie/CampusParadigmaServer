package com.germany.paradigmaindie.ParadigmaIndieServer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ParadigmaIndieServerApplicationTest {


        @Test
        @Disabled(value = "postgres data bases connection refused")
        void contextLoads() {
        }

}