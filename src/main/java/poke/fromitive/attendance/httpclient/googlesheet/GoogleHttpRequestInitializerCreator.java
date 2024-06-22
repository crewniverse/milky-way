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
import poke.fromitive.attendance.config.ApiClientOption;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class GoogleHttpRequestInitializerCreator {
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    private final NetHttpTransport netHttpTransport;
    private final JsonFactory jsonFactory;
    private final ApiClientOption apiClientOption;

    public GoogleHttpRequestInitializerCreator(final NetHttpTransport netHttpTransport,
                                               final JsonFactory jsonFactory,
                                               final ApiClientOption apiClientOption) {
        this.netHttpTransport = netHttpTransport;
        this.jsonFactory = jsonFactory;
        this.apiClientOption = apiClientOption;
    }

    public HttpRequestInitializer create() throws IOException {
        return setHttpTimeout(setCredentialInitializer());
    }

    private HttpRequestInitializer setCredentialInitializer() throws IOException {
        GoogleClientSecrets clientSecrets = loadClientSecrets();
        GoogleAuthorizationCodeFlow flow = buildAuthorizationCodeFlow(clientSecrets);
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private GoogleClientSecrets loadClientSecrets() throws IOException {
        InputStream in = GoogleHttpRequestInitializerCreator.class.getResourceAsStream(apiClientOption.getCredentialsFilePath());
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + apiClientOption.getCredentialsFilePath());
        }
        return GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
    }

    private GoogleAuthorizationCodeFlow buildAuthorizationCodeFlow(GoogleClientSecrets clientSecrets) throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(
                netHttpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(apiClientOption.getTokenDirectoryPath())))
                .setAccessType("offline")
                .build();
    }

    private HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer) {
        return request -> {
            requestInitializer.initialize(request);
            request.setConnectTimeout(apiClientOption.getConnectTimeoutMs());  // 3 minutes connect timeout
            request.setReadTimeout(apiClientOption.getReadTimeoutMs());  // 3 minutes read timeout
        };
    }
}
