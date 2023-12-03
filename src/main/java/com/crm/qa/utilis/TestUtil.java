package com.crm.qa.utilis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	public static FileInputStream ip;
	public static XSSFWorkbook WB;
	public static XSSFSheet Sheet;

	public static final int PAGE_LOAD_TIMEOUT = 20;
	public static final int IMPLICIT__WAIT = 20;
	public static final int SCRIPT_TIMEOUT = 20;

	public void switchToFrame() {
		driver.switchTo().frame("viewport");
	}


	public static Object[][] readExcelDataForCRM(String sheetName) throws Exception {
		ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\testdata\\CRM.xlsx");
		WB = new XSSFWorkbook(ip);
		Sheet = WB.getSheet(sheetName);
		int rows = Sheet.getLastRowNum();
		int cols = Sheet.getRow(0).getLastCellNum();

		Object[][] DataObj = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow DataRow = Sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell DataCell = DataRow.getCell(j);

				CellType cellType = DataCell.getCellType();

				switch (cellType) {
				case STRING:
					DataObj[i][j] = DataCell.getStringCellValue();
					break;
				case NUMERIC:
					DataObj[i][j] = Integer.toString((int) DataCell.getNumericCellValue());
					break;
				case BOOLEAN:
					DataObj[i][j] = DataCell.getBooleanCellValue();
					break;
				default:
					DataObj[i][j] = "";
					break;
				}
			}
		}
		return DataObj;

	}
public static void takeScreenShotAtFailure() throws IOException{
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String currentDir = System.getProperty("user.dir");
	FileUtils.copyFile(scrFile, new File(currentDir+"/screenshots"+System.currentTimeMillis()+ ".png"));
}

}
