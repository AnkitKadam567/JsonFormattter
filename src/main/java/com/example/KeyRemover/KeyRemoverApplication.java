package com.example.KeyRemover;

import com.example.KeyRemover.service.RemoveKeyFromJsonPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeyRemoverApplication implements CommandLineRunner {

    @Autowired
    RemoveKeyFromJsonPayload removeKeyFromJsonPayload;

    public static void main(String[] args) {
        SpringApplication.run(KeyRemoverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        removeKeyFromJsonPayload.removeKey();
    }
}
