package io.swagger.petstore.apiautomation.util;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Service
public class RestAPIClient {


    @Value("${base.uri:https://petstore.swagger.io/v2}")
    private String baseUri;
    public RequestSpecification requestSpecification() {
      return   given().baseUri(baseUri)
                .header("Content-Type", "application/json");
    }

    public void setParameter(String key,Object value){
        requestSpecification().param(key,value);
    }
    public ResponseOptions<Response> get(String endPoint){

        return   requestSpecification().get(endPoint);
    }
    public ResponseOptions<Response> post(String endPoint,Object body){
        return   requestSpecification().body(body).post(endPoint);
    }
    public ResponseOptions<Response> put(String endPoint,Object body) {
        return requestSpecification().body(body).put(endPoint);
    }
    public ResponseOptions<Response> delete(String endPoint){
        return   requestSpecification().delete(endPoint);
    }





}
