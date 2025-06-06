package com.alcozone.lib;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.FileInputStream;

@ApplicationScoped
@Startup
public class FirebaseInitializer {

    @ConfigProperty(name = "firebase.sa.path")
    String firebase_sa_path;

    @PostConstruct
    public void init() {
        if(FirebaseApp.getApps().isEmpty()){
            try {
                FileInputStream serviceAccount = new FileInputStream(firebase_sa_path);
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