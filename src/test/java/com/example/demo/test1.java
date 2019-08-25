package com.example.demo;

import com.csvreader.CsvWriter;
import com.example.demo.dao.NeInfoDao;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class test1 {

    @Autowired
    NeInfoDao neInfoDao;

    @Test
    public void test() throws IOException {
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("USERID","001");
        userInfo.put("COUNTRYCODE","001");
        userInfo.put("CITYCODE","002");
        userInfo.put("OPERATORCODE","003");
        userInfo.put("type","view");

        HashMap<String, Double> coor = new HashMap<>();
        coor.put("coorXMin",75.6);
        coor.put("coorXMax",85.6);
        coor.put("coorYMin",75.7);
        coor.put("coorYMax",95.7);
        userInfo.put("coor",coor);

        List<Map<String, Object>> userNeList = neInfoDao.getUserNeList(userInfo);

        File file = new File("e:/UserInfo.xlsx");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFWorkbook sheets = new XSSFWorkbook();
        XSSFSheet sheet1 = sheets.createSheet("sheet1");
        XSSFRow titleRow = sheet1.createRow(0);
        titleRow.createCell(0).setCellValue("CountryCode");
        titleRow.createCell(1).setCellValue("CityCode");
        titleRow.createCell(2).setCellValue("OperatorCode");
        titleRow.createCell(3).setCellValue("NeName");
        titleRow.createCell(4).setCellValue("NeType");
        titleRow.createCell(5).setCellValue("COOR_X");
        titleRow.createCell(6).setCellValue("COOR_Y");

        for (int i = 0; i < userNeList.size(); i++) {
            int lastRowNumber = sheet1.getLastRowNum();
            XSSFRow row = sheet1.createRow(lastRowNumber + 1);
            row.createCell(0).setCellValue(userNeList.get(i).get("COUNTRYCODE").toString());
            row.createCell(1).setCellValue(userNeList.get(i).get("CITYCODE").toString());
            row.createCell(2).setCellValue(userNeList.get(i).get("OPERATORCODE").toString());
            row.createCell(3).setCellValue(userNeList.get(i).get("NENAME").toString());
            row.createCell(4).setCellValue(userNeList.get(i).get("NETYPE").toString());
            row.createCell(5).setCellValue(userNeList.get(i).get("COOR_X").toString());
            row.createCell(6).setCellValue(userNeList.get(i).get("COOR_Y").toString());

        }
        sheets.write(new FileOutputStream(file));
        sheets.close();


    }

    @Test
    public void exportCsvFile() throws IOException {
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("USERID","001");
        userInfo.put("COUNTRYCODE","001");
        userInfo.put("CITYCODE","002");
        userInfo.put("OPERATORCODE","003");
        userInfo.put("type","view");

        HashMap<String, Double> coor = new HashMap<>();
        coor.put("coorXMin",75.6);
        coor.put("coorXMax",85.6);
        coor.put("coorYMin",75.7);
        coor.put("coorYMax",95.7);
        userInfo.put("coor",coor);

        List<Map<String, Object>> userNeList = neInfoDao.getUserNeList(userInfo);

        File file = new File("e:/UserInfo.csv");
//        File file1 = File.createTempFile("UserInfo", ".csv");
        CsvWriter csvWriter = new CsvWriter(file.getCanonicalPath(), ',', StandardCharsets.UTF_8);
        String[] headers = {"CountryCode","CityCode","OperatorCode","NeName","NeType","COOR_X","COOR_Y"};

        csvWriter.writeRecord(headers);
        for (int i = 0; i < userNeList.size(); i++) {
            csvWriter.write(userNeList.get(i).get("COUNTRYCODE").toString());
            csvWriter.write(userNeList.get(i).get("CITYCODE").toString());
            csvWriter.write(userNeList.get(i).get("OPERATORCODE").toString());
            csvWriter.write(userNeList.get(i).get("NENAME").toString());
            csvWriter.write(userNeList.get(i).get("NETYPE").toString());
            csvWriter.write(userNeList.get(i).get("COOR_X").toString());
            csvWriter.write(userNeList.get(i).get("COOR_Y").toString());
            csvWriter.endRecord();

        }
        csvWriter.close();
    }
}
