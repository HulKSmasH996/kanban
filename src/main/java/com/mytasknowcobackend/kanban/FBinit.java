package com.mytasknowcobackend.kanban;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javax.annotation.PostConstruct;
import java.io.InputStream;

public class FBinit {
	
	@PostConstruct
    public void initialize() {
        try {

            InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("mytasknowco-9d4d5-firebase-adminsdk-e4bdm-22dd1295ec.json");
                    //new FileInputStream("path/to/serviceAccountKey.json");
           /* FileInputStream serviceAccount1 =
                    new FileInputStream("path/to/serviceAccountKey.json");*/

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mytasknow-co.firebaseio.com")
                    .build();
            if (FirebaseApp.getApps().isEmpty())
                 FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Firestore getFireBase(){
        return FirestoreClient.getFirestore();
    }


}
