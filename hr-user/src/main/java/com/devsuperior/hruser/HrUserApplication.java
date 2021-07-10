package com.devsuperior.hruser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableEurekaClient
@SpringBootApplication
public class HrUserApplication implements CommandLineRunner {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public HrUserApplication(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(HrUserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("BCRYPT =" + bCryptPasswordEncoder.encode("admin"));
    }
}
