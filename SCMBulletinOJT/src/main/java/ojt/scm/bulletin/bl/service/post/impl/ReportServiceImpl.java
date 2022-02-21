package ojt.scm.bulletin.bl.service.post.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ojt.scm.bulletin.bl.dto.post.PostDto;
import ojt.scm.bulletin.bl.service.post.ReportService;

/**
 * <h2>ReportServiceImpl Class</h2>
 * <p>
 * Process for Displaying ReportServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
@Primary
public class ReportServiceImpl implements ReportService {
    /**
     * <h2>downloadExcel</h2>
     * <p>
     * Function to export CSV Form of Data from Database
     * </p>
     * 
     * @param postList List<PostDto>
     * @throws IOException
     * @throws ParseException
     */
    @SuppressWarnings("resource")
    @Override
    public void downloadExcel(List<PostDto> postList) throws IOException, ParseException {
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("PostList");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = { "Title", "Description", "Status", "Created User", "Created At" };
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            cell.setCellStyle(headerCellStyle);
        }
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        final String OLD_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
        final String NEW_FORMAT = "dd/MM/yyyy";
        int rowNumber = 1;
        DateFormat inputFormat = new SimpleDateFormat(OLD_FORMAT);
        DateFormat outputFormat = new SimpleDateFormat(NEW_FORMAT);
        String date;
        for (PostDto post : postList) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(post.getTitle());
            row.createCell(1).setCellValue(post.getDescription());
            row.createCell(2).setCellValue("ON");
            if (post.getStatus() == 0) {
                row.createCell(2).setCellValue("OFF");
            }
            row.createCell(3).setCellValue(post.getCreatedUserName());
            Date old_date = inputFormat.parse(post.getCreatedAt().toString());
            date = outputFormat.format(old_date);
            row.createCell(4).setCellValue(date);
        }
        for (int i = 0; i < columnHeaders.length; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream fileOut;
        fileOut = new FileOutputStream(
                System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis() + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
}