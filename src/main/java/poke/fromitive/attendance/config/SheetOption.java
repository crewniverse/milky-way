package poke.fromitive.attendance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sheet")
public class SheetOption {
    private String spreadSheetId;
    private String spreadSheetRange;
    private int dateColumnNumber;
    private int crewNameColumnNumber;
    private int campusNameColumnNumber;

    public SheetOption() {
    }

    public SheetOption(final String spreadSheetId, final String spreadSheetRange, final int dateColumnNumber, final int crewNameColumnNumber, final int campusNameColumnNumber) {
        this.spreadSheetId = spreadSheetId;
        this.spreadSheetRange = spreadSheetRange;
        this.dateColumnNumber = dateColumnNumber;
        this.crewNameColumnNumber = crewNameColumnNumber;
        this.campusNameColumnNumber = campusNameColumnNumber;
    }

    public String getSpreadSheetId() {
        return spreadSheetId;
    }

    public void setSpreadSheetId(final String spreadSheetId) {
        this.spreadSheetId = spreadSheetId;
    }

    public String getSpreadSheetRange() {
        return spreadSheetRange;
    }

    public void setSpreadSheetRange(final String spreadSheetRange) {
        this.spreadSheetRange = spreadSheetRange;
    }

    public int getDateColumnNumber() {
        return dateColumnNumber;
    }

    public void setDateColumnNumber(final int dateColumnNumber) {
        this.dateColumnNumber = dateColumnNumber;
    }

    public int getCrewNameColumnNumber() {
        return crewNameColumnNumber;
    }

    public void setCrewNameColumnNumber(final int crewNameColumnNumber) {
        this.crewNameColumnNumber = crewNameColumnNumber;
    }

    public int getCampusNameColumnNumber() {
        return campusNameColumnNumber;
    }

    public void setCampusNameColumnNumber(final int campusNameColumnNumber) {
        this.campusNameColumnNumber = campusNameColumnNumber;
    }
}
