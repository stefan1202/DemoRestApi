package com.example.demorestapi.controller;

public class TestStackOverFlow {
    public static void main(String[] args) {
        TestStackOverFlow t=new TestStackOverFlow();
        t.testOverflow();

        System.out.println("Am terminat");
    }

    public boolean testOverflow(){
        return testOverflow();
    }
}
