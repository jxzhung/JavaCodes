package com.t600.biz;

import com.t600.biz.entity.ContractInfo;
import com.t600.biz.util.ContactUtil;
import com.t600.biz.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 爬取全国教育局地址和电话
 * 注意如果出现错误，需从最近省份所在行开始
 * Created by Jzhung on 2017/5/18.
 */
public class EducationInfoTest2 {
    private static final int NAME = 0;
    private static final int ADDRESSS = 1;
    private static final int CONTRACT = 2;
    private static final int POSITION = 3;
    private static final int TEL = 4;
    private static final int POST_CODE = 5;

    public static void main(String[] args) {
        EducationInfoTest2 test = new EducationInfoTest2();
        test.savePostCode();
    }

    public void savePostCode() {
        String xlsFilePath = "C:\\Users\\Jzhung\\Desktop\\教育局信息\\00-所有省份.xlsx";

        int startRow = 2; //开始行 注意如果出现错误，需从最近省份所在行开始

        String province = null;
        String dijishi = null;
        String saveFile = null; //保存的文件

        int index = 0;
        Workbook wb = null;

        File xlsFile = new File(xlsFilePath);
        String xlsFileName = xlsFile.getName();
        String xlsShortName = xlsFileName.substring(0, xlsFileName.lastIndexOf("."));
        String xlsExt = xlsFileName.substring(xlsFileName.lastIndexOf("."));
        FileOutputStream out = null;
        String fname = xlsShortName + "-完成" + xlsExt;
        //saveFile = new File(xlsFile.getParentFile().getAbsolutePath(), fname).getAbsolutePath();

        saveFile = new File(xlsFilePath).getAbsolutePath();

        try {
            wb = ExcelUtil.getWorkbook(xlsFilePath);

            Sheet sheet = wb.getSheetAt(0);
            System.out.println("====================== 处理工作薄 " + sheet.getSheetName());
            int rows = sheet.getPhysicalNumberOfRows();

            //读取每一行 第二行开始
            for (int i1 = startRow - 1; i1 < rows; i1++) {
                Row row = sheet.getRow(i1);

                Cell cell0 = row.getCell(NAME);
                if (cell0 == null) {
                    continue;
                }
                // 如果当前行没有数据，跳出循环
                if (cell0.toString().equals("")) {
                    return;
                }

                String rawContent = ExcelUtil.getValue(cell0).trim();
                //System.out.println("内容：" + rawContent);
                if (rawContent.startsWith("##")) {
                    dijishi = rawContent.replace("#", "");
                    System.out.println("==========================================发现地级市：" + dijishi);
                    continue;
                }

                if (rawContent.startsWith("#")) {
                    province = rawContent.replace("#", "");
                    System.out.println("=================================================================发现省份：" + province);
                    continue;
                }

                if (dijishi == null) {
                    throw new IllegalArgumentException("地级市为null");
                }

                if (province == null) {
                    throw new IllegalArgumentException("省份为null");
                }

                String name = rawContent.replace("教育局", "").replace("#", "");

                System.out.println((i1 + 1) + " " + name);

                ContractInfo contraceInfo = null;
                if (province.equals("北京市")) {
                    contraceInfo = ContactUtil.getContraceInfo(name + "教育工作委员会");
                } else {
                    contraceInfo = ContactUtil.getContraceInfo(name + "教育局");
                }

                Cell addressCell = row.createCell(ADDRESSS);
                if (contraceInfo.address != null) {
                    String fullAddress = province + dijishi + name + contraceInfo.address.replace(province, "").replace(sheet.getSheetName(), "").replace(name, "");
                    //String fullAddress = sheet.getSheetName() + name + contraceInfo.address.replace(sheet.getSheetName(),"").replace(name,"");
                    addressCell.setCellValue(fullAddress);
                }

                // 电话
                Cell telCell = row.createCell(TEL);
                telCell.setCellValue(contraceInfo.tel);

                // 获取邮编
                Cell postCodeCell = row.createCell(POST_CODE);
                String postCode = ContactUtil.getPostCode(URLEncoder.encode(name, "gb2312"));
                postCodeCell.setCellValue(postCode);
                System.out.println("邮编：" + postCode);

                // 职位
                Cell posCell = row.createCell(POSITION);
                posCell.setCellValue("局长");

                System.out.println();
                index++;

                // 随机等待
                /*long sleepTime  = rdm.nextInt(2000) + 1000;
                System.out.println("等待：" + sleepTime);
                Thread.sleep(sleepTime);*/

                //每50条等待
                /*if (index % 50 == 0) {
                    System.out.println("---------------------- 等待10秒防止高德限制。。。");
                    TimeUnit.SECONDS.sleep(10);
                }*/
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
