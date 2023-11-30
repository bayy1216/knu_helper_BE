package com.reditus.knuhelper.core.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseConfig(
    @Value("\${serviceAccountKeyPath}")
    val serviceAccountKeyPath: String
) {

    @PostConstruct
    fun init() {
        val serviceAccount = FileInputStream(serviceAccountKeyPath)
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)
    }

    @Bean
    fun firebaseMessaging(): FirebaseMessaging =
        FirebaseMessaging.getInstance()

}