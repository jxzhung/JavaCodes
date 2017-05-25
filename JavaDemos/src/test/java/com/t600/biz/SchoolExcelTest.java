package com.t600.biz;

import com.t600.biz.entity.ContractInfo;
import com.t600.biz.util.ContactUtil;
import com.t600.biz.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 给做好的学校表格加邮编
 * Created by Jzhung on 2017/5/23.
 */
public class SchoolExcelTest {
    private static final int NUMBER = 2;
    private static final int ADDRESS = 3;
    private static final int POST_CODE = 6;

    @Test
    public void getPostCode() {
        String xlsFilePath = "C:\\Users\\Jzhung\\Desktop\\第二次去重表.xlsx";
        int startRow = 2; //开始行

        File xlsFile = new File(xlsFilePath);
        String xlsFileName = xlsFile.getName();
        String xlsShortName = xlsFileName.substring(0, xlsFileName.lastIndexOf("."));
        String xlsExt = xlsFileName.substring(xlsFileName.lastIndexOf("."));
        FileOutputStream out = null;
        String fname = xlsShortName + "-完成" + xlsExt;
        String saveFile = new File(xlsFile.getParentFile().getAbsolutePath(), fname).getAbsolutePath();

        Workbook wb = null;
        int index = 0;
        try {
            wb = ExcelUtil.getWorkbook(xlsFilePath);
            Sheet sheet = wb.getSheetAt(0);
            System.out.println("====================== 处理工作薄 " + sheet.getSheetName());
            int rows = sheet.getPhysicalNumberOfRows();

            //读取每一行 第二行开始
            for (int i1 = startRow - 1; i1 < rows; i1++) {
                Row row = sheet.getRow(i1);

                Cell cell0 = row.getCell(NUMBER);
                if (cell0 == null) {
                    continue;
                }
                // 如果当前行没有数据，跳出循环
                if (cell0.toString().equals("")) {
                    return;
                }

                Cell addressCell = row.getCell(ADDRESS);
                String rawContent = ExcelUtil.getValue(addressCell).trim();

                String city = ContactUtil.getCityFromAddress(rawContent);
                //System.out.println(city);

                // 获取邮编
                Cell postCodeCell = row.getCell(POST_CODE);
                if (postCodeCell == null || ExcelUtil.getValue(postCodeCell).replace("#", "").trim().equals("")) {
                    postCodeCell = row.createCell(POST_CODE);
                    String postCode = ContactUtil.getPostCode(URLEncoder.encode(city, "gb2312"));
                    postCodeCell.setCellValue(postCode);
                    System.out.println("处理" + row.getRowNum() + " " + city + " 邮编：" + postCode);
                } else {
                    System.out.println("跳过" + row.getRowNum() + " " + city );
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*String name = xlsShortName + "-" + System.currentTimeMillis() + xlsExt;
            saveFile = new File(xlsFile.getParentFile().getAbsolutePath(), name).getAbsolutePath();*/
        } finally {
            try {
                out = new FileOutputStream(saveFile);
                wb.write(out);
                out.close();
                System.out.println("写入文件 " + saveFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(xlsFilePath + " 处理完毕");
        }
    }
}
