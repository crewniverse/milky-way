package poke.fromitive.attendance.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;
import poke.fromitive.attendance.httpclient.googlesheet.GoogleHttpRequestInitializerCreator;
import poke.fromitive.attendance.httpclient.googlesheet.GoogleSheetCrawler;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
@Profile("!test")
@EnableConfigurationProperties({SheetOption.class, ApiClientOption.class})
public class GoogleCrawlerConfiguration {
    private final SheetOption sheetOption;
    private final ApiClientOption apiClientOption;

    public GoogleCrawlerConfiguration(final SheetOption sheetOption, final ApiClientOption apiClientOption) {
        this.sheetOption = sheetOption;
        this.apiClientOption = apiClientOption;
    }

    @Bean
    AttendanceCrawler attendanceCrawler() throws GeneralSecurityException, IOException {
        final GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        final NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleHttpRequestInitializerCreator creator = new GoogleHttpRequestInitializerCreator(netHttpTransport, jsonFactory, apiClientOption);
        final HttpRequestInitializer httpRequestInitializer = creator.create();
        return new GoogleSheetCrawler(httpRequestInitializer, jsonFactory, netHttpTransport, sheetOption);
    }
}
