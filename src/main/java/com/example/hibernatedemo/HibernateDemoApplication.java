package com.example.hibernatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HibernateDemoApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onInit(){


    }
}
