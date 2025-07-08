package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@RestController
public class StoredIntController {

    private static final String FILE_PATH = System.getProperty("java.io.tmpdir") + "/stored-int.txt";

    @GetMapping("/stored-int")
    public Integer getStoredInt() {
        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                // Lire le nombre depuis le fichier
                String content = Files.readString(Path.of(FILE_PATH));
                return Integer.parseInt(content.trim());
            } else {
                // Générer un nombre aléatoire entre 0 et 1000
                int randomNumber = new Random().nextInt(1001);

                // Écrire le nombre dans le fichier
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(String.valueOf(randomNumber));
                }

                return randomNumber;
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture ou écriture du fichier.", e);
        }
    }
}
