package api.shipper;

import data.TestDataProvider;
import data.TestDataType;
import extension.Comparable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ShipperTest  extends base.BaseServiceTest {

    ShipperEPO epo = new ShipperEPO();

    @Test(enabled = true, priority = 1)
    public void SH01_VerifyAllExistingShipper() throws  Exception
    {
        String sql = TestDataProvider.getSqlScript(method,TestDataType.API);

        //Create headers and basic authorization
        epo.createHeader();

        epo.createAuthorization();

        //Send request and get response
        ResponseEntity<String>  response  = epo.sendRequestToGetFullExistingShipper();

        //Status checking
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        //Verify body
        List<ShipperModel> actual = epo.getActualBodyForFullShipperList(response);

        //Get Expected data
        List<ShipperModel> expected = epo.getExpectedData(sql);

        Comparable.deepEquals(actual,expected);
    }

   @Test( dataProvider="apiData", dataProviderClass = TestDataProvider.class,priority = 2)
    public void SH02_VerifyShipperSingleLookUpId(String id){

        String sql = TestDataProvider.getSqlScript(method, TestDataType.API).replace("@@id",id);

        //Create headers and basic authorization
        epo.createHeader();

        epo.createAuthorization();

        //Send request and get response
        ResponseEntity<String>  response  = epo.sendRequestWithSingleLookupShipperId(id);

        //Status checking
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        //Verify body
        List<ShipperModel> actual = epo.getActualBodyWithArrayCast(response);

        //Get Expected data
        List<ShipperModel> expected = epo.getExpectedData(sql);

        Comparable.deepEquals(actual,expected);
    }


    @Test(enabled = true, dataProvider="apiData", dataProviderClass = TestDataProvider.class,priority = 3)
    public void SH03_VerifyShipperSearching (String companyname)
    {
        //Get parameter data
        String sql = TestDataProvider.getSqlScript(method, TestDataType.API).replace("@@companyname",companyname);

        //Create headers and basic authorization
        epo.createHeader();

        epo.createAuthorization();

        //Send request and get response
        ResponseEntity<String>  response  = epo.sendRequestWithSearchingInfo(epo.uri(companyname));

        //Status checking
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        //Verify body
        List<ShipperModel> actual = epo.getActualBodyForFullShipperList(response);

        //Get Expected data
        List<ShipperModel> expected = epo.getExpectedData(sql);

        Comparable.deepEquals(actual,expected);

    }

    @Test(enabled = true, dataProvider="apiData", dataProviderClass = TestDataProvider.class, priority = 1)
    public void SH04_verifyInsertNewShipper (String companyname, String phone)
    {
        String sql = TestDataProvider.getSqlScript(method,TestDataType.API).replace("@@id","");

        //Create headers and basic authorization
        epo.createHeader();

        //Setup basic auth
        epo.createAuthorization();

        //Setup body
        epo.createBody(companyname,phone);

        //Send request and get response
        ResponseEntity<String>  response  = epo.sendRequestWithBody();

        //Status checking
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        //Get Actual data
        List<ShipperModel> actual = epo.getActualBodyWithArrayCast(response);

        //Get Expected data
        List<ShipperModel> expected = epo.getExpectedData(sql.concat(epo.getInsertedShipperId(response.getBody())));

        Comparable.deepEquals(actual,expected);
    }

}
