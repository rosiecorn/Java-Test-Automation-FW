package api.shipper;

import api.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import db.*;
import exception.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import security.Bases64;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipperEPO  {

    private static final String URL = "http://192.168.59.119:8080/shipper";
    private static final MediaType PROCEDURE = MediaType.APPLICATION_JSON;
    private static final String USERNAME="admin";
    private static final String PASSWORD = Bases64.decode("cGFzc3dvcmQ=");

    RestService restService;


    public ShipperEPO()
    {

        restService = RestService.getInstance();
    }


    public String getUrl() {
        return URL;
    }

    public MediaType getMediaType()
    {
        return PROCEDURE;
    }

    public void createHeader()
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(PROCEDURE);
        restService.setHeaders(headers);
    }

    public void createAuthorization()
    {
        restService.setAuthorization(USERNAME,PASSWORD);
    }

    public ResponseEntity<String> sendRequestToGetFullExistingShipper ()
    {
        return restService.sendGet(getUrl(),String.class);
    }

    public ResponseEntity<String> sendRequestWithSingleLookupShipperId (String id)
    {

        return restService.sendGet(getUrl()+"/" +id ,String.class);
    }

    public ResponseEntity<String> sendRequestWithSearchingInfo (String uri)
    {

        return restService.sendGet(uri,String.class);
    }

    public ResponseEntity<String> sendRequestWithBody ()
    {
        return restService.sendPost(getUrl(),String.class);
    }

    public void createBody(String companyName, String phone)
    {
        ShipperModel shipper = new ShipperModel();
        shipper.setCompanyname(companyName);
        shipper.setPhone(phone);

        restService.setBody(shipper);
    }
    public List<ShipperModel>  getActualBodyForFullShipperList (ResponseEntity<String> response)
    {
        try {
            return restService.objectMapper().readValue(response.getBody(), new TypeReference<List<ShipperModel>>() {
            });
        }
        catch(Exception ex)
            {
                throw new CustomException(ex.getMessage());
            }
    }

    public List<ShipperModel>  getActualBodyWithArrayCast (ResponseEntity<?> response)
    {
        try {
            return restService.objectMapper().readValue("[" + response.getBody() + "]", new TypeReference<List<ShipperModel>>() {
            });
        }
        catch(Exception ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }

    public String getInsertedShipperId(String json)
    {
        return restService.getJsonNode(json,"id");
    }

    public String uri (String searchString)
    {
        return restService.uri(getUrl() +"/search")
                .queryParam("companyname",searchString).toUriString();
    }

    public List<ShipperModel> getExpectedData (String queryString)
    {
        DatabaseConnection dbConnection = new DatabaseConnection(DBType.MYSQL);

        Connection conn = dbConnection.getConnection();

        ResultSet data;

        try {
            data  = SqlRunner.executeQuery(conn, queryString);
        }
        catch(SQLException ex)
        {
            throw new CustomException(ex.getMessage());
        }


        ResultSetMapper<ShipperModel> resultSetMapper = new ResultSetMapper<>();

        List<ShipperModel> list;

            if (!SqlResponse.isEmpty(data)) {
               list = resultSetMapper.mapRersultSetToObject(data, ShipperModel.class);
            } else {
                list= new ArrayList<>();
            }

            dbConnection.close(conn);

            return list;
    }

}