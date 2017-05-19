package com.t600.biz;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
    private Random rdm = new Random();


    public static final String EXCEL_XLS = "xls";
    public static final String EXCEL_XLSX = "xlsx";
    private static final String POST_CODE_URL = "http://opendata.baidu.com/post/s?wd=";
    private static final String ADDRESS_URL = "http://ditu.amap.com/service/poiInfo?query_type=TQUERY&pagesize=20&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=14&city=440100&geoobj=113.289334%7C23.121279%7C113.412931%7C23.150956&keywords=";
    public static final String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    Gson gson = new Gson();
    private boolean chooseContraceInfo = false; //是否手动选择教育局联系信息

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
            wb = new XSSFWorkbook(new FileInputStream(new File(xlsFilePath)));

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

                String rawContent = getValue(cell0).trim();
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
                    contraceInfo = getContraceInfo(name + "教育工作委员会");
                } else {
                    contraceInfo = getContraceInfo(name + "教育局");
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
                String postCode = getPostCode(URLEncoder.encode(name, "gb2312"));
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

    /**
     * 获取邮编
     *
     * @param name
     * @return
     */
    private String getPostCode(String name) {
        String postCode = "";

        String url = POST_CODE_URL + name;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(ua).get();
            Element list = doc.getElementsByTag("ul").first();
            if (list != null) {
                Element a = list.select("a[href]").first();
                if (a != null) {
                    String html = a.html();
                    postCode = html.substring(html.lastIndexOf(">") + 2).trim();
                    //System.out.println(postCode);
                } else {
                    System.out.println("无A");
                }
            } else {
                System.out.println("无list");
            }
            //System.out.println(doc.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postCode;
    }

    /**
     * 获取单元格值
     *
     * @param cell
     * @return
     */
    public String getValue(Cell cell) {
        int cellType = cell.getCellType();
        String cellValue = "";
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:     // 文本
                cellValue = cell.getRichStringCellValue().getString() + "#";
                break;
            case Cell.CELL_TYPE_NUMERIC:    // 数字、日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue() + "#";
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellValue = String.valueOf(cell.getRichStringCellValue().getString()) + "#";
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:    // 布尔型
                cellValue = String.valueOf(cell.getBooleanCellValue()) + "#";
                break;
            case Cell.CELL_TYPE_BLANK: // 空白
                cellValue = cell.getStringCellValue() + "#";
                break;
            case Cell.CELL_TYPE_ERROR: // 错误
                cellValue = "";
                break;
            case Cell.CELL_TYPE_FORMULA:    // 公式
                // 得到对应单元格的公式
                //cellValue = cell.getCellFormula() + "#";
                // 得到对应单元格的字符串
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cellValue = String.valueOf(cell.getRichStringCellValue().getString()) + "#";
                break;
            default:
                cellValue = "#";
                break;
        }
        return cellValue;
    }


    /**
     * 获取教育局地址和电话
     *
     * @param name
     * @return
     */
    public ContractInfo getContraceInfo(String name) throws Exception {

        ContractInfo contractInfo = new ContractInfo();
        String url = ADDRESS_URL + URLEncoder.encode(name, "utf-8");

        HttpResponse httpResponse = Request.Get(url).execute().returnResponse();
        org.apache.http.Header[] headers = httpResponse.getHeaders("Content-Type");

            /*for (Header header : headers) {
            }*/

        String text = Request.Get(url).execute().returnContent().asString();
        KeySearchResponse resp = gson.fromJson(text, KeySearchResponse.class);
        List<KeySearchResponse.DataBean.PoiListBean> poi_list = resp.getData().getPoi_list();
        if (poi_list != null && poi_list.size() > 0) {

            //自动选择的情况
            if (!chooseContraceInfo) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(0);
                String itemName = poiListBean.getName();
                if (itemName.contains("教育局")) {
                    String address = poiListBean.getAddress();
                    String tel = poiListBean.getTel();
                    System.out.println("自动选择了 " + itemName + " 地址：" + address + " 电话：" + tel);
                    contractInfo.address = address;
                    contractInfo.tel = tel;
                }
                //TimeUnit.SECONDS.sleep(1); //防止返回错误数据
                return contractInfo;
            }

            System.out.println("====> 请选择 " + name + " 的联系信息");
            int total = poi_list.size();
            if (total > 5) {
                total = 5;
            }

            for (int i = 0; i < total; i++) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(i);
                String name1 = poiListBean.getName();
                String address = poiListBean.getAddress();
                String tel = poiListBean.getTel();
                System.out.println((i + 1) + ". " + name1 + " 地址：" + address + " 电话：" + tel);
            }
            System.out.println("输入序号: ");

            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            x = x - 1;

            if (x >= 0 && x < total) {
                KeySearchResponse.DataBean.PoiListBean poiListBean = poi_list.get(x);
                String name1 = poiListBean.getName();
                String address = poiListBean.getAddress();
                String tel = poiListBean.getTel();
                System.out.println("你选择了 " + name1 + " 地址：" + address + " 电话：" + tel);
                contractInfo.address = address;
                contractInfo.tel = tel;
            } else {
                System.out.println("输入错误");
            }

        } else {
            System.out.println("无建议列表");
        }

        //  System.out.println(text);
        return contractInfo;
    }

    class ContractInfo {
        String address;
        String tel;
    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param in
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }

        InputStream input = new FileInputStream(file);

        return wb;
    }
}
