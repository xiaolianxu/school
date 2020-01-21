package com.school.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReadExcelUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";

    /**
     *读取excel数据
     * @throws Exception
     *
     */
    public static JSONObject readExcelInfo(HttpServletRequest req, List<String> title) throws Exception{
        HttpSession session = req.getSession();
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) req;
        MultipartFile file = mreq.getFile("file");
        String location = session.getServletContext().getRealPath("upload");
        String url = location + "/" + System.currentTimeMillis() + file.getOriginalFilename();
        File excelFile = new File(url);
        try {
            org.apache.commons.io.FileUtils.copyInputStreamToFile(file.getInputStream(), excelFile);// 复制临时文件到指定目录下
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = new FileInputStream(excelFile);
        checkExcelVaild(excelFile);
        Workbook workbook = getWorkBook(is, excelFile);
        List<Map> result = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        if(sheet==null || sheet.getLastRowNum()<=0){
            return CommonUtil.errorJson(ErrorEnum.E_607);
        }
        int length = sheet.getRow(0).getLastCellNum();
        if(length<title.size()){
            return CommonUtil.errorJson(ErrorEnum.E_604);
        }
        String[] name = new String[length];
        List<String> names = new ArrayList<>();
        Row row0 = sheet.getRow(0);
        for(int cellIndex=0;cellIndex<length;cellIndex++) {
            Cell cell = row0.getCell(cellIndex);
            if (cell == null) {
                return CommonUtil.errorJson(ErrorEnum.E_605);
            } else {
                name[cellIndex] = getCellValue(cell);
                names.add(name[cellIndex]);
            }
        }
        if (names.size()>= title.size()){
            if(names.containsAll(title)){
            }else {
                return CommonUtil.errorJson(ErrorEnum.E_604);
            }
        }
        for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++){
            Row row = sheet.getRow(rowIndex);
            if(row==null){
                continue;
            }
            if(row.getLastCellNum() != length){
                return CommonUtil.errorJson(ErrorEnum.E_606);
            }else{
                Map cellList = new HashMap();
                for(int cellIndex=0;cellIndex<row.getLastCellNum();cellIndex++){
                    Cell cell = row.getCell(cellIndex);
                    if(cell==null){
                        return CommonUtil.errorJson(ErrorEnum.E_606);
                    }
                    cellList.put(name[cellIndex],getCellValue(cell));
                }
                result.add(cellList);
            }
        }
        is.close();
        System.gc();
        excelFile.delete();
        return CommonUtil.successJson(result);
    }
    /**
     *获取单元格的数据,暂时不支持公式
     *
     *
     */
    public static String getCellValue(Cell cell){
        CellType cellType = cell.getCellType();
        String cellValue = "";
        if(cell==null || cell.toString().trim().equals("")){
            return null;
        }

        if(cellType==CellType.STRING){
            cellValue = cell.getStringCellValue().trim();
            return cellValue = StringUtil.isEmpty(cellValue)?"":cellValue;
        }
        if(cellType==CellType.NUMERIC){
            if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                Date d = cell.getDateCellValue();
                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                cellValue = df2.format(d);
            } else {
                cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
            }
            return cellValue;
        }
        if(cellType==CellType.BOOLEAN){
            cellValue = String.valueOf(cell.getBooleanCellValue());
            return cellValue;
        }
        return null;
    }
    /**
     *判断excel的版本，并根据文件流数据获取workbook
     * @throws IOException
     *
     */
    public static Workbook getWorkBook(InputStream is,File file) throws Exception{

        Workbook workbook = null;
        if(file.getName().endsWith(EXCEL_XLS)){
            workbook = new HSSFWorkbook(is);
        }else if(file.getName().endsWith(EXCEL_XLSX)){
            workbook = new XSSFWorkbook(is);
        }

        return workbook;
    }
    /**
     *校验文件是否为excel
     * @throws Exception
     *
     *
     */
    public static void checkExcelVaild(File file) throws Exception {
        String message = "该文件是EXCEL文件！";
        if(!file.exists()){
            message = "文件不存在！";
            throw new Exception(message);
        }
        if(!file.isFile()||((!file.getName().endsWith(EXCEL_XLS)&&!file.getName().endsWith(EXCEL_XLSX)))){
            System.out.println(file.isFile()+"==="+file.getName().endsWith(EXCEL_XLS)+"==="+file.getName().endsWith(EXCEL_XLSX));
            System.out.println(file.getName());
            message = "文件不是Excel";
            throw new Exception(message);
        }
    }
}
