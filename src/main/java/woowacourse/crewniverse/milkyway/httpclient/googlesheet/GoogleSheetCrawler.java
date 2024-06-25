package woowacourse.crewniverse.milkyway.httpclient.googlesheet;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import woowacourse.crewniverse.milkyway.config.SheetOption;
import woowacourse.crewniverse.milkyway.httpclient.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.response.AttendanceSheetResponse;
import woowacourse.crewniverse.milkyway.response.AttendanceSheetResponses;

public class GoogleSheetCrawler implements AttendanceCrawler {
    private final Sheets sheetsService;
    private final SheetOption sheetOption;

    public GoogleSheetCrawler(final Sheets sheetsService,
                              final SheetOption sheetOption) {
        this.sheetsService = sheetsService;
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

    private List<List<Object>> crawl(String spreadsheetId, String range) throws IOException {
        List<List<Object>> values = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute()
                .getValues();

        if (values == null || values.isEmpty()) {
            throw new RuntimeException("데이터 가져오는데 실패했어요 ㅜ ㅅ ㅜ");
        }

        return values;
    }
}
