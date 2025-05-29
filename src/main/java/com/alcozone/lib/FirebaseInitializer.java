package com.alcozone.lib;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.FileInputStream;

@ApplicationScoped
@Startup
public class FirebaseInitializer {
    @PostConstruct
    public void init() {
        if(FirebaseApp.getApps().isEmpty()){
            try {
                FileInputStream serviceAccount = new FileInputStream("src/main/resources/alcozone-e21b0-firebase-adminsdk-fbsvc-23105318fc.json");
                FirebaseOptions options= FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully");
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize Firebase", e);
            }
        }
    }
}