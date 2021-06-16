package coviddashboard.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcelFile {
	public void createExcelSheet(Map<String, Object[]> data) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");

		// Create row object
		XSSFRow row;

		// Iterate over data and write to sheet
		Set<String> keyid = data.keySet();
		int rowid = 0;

		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = data.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				} else if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				}
			}
		}
		// Write the workbook in file system
		String path = new File(ApplicationConfig.EXCEL_PATH + LocalDate.now().toString() + ".xlsx").getAbsolutePath();
		FileOutputStream out = new FileOutputStream(path);

		workbook.write(out);
		out.close();
		workbook.close();
		System.out.println("Writesheet.xlsx written successfully");
	}
	
}
