package com.tms;

import com.tms.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ConsoleApp consoleApp = context.getBean(ConsoleApp.class);
        try {
            consoleApp.begin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}