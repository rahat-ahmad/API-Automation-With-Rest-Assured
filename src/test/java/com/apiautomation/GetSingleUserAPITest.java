package com.apiautomation;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSingleUserAPITest {

    String contextPath;
    @BeforeClass()
    public void beforeClass(){
        contextPath = "https://reqres.in/";
    }

    @Test
    public void test_1_getUserValidateWithStatusCode_TC0009() throws IOException {

        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_1_get User Validate With StatusCode",true);
        given().contentType("application/json\r\n").get(contextPath+"api/users/2")
                .then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void test_2_getUserValidateWithResponseData_TC0010() throws IOException {
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_2_get User Validate With Response Data",true);

        given().contentType("application/json\r\n").get(contextPath+"api/users/2").then().statusCode(200).
                body("data.id",equalTo(2)).log().all();

    }

    @Test
    public void test_3_getUserWithWrongID_TC0011() throws IOException {
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_3_getUserWithWrongID",true);

        given().contentType("application/json\r\n").get(contextPath+"api/users/23").then().statusCode(404).log().all();

    }


}

