package poke.fromitive.attendance.httpclient.googlesheet;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import poke.fromitive.attendance.config.SheetOption;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;
import poke.fromitive.attendance.response.AttendanceSheetResponse;
import poke.fromitive.attendance.response.AttendanceSheetResponses;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class GoogleSheetCrawler implements AttendanceCrawler {
    private static final String APPLICATION_NAME = "Google Sheet Crawler";
    private final HttpRequestInitializer httpRequestInitializer;
    private final JsonFactory jsonFactory;
    private final NetHttpTransport netHttpTransport;
    private final SheetOption sheetOption;

    public GoogleSheetCrawler(final HttpRequestInitializer httpRequestInitializer,
                              final JsonFactory jsonFactory,
                              final NetHttpTransport netHttpTransport,
                              final SheetOption sheetOption) {
        this.httpRequestInitializer = httpRequestInitializer;
        this.jsonFactory = jsonFactory;
        this.netHttpTransport = netHttpTransport;
        this.sheetOption = sheetOption;
    }

    @Override
    public AttendanceSheetResponses execute() throws GeneralSecurityException, IOException {
        List<AttendanceSheetResponse> attendanceSheetResponses = new ArrayList<>();
        final List<List<Object>> values = crawl(sheetOption.getSpreadSheetId(), sheetOption.getSpreadSheetRange());

        for (List row : values) {
            String rawDate = (String) row.get(sheetOption.getDateColumnNumber());
            String name = (String) row.get(sheetOption.getCrewNameColumnNumber());
            String campusName = (String) row.get(sheetOption.getCampusNameColumnNumber());
            attendanceSheetResponses.add(new AttendanceSheetResponse(rawDate, name, campusName));
        }

        return new AttendanceSheetResponses(attendanceSheetResponses);
    }

    private List<List<Object>> crawl(String spreadsheetId, String range) throws GeneralSecurityException, IOException {
        Sheets service = new Sheets.Builder(netHttpTransport, jsonFactory, httpRequestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();

        List<List<Object>> values = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute()
                .getValues();

        if (values == null || values.isEmpty()) {
            throw new RuntimeException("데이터 가져오는데 실패했어요 ㅜ ㅅ ㅜ");
        }

        return values;
    }
}
