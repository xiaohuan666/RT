package com.example.demo;

import com.example.demo.Pojo.Student;
import com.example.demo.dao.StudentDao;

import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class hehe {
    @Autowired
    StudentDao studentDao;
    @Test
    public void toExcel() throws IOException {
        List<Student> students = studentDao.getStudents();

        File file = new File("e:/students.xls");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFSheet sheet1 = sheets.createSheet("sheet1");
//        Sheet sheet1 = sheets.createSheet("sheet1");
        HSSFRow titleRow = sheet1.createRow(0);
        titleRow.createCell(0).setCellValue("sid");
        titleRow.createCell(1).setCellValue("姓名");
        titleRow.createCell(2).setCellValue("出生日期");
        titleRow.createCell(3).setCellValue("性别");
        for (Student student : students) {
            int lastRowNumber = sheet1.getLastRowNum();
            HSSFRow row = sheet1.createRow(lastRowNumber + 1);
            row.createCell(0).setCellValue(student.getSid());
            row.createCell(1).setCellValue(student.getSname());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            row.createCell(2).setCellValue(simpleDateFormat.format(student.getSage()));
            row.createCell(3).setCellValue(student.getSsex());
        }
        sheets.write(file);
        sheets.close();
    }
}
