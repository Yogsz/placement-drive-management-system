package com.example.campus_placement_tracker;

import com.example.campus_placement_tracker.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CampusPlacementTrackerApplication {

    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(CampusPlacementTrackerApplication.class, args);

        Test test = context.getBean(Test.class);
        System.out.println("maja");
        test.printMessage();


    }

}
