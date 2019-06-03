package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
/**
 * @author hongle
 *
 */

@JacksonXmlRootElement(localName="TestDataEntities")
public class TestDataEntity {

    @JsonProperty("TestClass")
    private String testClass;

    @JsonProperty("TestMethod")
    private String testMethod;

    @JsonProperty("TestDataFile")
    private String testDataFile;

    public String getSheetData() {
        return sheetData;
    }

    public void setSheetData(String sheetData) {
        this.sheetData = sheetData;
    }

    @JsonProperty("SheetData")
    private String sheetData;

    @JsonProperty("SqlScript")
    private String sqlScript;


    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public String getTestDataFile() {
        return testDataFile;
    }

    public void setTestDataFile(String testDataFile) {
        this.testDataFile = testDataFile;
    }

    public String getSqlScript() {
        return sqlScript;
    }

    public void setSqlScript(String sqlScript) {
        this.sqlScript = sqlScript;
    }
}
