package woowacourse.crewniverse.milkyway.googleclient;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import woowacourse.crewniverse.milkyway.googleclient.config.SpreadSheetProperties;
import woowacourse.crewniverse.milkyway.service.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponse;

public class GoogleSheetCrawler implements AttendanceCrawler {
    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern(
            "yyyy. M. d a h:mm:ss", Locale.KOREA
    );
    private static final Logger log = LoggerFactory.getLogger(GoogleSheetCrawler.class);

    private final Sheets sheetsService;
    private final SpreadSheetProperties spreadSheetProperties;

    public GoogleSheetCrawler(final Sheets sheetsService,
                              final SpreadSheetProperties spreadSheetProperties) {
        this.sheetsService = sheetsService;
        this.spreadSheetProperties = spreadSheetProperties;
    }

    @Override
    public List<AttendanceSheetResponse> execute() {
        List<AttendanceSheetResponse> attendanceSheetResponses = new ArrayList<>();
        final List<List<Object>> values = crawl(spreadSheetProperties.getSpreadSheetId(),
                spreadSheetProperties.getSpreadSheetRange());

        for (List row : values) {
            String rawDate = (String) row.get(spreadSheetProperties.getDateColumnNumber());
            String name = (String) row.get(spreadSheetProperties.getCrewNameColumnNumber());
            String campusName = (String) row.get(spreadSheetProperties.getCampusNameColumnNumber());
            attendanceSheetResponses.add(
                    new AttendanceSheetResponse(LocalDate.parse(rawDate, DATE_PARSER), name, campusName)
            );
        }
        return attendanceSheetResponses;
    }

    private List<List<Object>> crawl(String spreadsheetId, String range) {
        try {
            return sheetsService.spreadsheets()
                    .values()
                    .get(spreadsheetId, range)
                    .execute()
                    .getValues();
        } catch (IOException e) {
            log.error("Failed to fetch data from spreadsheet", e);
            throw new RuntimeException("데이터 가져오는데 실패했어요 ㅜ ㅅ ㅜ");
        }
    }
}
