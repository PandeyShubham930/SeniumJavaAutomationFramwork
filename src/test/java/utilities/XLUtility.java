package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;
	static String path;

	public XLUtility(String path) {
		this.path = path;
	}

	// getting all rows
	public static int getRowCount(String path, String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	// getting all cols
	public static int getCellCount(String path, String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String path, String sheetName, int rownum, int colnum) throws IOException {
		// taking the data from the Excel sheet
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);// Returns the formatted value of a cell as a String
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public static void setCellData(String excellName, String sheetName, int rownum, int collnum, String data) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet =workbook.getSheet(sheetName);
		row  =sheet.getRow(rownum);
		cell =row.createCell(collnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
}
