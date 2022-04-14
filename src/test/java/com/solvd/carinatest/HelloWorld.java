package com.solvd.carinatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;

public class HelloWorld implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void helloWorld() {
        LOGGER.info("Hello World!");
    }

}