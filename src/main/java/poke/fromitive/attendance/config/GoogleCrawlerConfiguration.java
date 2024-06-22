package poke.fromitive.attendance.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;
import poke.fromitive.attendance.httpclient.googlesheet.GoogleHttpRequestInitializerCreator;
import poke.fromitive.attendance.httpclient.googlesheet.GoogleSheetCrawler;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class GoogleCrawlerConfiguration {

    private final SheetOption sheetOption;

    public GoogleCrawlerConfiguration(final SheetOption sheetOption) {
        this.sheetOption = sheetOption;
    }

    @Bean
    AttendanceCrawler crawler() throws GeneralSecurityException, IOException {
        final GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        final NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleHttpRequestInitializerCreator creator = new GoogleHttpRequestInitializerCreator(netHttpTransport, jsonFactory);
        final HttpRequestInitializer httpRequestInitializer = creator.create(3000, 3000);
        return new GoogleSheetCrawler(httpRequestInitializer, jsonFactory, netHttpTransport, sheetOption);
    }
}
