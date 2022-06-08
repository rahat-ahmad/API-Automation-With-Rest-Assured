package com.apiautomation;

import com.apiautomation.util.ExcelUtils;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginAPITest {

    String contextPath;
    @BeforeClass()
    public void beforeClass(){
        contextPath = "https://reqres.in/";
    }

    @Test
    public void test_1_LoginValidateWithStatusCode_TC0005() throws IOException {

        ExcelUtils excelUtils = new ExcelUtils();
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_1_LoginValidateWithStatusCode",true);
        JSONObject requestData = excelUtils.getRequestBodyFromXcel("./data/Login.xlsx",true, new ArrayList());
        Reporter.log("Request Data: " + requestData, true);
        given().contentType("application/json\r\n").body(requestData.toString()).when().post(contextPath+"api/login")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void test_2_LoginValidateWithInvalidData_TC0006() throws IOException {

        ExcelUtils excelUtils = new ExcelUtils();
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_2_LoginValidateWithInvalidData",true);
        JSONObject requestData = excelUtils.getRequestBodyFromXcel("./data/LoginWithInvalidData.xlsx",true, new ArrayList());
        Reporter.log("Request Data: " + requestData, true);
        given().contentType("application/json\r\n").body(requestData.toString()).when().post(contextPath+"api/login")
                .then().assertThat().statusCode(400).log().all();
    }

    @Test
    public void test_3_LoginWithInvalidDataValidateWithResponse_TC0007() throws IOException {

        ExcelUtils excelUtils = new ExcelUtils();
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_3_LoginWithInvalidDataValidateWithResponse",true);
        JSONObject requestData = excelUtils.getRequestBodyFromXcel("./data/LoginWithInvalidData.xlsx",true, new ArrayList());
        Reporter.log("Request Data: " + requestData, true);
        given().contentType("application/json\r\n").body(requestData.toString()).when().post(contextPath+"api/login").then().statusCode(400).
                body("error",equalTo("user not found")).log().all();

    }
}
