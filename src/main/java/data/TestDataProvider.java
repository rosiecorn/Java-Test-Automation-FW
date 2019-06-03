package data;

import exception.CustomException;
import global.Variables;
import helper.ExcelHelper;
import helper.InputStream;
import org.testng.annotations.DataProvider;
import setting.ExtractDataMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author hongle
 * This class provide data test for both api and ui. almost of data will be extracted from data-mapping file
 */

public class TestDataProvider {

    @DataProvider
    public Object[][] apiData(Method method)
    {
        return data (method,TestDataType.API);
    }

    @DataProvider
    public Object[][] uiData(Method method)
    {
       return data (method,TestDataType.UI);

    }

    public Object[][] data (Method method, TestDataType type)
    {
        ExtractDataMapping mapping = new ExtractDataMapping();

        List <TestDataEntity> entities = mapping.getDataEntities(type);

        List<String> data = mapping.getTestData(method,entities);

        String filePath = data.get(0);
        String sheetName = data.get(1);

        ExcelHelper excel = new ExcelHelper();

        return excel.getWorkSheetData(sheetName,filePath);
    }

    public static String getSqlScript(Method method, TestDataType type)
    {
        try {
            ExtractDataMapping mapping = new ExtractDataMapping();

            List<TestDataEntity> entities = mapping.getDataEntities(type);
            List<String> data = mapping.getTestData(method, entities);

            String sqlFile = Variables.SQL_SCRIPT_PATH.concat("/" + data.get(2));

            return InputStream.toString(new FileInputStream(new File(sqlFile)));

        }catch (IOException ex)
        {
            throw new CustomException(ex.getMessage());
        }

    }

}
