package com.own.test.test;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;


public class LogTest {
    public static void main(String[] args) {

    }

    @Test
    public void testLoger() {
        final Logger logger = Logger.getLogger("TestErrOut");
        logger.debug(" This is debug!!!");
        logger.info(" This is info!!!");
        logger.warn(" This is warn!!!");
        logger.error(" This is error!!!");
        logger.fatal(" This is fatal!!!");
        
    }

}
