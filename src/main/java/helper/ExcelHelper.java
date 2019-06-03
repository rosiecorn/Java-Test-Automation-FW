package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import exception.CustomException;

/**
 * @author hongle
 */

public class ExcelHelper {

    private Workbook workbook;

    public Workbook openWorkBook(String filePath) {
        FileInputStream file =null;
        try {
            file = new FileInputStream(new File(filePath));
            this.workbook = WorkbookFactory.create(file);
            return workbook;
        } catch (IOException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public Sheet openWorkSheet(String name, String filePath) {
        return openWorkBook(filePath).getSheet(name);
    }

    public void closeWorkBook(Workbook workbook)  {
        try {
            workbook.close();
        } catch (IOException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public Object[][] getWorkSheetData(String sheetName, String filePath)
    {
        Sheet sheet = openWorkSheet(sheetName,filePath);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        DataFormatter objDefaultFormat = new DataFormatter();

        FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

        for (int i=0;i<sheet.getLastRowNum();i++)
        {
            for (int j=0;j<sheet.getRow(i).getLastCellNum();j++)
            {
                Cell cellValue = sheet.getRow(i+1).getCell(j);

                // This will evaluate the cell, And any type of cell will return string value
                objFormulaEvaluator.evaluate(cellValue);

                String cellValueStr = objDefaultFormat.formatCellValue(cellValue, objFormulaEvaluator);

                data[i][j] = cellValueStr;
            }
        }

        closeWorkBook(workbook);

        return data;
    }
}
