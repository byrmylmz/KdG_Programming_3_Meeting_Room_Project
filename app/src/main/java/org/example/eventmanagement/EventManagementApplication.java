package org.example.eventmanagement;

import org.example.eventmanagement.presentation.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EventManagementApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(EventManagementApplication.class, args);
        context.getBean(Menu.class).showMenu();
        context.close();
    }


}
