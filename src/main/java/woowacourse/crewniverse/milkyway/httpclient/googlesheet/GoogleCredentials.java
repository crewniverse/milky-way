package woowacourse.crewniverse.milkyway.httpclient.googlesheet;

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
import woowacourse.crewniverse.milkyway.config.GoogleCredentialsOption;

public class GoogleCredentials {

    private GoogleCredentials() {
    }

    public static Credential authorize(GoogleCredentialsOption googleCredentialsOption)
            throws IOException, GeneralSecurityException {
        InputStream in = GoogleCredentials.class.getResourceAsStream(googleCredentialsOption.getCredentialsFilePath());
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + googleCredentialsOption.getCredentialsFilePath());
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                GsonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS_READONLY);
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(
                new File(googleCredentialsOption.getTokenDirectoryPath())
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
                .setPort(googleCredentialsOption.getReceiverPort())
                .build();

        return new AuthorizationCodeInstalledApp(flow, receiver)
                .authorize("user");
    }
}
