package com.yys.szcp.utils;


import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownLoadListUtils {


    //封装sheel
    public static void setListMapToExcel(List<Map> ListMap, String sheetName, String[] titleArray, String filePath) {
        try {

            //建立工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置sheet页面
            HSSFSheet sheet = workbook.createSheet(sheetName);
            sheet.setDefaultColumnWidth(20);//设置所有列的列宽

            //标题
            setRowTitle(sheet, getTitleCellStyle(workbook), titleArray);

            //内容
            setRowContent(sheet, getContentCellStyle(workbook), ListMap);

            File file = createFile(filePath);
            if (file != null) {
                OutputStream output = new FileOutputStream(file);
                workbook.write(output);
                output.close();
                workbook.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //创建文件
    private static File createFile(String filePath) {
        try {
            File file = new File(filePath);
            File temp = new File(file.getParent());
            if (!temp.exists()) {
                createDir(file.getParent());
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            return null;
        }
    }

    //创建目录
    private static Boolean createDir(String dirPath) {
        File dirFile = new File(dirPath);
        return dirFile.mkdirs();
    }

    //设置标题行
    private static void setRowTitle(HSSFSheet sheet, HSSFCellStyle titleCellStyle, String[] titleArray) {

        HSSFRow row1 = sheet.createRow(0);
        // row1.setHeight((short) (19*200));  // 设置行高

        for (int i = 0; i < titleArray.length; i++) {
            HSSFCell cell1 = row1.createCell(i);
            cell1.setCellStyle(titleCellStyle);
            cell1.setCellValue(titleArray[i]);
        }

    }

    //设置内容
    private static void setRowContent(HSSFSheet sheet, HSSFCellStyle contentCellStyle, List<Map> listMap) {
        for (int i = 0; i < listMap.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Map map = listMap.get(i);
            int j = 0;
            for (Object key : map.keySet()) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(contentCellStyle);
                cell.setCellValue(StringISNULLUtil.mapToString(map.get(key)));
                j++;
            }
        }
    }

    //标题样式
    private static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook) {
        //标题样式
        HSSFCellStyle titleCellStyle = workbook.createCellStyle();
        HSSFFont titleFont = workbook.createFont(); //标题字体
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //加粗
        titleFont.setFontHeightInPoints((short) 12); //字号
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setWrapText(true);//设置自动换行
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直居中
        //设置为文本
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        titleCellStyle.setDataFormat(dataFormat.getFormat("@"));
        //设置边框
        titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        return titleCellStyle;
    }

    //内容样式
    private static HSSFCellStyle getContentCellStyle(HSSFWorkbook workbook) {
        //内容样式
        HSSFCellStyle contentCellStyle = workbook.createCellStyle();
        HSSFFont contentFont = workbook.createFont(); //标题字体
//            contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //加粗
        contentFont.setFontHeightInPoints((short) 10); //字号
        contentCellStyle.setFont(contentFont);
//            contentCellStyle.setWrapText(true);//设置自动换行
        contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        contentCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直居中
        //设置为文本
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        contentCellStyle.setDataFormat(dataFormat.getFormat("@"));
        //设置边框
        contentCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        return contentCellStyle;
    }


    /**
     * Excel 文件导出流
     *
     * @param request
     * @param response
     * @param filePath
     * @param fileName
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        if (filePath.contains("..")) {
            throw new RuntimeException("路径不安全!");
        }
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            String projectUrl = request.getContextPath();
            response.setContentType("multipart/form-data");
            String regex = "[`~!@#$%^&*()\\+\\=\\{}|:\"?><\\/r\\/n]";
            Pattern pa = Pattern.compile(regex);
            Matcher ma = pa.matcher(fileName);
            if (ma.find()) {
                fileName = ma.replaceAll("");
            }
            response.setHeader("content-Disposition", "atachment;fileName=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            byte[] b = new byte[100];
            int len;
            try {
                while ((len = inputStream.read(b)) > 0)
                    outputStream.write(b, 0, len);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
