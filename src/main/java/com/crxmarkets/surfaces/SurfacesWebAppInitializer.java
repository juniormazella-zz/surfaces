package com.crxmarkets.surfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This class is the starting point of the system,
 * it contains the main method that is responsible for initializing the context
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class SurfacesWebAppInitializer {

    /**
     * This method is responsible for starting the SpringBoot
     *
     * @param args
     *         arguments that can be passed by parameters on the command line
     */
    public static void main(String[] args) {
        SpringApplication.run(SurfacesWebAppInitializer.class, args);
    }

}
