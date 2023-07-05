package com.earthquake.api.service.excel;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.response.ExcelResponse;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.shared.util.DateUtils;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateExcelFileService {


    private final UsageCountService usageCountService;

    public int generateExcel(List<EarthQuakeInfo> response, String excelName, String [] excelCell) throws IOException {

        int status = 0;
        try {

            log.info("Start to create excel file...");
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) +  excelName;
            try (FileOutputStream outputStream = new FileOutputStream(fileLocation)) {

                final Workbook workbook = new XSSFWorkbook();

                final Sheet sheet = workbook.createSheet("Turkey-List");
                log.info("Start to create sheet...");
                for (int i = 0; i <= 6; i++) {
                    sheet.setColumnWidth(i, 6000);
                }
                final CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Row header = sheet.createRow(0);
                for (int i = 0; i < excelCell.length; i++) {
                    final Cell headerCell = header.createCell(i);
                    headerCell.setCellValue(excelCell[i]);
                    headerCell.setCellStyle(headerStyle);
                }
                int rowCount = 1;
                log.info("Adding all list of element for row and column");
                for (EarthQuakeInfo info : response) {
                    final Row row = sheet.createRow(++rowCount);
                    row.createCell(0).setCellValue(info.getDate());
                    row.createCell(1).setCellValue(info.getMagnitude());
                    row.createCell(2).setCellValue(info.getDepth());
                    row.createCell(3).setCellValue(info.getLatitude());
                    row.createCell(4).setCellValue(info.getLongitude());
                    row.createCell(5).setCellValue(info.getLocationName());
                }
                workbook.write(outputStream);
                status = 200;
                log.info("Excel Status: {}", status);
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
            status = 404;
            log.error("Excel Status: {}", status);
        }
        return status;
    }

    public ExcelResponse byteArray(List<EarthQuakeInfo> response, String [] excelCell) throws IOException {
        ByteArrayInputStream byteArray = null;
        int status = 0;
        try {

            log.info("Start to create excel file...");

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                final Workbook workbook = new XSSFWorkbook();

                final Sheet sheet = workbook.createSheet("Turkey-List");
                log.info("Start to create sheet...");
                for (int i = 0; i <= 6; i++) {
                    sheet.setColumnWidth(i, 6000);
                }
                final CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Row header = sheet.createRow(0);
                for (int i = 0; i < excelCell.length; i++) {

                    final Cell headerCell = header.createCell(i);
                    headerCell.setCellValue(excelCell[i]);
                    headerCell.setCellStyle(headerStyle);
                }
                int rowCount = 1;
                log.info("Adding all list of element for row and column");
                for (EarthQuakeInfo info : response) {

                    final Row row = sheet.createRow(++rowCount);
                    final LocalDateTime localDate = DateUtils.convertToLocalDateTimeViaInstant(info.getDate());
                    row.createCell(0).setCellValue(String.valueOf(localDate));
                    row.createCell(1).setCellValue(info.getMagnitude());
                    row.createCell(2).setCellValue(info.getDepth());
                    row.createCell(3).setCellValue(info.getLatitude());
                    row.createCell(4).setCellValue(info.getLongitude());
                    row.createCell(5).setCellValue(info.getLocationName());
                }
                workbook.write(outputStream);
                byteArray = new ByteArrayInputStream(outputStream.toByteArray());
                status = 200;
                log.info("Excel Status: {}", status);
                usageCountService.accept(Constant.Request.EXCEL_EARTHQUAKE, ServiceUsageStatusType.SUCCESS);
            }
        }
        catch (IOException e) {
            usageCountService.accept(Constant.Request.EXCEL_EARTHQUAKE, ServiceUsageStatusType.SUCCESS);
            log.error(e.getMessage());
            status = 404;
            log.error("Excel Status: {}", status);
        }
        return ExcelResponse.builder()
                .byteArrayInputStream(byteArray)
                .status(status).build();
    }
}

