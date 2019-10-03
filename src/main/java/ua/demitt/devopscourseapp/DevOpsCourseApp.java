package ua.demitt.devopscourseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DevOpsCourseApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DevOpsCourseApp.class, args);
    }
}
