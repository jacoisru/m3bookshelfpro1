package com.m3bookshelfpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BookShelf Pro — Main Application Entry Point.
 *
 * <p>This is the primary Spring Boot application class for BookShelf Pro,
 * a web-based book store catalog management system built for CST-323
 * Cloud Computing at Grand Canyon University.</p>
 *
 * <p>The application supports full CRUD operations for Books and Categories
 * and is deployed to Heroku with Aiven MySQL as the cloud database.</p>
 *
 * @author Jacob Israel
 * @author Peyton Wolf
 * @version 4.0
 */
@SpringBootApplication
public class Application {

    /**
     * Main method that bootstraps and launches the Spring Boot application.
     *
     * @param args command-line arguments passed at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
