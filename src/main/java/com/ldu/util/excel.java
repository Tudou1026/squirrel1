package com.ldu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 包名+类名： com.excel.Test
 */
public class excel {


    //逻辑：1、创建工作簿-->2、创建工作表-->3、创建行-->4、创建表格/cell-->5、写入数据-->6、设置储存路径
    public static void main(String[] args) throws ParseException {

        //1、创建工作簿
        Workbook wb = new XSSFWorkbook();
        //1.1、设置表格的格式----居中
        CellStyle cs = wb.createCellStyle();
        //2.1、创建工作表
        Sheet sheet = wb.createSheet("学生表格");
        //2.2、合并单元格
        sheet.addMergedRegion(new CellRangeAddress(4, 8, 5, 9));
        //3.1、创建行----表头行
        Row row = sheet.createRow(0);
        //4、创建格
        Cell cell = row.createCell(0);
        cell.setCellValue("学号");
        cell.setCellStyle(cs);
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(cs);
        cell = row.createCell(2);
        cell.setCellValue("时间");
        cell.setCellStyle(cs);

        //5、写入实体数据
        for (int i = 0; i < 5; i++) {
            //3.2、创建行----内容行
            row = sheet.createRow(i+1);
            //第几行第几格  第一行第一格为“code”
            row.createCell(0).setCellValue(222);
            row.createCell(1).setCellValue(111);
            row.createCell(2).setCellValue(333);
        }

        //6、将文件储存到指定位置
        String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xlsx";
        String home = System.getProperty("user.home");
        File f = new File(home + File.separator + "Downloads" + File.separator + fileName);
        try {
            FileOutputStream fout = new FileOutputStream(f);
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
