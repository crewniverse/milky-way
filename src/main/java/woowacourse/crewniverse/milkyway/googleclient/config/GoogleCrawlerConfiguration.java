package woowacourse.crewniverse.milkyway.googleclient.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woowacourse.crewniverse.milkyway.service.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.googleclient.GoogleCredentials;
import woowacourse.crewniverse.milkyway.googleclient.GoogleSheetCrawler;

@Configuration
@EnableConfigurationProperties({SpreadSheetProperties.class, GoogleCredentialsProperties.class, GoogleClientProperties.class})
public class GoogleCrawlerConfiguration {
    private final SpreadSheetProperties spreadSheetProperties;
    private final GoogleCredentialsProperties googleCredentialsProperties;
    private final GoogleClientProperties googleClientProperties;

    public GoogleCrawlerConfiguration(final SpreadSheetProperties spreadSheetProperties,
                                      final GoogleCredentialsProperties googleCredentialsProperties,
                                      final GoogleClientProperties googleClientProperties) {
        this.spreadSheetProperties = spreadSheetProperties;
        this.googleCredentialsProperties = googleCredentialsProperties;
        this.googleClientProperties = googleClientProperties;
    }

    @Bean
    AttendanceCrawler attendanceCrawler() throws GeneralSecurityException, IOException {
        Credential credential = GoogleCredentials.authorize(googleCredentialsProperties);

        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        HttpRequestInitializer initializer = request -> {
            credential.initialize(request);
            request.setReadTimeout(googleClientProperties.getReadTimeoutInMillis());
            request.setConnectTimeout(googleClientProperties.getConnectionTimeoutInMillis());
        };

        Sheets sheetsService = new Sheets.Builder(transport, gsonFactory, initializer)
                .setApplicationName(googleClientProperties.getApplicationName())
                .build();
        return new GoogleSheetCrawler(sheetsService, spreadSheetProperties);
    }
}
