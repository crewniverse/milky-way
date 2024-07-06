package woowacourse.crewniverse.milkyway.googleclient;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;
import woowacourse.crewniverse.milkyway.googleclient.config.GoogleCredentialsProperties;

public class GoogleCredentials {

    private GoogleCredentials() {
    }

    public static Credential authorize(final GoogleCredentialsProperties googleCredentialsProperties)
            throws IOException, GeneralSecurityException {
        InputStream in = GoogleCredentials.class
                .getResourceAsStream(googleCredentialsProperties.getCredentialsFilePath());
        if (in == null) {
            throw new FileNotFoundException(
                    "Resource not found: " + googleCredentialsProperties.getCredentialsFilePath()
            );
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                GsonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS_READONLY);
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(
                new File(googleCredentialsProperties.getTokenDirectoryPath())
        );
        GoogleAuthorizationCodeFlow flow = new Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                clientSecrets,
                scopes
        ).setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setPort(googleCredentialsProperties.getReceiverPort())
                .build();

        return new AuthorizationCodeInstalledApp(flow, receiver)
                .authorize("user");
    }
}
