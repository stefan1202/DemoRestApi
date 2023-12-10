package com.example.demorestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
//@RequestScope
public class BeanWithValues implements CommandLineRunner {
    @Value("${sda.java59}")
    private String someValue;

    @Value("${sda.java60:DefaultText}")
    private String anotherValue;

    @Autowired
    private BeanLoadedFromApplication beanLoadedFromApplication;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("I have used the value:" + someValue);
        System.out.println("I have used the anotherValue:" + anotherValue);

        System.out.println("I have used a bean with values: " + beanLoadedFromApplication);
    }
}
