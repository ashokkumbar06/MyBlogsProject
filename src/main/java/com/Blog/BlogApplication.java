package com.Blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

    @Bean
    public ModelMapper modleMapper() {
        return new ModelMapper();
        //When ever we use external libreary this method step is mandatory
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("    A        SSSSS      H       H      OOOO      K      K  ");
        System.out.println("   A A      S     S     H       H     O    O     K    K    ");
        System.out.println("  AAAAA     S     s     H H H H H     O    O     K  K      ");
        System.out.println(" A     A    S     S     H       H     O    O     K    K    ");
        System.out.println("A       A    SSSSS      H       H      OOOO      K      K  ");
    }
}