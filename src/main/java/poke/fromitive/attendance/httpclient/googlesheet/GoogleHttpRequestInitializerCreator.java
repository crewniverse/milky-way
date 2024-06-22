package poke.fromitive.attendance.httpclient.googlesheet;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class GoogleHttpRequestInitializerCreator {
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private final NetHttpTransport netHttpTransport;
    private final JsonFactory jsonFactory;

    public GoogleHttpRequestInitializerCreator(final NetHttpTransport netHttpTransport, final JsonFactory jsonFactory) {
        this.netHttpTransport = netHttpTransport;
        this.jsonFactory = jsonFactory;
    }

    public HttpRequestInitializer create(int connectTimeoutMillisecond, int readTimeoutMillisecond) throws IOException {
        return setHttpTimeout(setCredentialInitializer(), connectTimeoutMillisecond, readTimeoutMillisecond);
    }

    private HttpRequestInitializer setCredentialInitializer() throws IOException {
        GoogleClientSecrets clientSecrets = loadClientSecrets();
        GoogleAuthorizationCodeFlow flow = buildAuthorizationCodeFlow(clientSecrets);
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private GoogleClientSecrets loadClientSecrets() throws IOException {
        InputStream in = GoogleHttpRequestInitializerCreator.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        return GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
    }

    private GoogleAuthorizationCodeFlow buildAuthorizationCodeFlow(GoogleClientSecrets clientSecrets) throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(
                netHttpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
    }

    private HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer, int connectTimeoutMillisecond, int readTimeoutMillisecond) {
        return request -> {
            requestInitializer.initialize(request);
            request.setConnectTimeout(connectTimeoutMillisecond);  // 3 minutes connect timeout
            request.setReadTimeout(readTimeoutMillisecond);  // 3 minutes read timeout
        };
    }
}
