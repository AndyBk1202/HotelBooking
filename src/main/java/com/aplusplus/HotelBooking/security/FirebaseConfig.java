package com.aplusplus.HotelBooking.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Value("${firebase.service-account-key}")
    private String firebaseServiceAccountKeyPath;
    @PostConstruct
    public void init(){
        try{
            //InputStream serviceAccount = new ClassPathResource("serviceAccountKey.json").getInputStream();
            FileInputStream serviceAccount = new FileInputStream(firebaseServiceAccountKeyPath);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("hotelbooking-fc85a.appspot.com")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("FirebaseApp đã được khởi tạo thành công.");
            }
        }
        catch (Exception e){
            throw new RuntimeException("Không thể khởi tạo FirebaseApp", e);
        }
    }
}