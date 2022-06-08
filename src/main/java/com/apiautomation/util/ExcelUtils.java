package com.apiautomation.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    public JSONObject getRequestBodyFromXcel(String filePath, boolean containHeader, ArrayList requestParam) throws IOException {
        String excelPath = filePath;
        ArrayList requestBodyAttributeList = new ArrayList();
        Map<String, Object> mapData = new HashMap<String, Object>();
        int getCellCount;

        XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        getCellCount = sheet.getRow(0).getPhysicalNumberOfCells();

        //While excel file contain header
        if (containHeader){
            for(int i = 0; i < getCellCount; i++){
                requestBodyAttributeList.add(sheet.getRow(0).getCell(i).getStringCellValue());
            }
            for (int i=0; i<requestBodyAttributeList.size();i++){
                try{
                    mapData.put(requestBodyAttributeList.get(i).toString(),sheet.getRow(1).getCell(i).getStringCellValue());
                }
               catch (IllegalStateException e){
                   String value = sheet.getRow(1).getCell(i).toString();
                   value = value.substring(0,value.length()-2);
                   mapData.put(requestBodyAttributeList.get(i).toString(),value);
               }

            }

            return new JSONObject(mapData);
        }

        //While excel file do not contain header
        for (int i=0; i<requestParam.size();i++){
            try{
                mapData.put(requestParam.get(i).toString(),sheet.getRow(0).getCell(i).getStringCellValue());
            }catch (IllegalStateException e){
                String value = sheet.getRow(0).getCell(i).toString();
                value = value.substring(0,value.length()-2);
                mapData.put(requestParam.get(i).toString(),value);
            }
        }
        return new JSONObject(mapData);

    }
}
