package setting;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.TestDataEntity;
import data.TestDataType;
import exception.CustomException;
import global.Variables;
import helper.InputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hongle
 * This class use to convert from data-mapping xml file to Java Object "TestDataEntity" with the same properties
 */
public class ExtractDataMapping {


    public List <TestDataEntity> getDataEntities (TestDataType type)
    {
        File file;

        if (type == TestDataType.API)
            file = new File(Variables.API_DATA_MAPPING_FILE_PATH);
        else
            file = new File(Variables.UI_DATA_MAPPING_FILE_PATH);

        XmlMapper xmlMapper = new XmlMapper();

        try {
            String xml = InputStream.toString(new FileInputStream(file));

            return xmlMapper.readValue(xml, new TypeReference<List<TestDataEntity>>() {
            });

        }
        catch (IOException ex)
        {
            throw new CustomException("Failing mapping data from data-mapping.xml file to TestDataEntity" + ex.getMessage());
        }
    }

    public List<String> getTestData(Method method,List<TestDataEntity> entries) {

        List<String> data = new ArrayList<>();
            Iterator<TestDataEntity> iterator = entries.iterator();
            while (iterator.hasNext()) {
                TestDataEntity entity = iterator.next();
                if (entity.getTestMethod().equals(method.getName()) && entity.getTestClass().equals(method.getDeclaringClass().getName())) {
                    data.add(Variables.TEST_DATA_FILE_PATH +"/" + entity.getTestDataFile());
                    data.add(entity.getSheetData());
                    data.add(entity.getSqlScript());
                }
            }
        return data;
    }


}
