package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.CustomException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * @author hongle
 *
 */

public class RestService<T> extends RestTemplate{

    private static RestService instance= null;
    private T body;

    private HttpHeaders headers;
    private ThreadLocal<RestTemplate> restTemplate = new ThreadLocal<>();

    public static RestService getInstance(){
        if (instance ==null) {
            instance = new RestService();
        }
        return instance;
    }

    public void setRest()
    {
        restTemplate.set(new RestTemplate());
    }

    public RestTemplate getCurrentRest()
    {
        return  restTemplate.get();
    }

    public ResponseEntity<T> sendGet (String url, Class<T> responseType) {
        return exchange(url, HttpMethod.GET, getHeaderAndBodyContent(),responseType);
    }

    public ResponseEntity<T> sendPost( String url, Class<T> responseType) {
        return exchange(url, HttpMethod.POST, getHeaderAndBodyContent(),responseType);
    }

    public  ResponseEntity<T> sendPut (String url, Class<T> responseType) {

        return exchange(url, HttpMethod.PUT, getHeaderAndBodyContent(),responseType);

    }

    public void setAuthorization( String username,  String password){
        getInterceptors().add(
                new BasicAuthorizationInterceptor(username, password));
    }

    private HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public void setBody(T body) {
        this.body = body;
    }

    private T getBody() {
        return body;
    }

    public HttpEntity<T> getHeaderAndBodyContent() {
        return new HttpEntity<>(getBody(),getHeaders());
    }

    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }

    public UriComponentsBuilder uri (String url)
    {
        return UriComponentsBuilder.fromHttpUrl(url);
    }

    public String getJsonNode (String json,String name)
    {
       try {
            JsonNode root = objectMapper().readTree(json);
            JsonNode node = root.path(name);
            return node.asText();
        }
        catch (IOException ex)
        {
            throw new CustomException(ex.getMessage());
        }
    }
}
