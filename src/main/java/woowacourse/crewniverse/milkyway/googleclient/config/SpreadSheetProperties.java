package woowacourse.crewniverse.milkyway.googleclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sheet")
public class SpreadSheetProperties {
    private final String spreadSheetId;
    private final String spreadSheetRange;
    private final int dateColumnNumber;
    private final int crewNameColumnNumber;
    private final int campusNameColumnNumber;

    public SpreadSheetProperties(final String spreadSheetId,
                                 final String spreadSheetRange,
                                 final int dateColumnNumber,
                                 final int crewNameColumnNumber,
                                 final int campusNameColumnNumber) {
        this.spreadSheetId = spreadSheetId;
        this.spreadSheetRange = spreadSheetRange;
        this.dateColumnNumber = dateColumnNumber;
        this.crewNameColumnNumber = crewNameColumnNumber;
        this.campusNameColumnNumber = campusNameColumnNumber;
    }

    public String getSpreadSheetId() {
        return spreadSheetId;
    }

    public String getSpreadSheetRange() {
        return spreadSheetRange;
    }

    public int getDateColumnNumber() {
        return dateColumnNumber;
    }

    public int getCrewNameColumnNumber() {
        return crewNameColumnNumber;
    }

    public int getCampusNameColumnNumber() {
        return campusNameColumnNumber;
    }
}
