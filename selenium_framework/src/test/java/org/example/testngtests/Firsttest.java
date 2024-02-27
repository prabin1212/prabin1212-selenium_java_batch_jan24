package org.example.testngtests;

import org.testng.annotations.*;

public class Firsttest {

    @BeforeSuite
    public void beforesuite(){
        System.out.println("beforesuite");
    }

    @BeforeClass
    public void beforeclass(){
        System.out.println("beforclass");
    }

    @BeforeTest
    public void beforetest(){
        System.out.println("beforetest");
    }

    @BeforeMethod
    public void beforemethod(){
        System.out.println("beforemethod");
    }


    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @AfterTest
    public void aftertest(){
        System.out.println("aftertest");
    }

    @AfterMethod
    public void aftermethod(){
        System.out.println("aftermethod");
    }

    @AfterClass
    public void afterclass(){
        System.out.println("afterclass");
    }

    @AfterSuite
    public void aftersuite(){
        System.out.println("beforesuite");
    }


}

