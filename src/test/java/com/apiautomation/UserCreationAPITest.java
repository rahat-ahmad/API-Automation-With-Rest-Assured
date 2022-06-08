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

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class UserCreationAPITest {

    String contextPath;
    @BeforeClass()
    public void beforeClass(){
        contextPath = "https://reqres.in/";
    }

    @Test
    public void test_1_userCreationValidateWithStatusCode_TC0002() throws IOException {

        ExcelUtils excelUtils = new ExcelUtils();

        JSONObject requestData = excelUtils.getRequestBodyFromXcel("./data/ValidDataForReg.xlsx",true, new ArrayList());
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_1_userCreationValidateWithStatusCode",true);
        Reporter.log("Request Data: " + requestData, true);
        given().contentType("application/json\r\n").body(requestData.toString()).when().post(contextPath+"api/users")
                .then().assertThat().statusCode(201).log().all();
    }

    @Test
    public void test_2_userCreationValidateWithResponseData_TC0003() throws IOException {

        ExcelUtils excelUtils = new ExcelUtils();

        JSONObject requestData = excelUtils.getRequestBodyFromXcel("./data/ValidDataForReg.xlsx",true, new ArrayList());
        Reporter.log("##############################################################################################################",true);
        Reporter.log("Log of test_2_userCreationValidateWithResponseData",true);
        Reporter.log("Request Data: " + requestData, true);
        //validate with response data and full log printed
        given().contentType("application/json\r\n").body(requestData.toString()).when().post(contextPath+"api/users").then().statusCode(201)
                .body("name",equalTo("aBcd")).body("job",equalTo("QA")).log().all();
    }


}

