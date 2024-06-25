package woowacourse.crewniverse.milkyway.config;

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
import woowacourse.crewniverse.milkyway.httpclient.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.httpclient.googlesheet.GoogleCredentials;
import woowacourse.crewniverse.milkyway.httpclient.googlesheet.GoogleSheetCrawler;

@Configuration
@EnableConfigurationProperties({SheetOption.class, GoogleCredentialsOption.class, GoogleClientOption.class})
public class GoogleCrawlerConfiguration {
    private final SheetOption sheetOption;
    private final GoogleCredentialsOption googleCredentialsOption;
    private final GoogleClientOption googleClientOption;

    public GoogleCrawlerConfiguration(final SheetOption sheetOption,
                                      final GoogleCredentialsOption googleCredentialsOption,
                                      final GoogleClientOption googleClientOption) {
        this.sheetOption = sheetOption;
        this.googleCredentialsOption = googleCredentialsOption;
        this.googleClientOption = googleClientOption;
    }

    @Bean
    AttendanceCrawler attendanceCrawler() throws GeneralSecurityException, IOException {
        Credential credential = GoogleCredentials.authorize(googleCredentialsOption);

        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        HttpRequestInitializer initializer = request -> {
            credential.initialize(request);
            request.setReadTimeout(googleClientOption.getReadTimeoutInMillis());
            request.setConnectTimeout(googleClientOption.getConnectionTimeoutInMillis());
        };

        Sheets sheetsService = new Sheets.Builder(transport, gsonFactory, initializer)
                .setApplicationName(googleClientOption.getApplicationName())
                .build();
        return new GoogleSheetCrawler(sheetsService, sheetOption);
    }
}
