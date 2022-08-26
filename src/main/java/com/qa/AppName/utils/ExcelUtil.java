package com.qa.AppName.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static final String TEST_DATA_SHEET_PATH="./src/test/resources/TestData/opencartRegistrationTD.xlsx";
    private static Workbook book;
    private static Sheet sheet;

	
    
    public static Object[][] getTestDataFromXL(String sheetName)
	{
		Object data[][]=null;
		
		try {
			FileInputStream ip= new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetName);
			 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//initialising a 3*6 obj array
		//to get the size of data eg: a 3R and 6Col sheet
		//sheet.getLastRowNum-->total num of rows ie: here 3
		//sheet.getRow(0).getLastCellNum()-->goes to 1st row and get the last col num ie:here 6
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i=0;i<sheet.getLastRowNum();i++)
		{
			for (int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
		
		
	}
}
