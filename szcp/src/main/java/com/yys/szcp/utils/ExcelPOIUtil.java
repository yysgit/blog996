package com.yys.szcp.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/08/31 16:55:48
 * Email: 1095737364@qq.com
 */
public class ExcelPOIUtil {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    /**
     * Excel导入
     */
    public static List<Map<String, Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<Map<String, Object>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<Map<String, Object>>();
        //遍历Excel中所有的sheet
        sheet = work.getSheetAt(0);
        //遍历当前sheet中的所有行
        //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            //读取一行
            row = sheet.getRow(j);
            //遍历所有的列
            Map li = new HashMap();
            cell = row.getCell(0);
            if (cell == null) {
                li.put("deviceCode", null);
            } else {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceCode",StringISNULLUtil.mapToString( cell.getStringCellValue()));
            }
            cell = row.getCell(1);
            if (cell == null) {
                li.put("deviceType", null);
            } else {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceType", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(2);
            if (cell != null) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                li.put("dealerPhone",StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }

            cell = row.getCell(3);
            if (cell != null) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                li.put("adminPhone", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(4);
            if (cell != null) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                li.put("gpresCode", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            list.add(li);
        }
        return list;
    }


    /**
     * 销售经理Excel导入
     */
    public static List<Map<String, Object>> getBankSalsListByExcel(InputStream in, String fileName) throws Exception {
        List<Map<String, Object>> list = null;
        //创建Excel工作薄
        Workbook work = WorkbookFactory.create(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<Map<String, Object>>();
        //遍历Excel中所有的sheet
        sheet = work.getSheetAt(0);
        //遍历当前sheet中的所有行
        //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            //读取一行
            row = sheet.getRow(j);
            //遍历所有的列
            Map li = new HashMap();
            cell = row.getCell(0);
            if (cell != null) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceCode",StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(2);
            if (cell != null) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                li.put("adminPhone", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }

            list.add(li);
        }
        return list;
    }


    /**
     * 房东导入
     */
    public static List<Map<String, Object>> getBankAdminListByExcel(InputStream in, String fileName) throws Exception {
        List<Map<String, Object>> list = null;
        //创建Excel工作薄
        Workbook work = WorkbookFactory.create(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<Map<String, Object>>();
        //遍历Excel中所有的sheet
        sheet = work.getSheetAt(0);
        //遍历当前sheet中的所有行
        //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            //读取一行
            row = sheet.getRow(j);
            //遍历所有的列
            Map li = new HashMap();
            cell = row.getCell(0);
            if (cell != null) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceCode", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(1);
            if (cell != null) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceType",StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(2);
            if (cell != null) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                li.put("username", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(3);
            if (cell != null) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                li.put("gpresCode", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(4);
            if (cell != null) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceAddress", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(5);
            if (cell != null) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                li.put("deviceTel", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(6);
            if (cell != null) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                li.put("warningMoney", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(7);
            if (cell != null) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                li.put("isCapacity", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(8);
            if (cell != null) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                li.put("superOutage", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            cell = row.getCell(9);
            if (cell != null) {
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                li.put("ratio", StringISNULLUtil.mapToString(cell.getStringCellValue()));
            }
            list.add(li);
        }
        return list;
    }


    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

}
