package com.miguelprojects;

import com.miguelprojects.repository.GradeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsultasJPA {

    public static void main(String[] args) {
        SpringApplication.run(ConsultasJPA.class, args);
    }

//    @Bean
//    CommandLineRunner runner(GradeRepository repository) {
//        return args -> {
//
//            repository.findScoreGreaterThan50();
//
//        };
//    }
}
