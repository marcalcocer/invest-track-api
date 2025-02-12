package com.investTrack.api.google;

import com.investTrack.model.Investment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoogleSheetsInvestmentAdapter {

  // Google Sheets date time format
  private static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  public List<Object> toSheetValueRange(Investment investment) {
    log.trace("Converting to sheet value range investment {}", investment);
    var startDateTime = parseDateTime(investment.getStartDateTime());
    var endDateTime = parseDateTime(investment.getEndDateTime());

    return List.of(
        investment.getId(),
        investment.getName(),
        investment.getDescription(),
        investment.getCurrency(),
        startDateTime,
        endDateTime,
        investment.isReinvested(),
        investment.getInitialInvestedAmount(),
        investment.getReinvestedAmount(),
        investment.getProfitability());
  }

  private String parseDateTime(LocalDateTime dateTime) {
    if (dateTime == null) {
      return "";
    }
    return dateTime.format(DATE_FORMATTER);
  }
}
