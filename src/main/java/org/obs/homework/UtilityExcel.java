package org.obs.homework;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilityExcel {
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static FileInputStream f;
    public List<String> readDataFromExcel(String filePath, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        f = new FileInputStream(System.getProperty("user.dir") + filePath);
        wb = new XSSFWorkbook(f);
        sh = wb.getSheet(sheetName);
        ArrayList<String> excelRows = new ArrayList<String>();
        for (Row r : sh) {
            for (Cell c : r) {
                excelRows.add(formatter.formatCellValue(c));
            }
        }
        return excelRows;
    }

    public ArrayList<ArrayList<String>> readDatasFromExcel(String filePath, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        ArrayList<ArrayList<String> > data = new ArrayList<ArrayList<String> >();
        f = new FileInputStream(System.getProperty("user.dir") + filePath);
        wb = new XSSFWorkbook(f);
        sh = wb.getSheet(sheetName);
        int rowCount=sh.getLastRowNum()-sh.getFirstRowNum();
        ArrayList<String> excelRows = new ArrayList<String>();
        for(int i=0;i<rowCount+1;i++){
            int x=1;
            Row row=sh.getRow(i);
            String[] columnList=new String[row.getLastCellNum()];
            for(int j=0;j<columnList.length;j++){
                columnList[j]=formatter.formatCellValue(row.getCell(x));
                x++;
            }
            data.add(new ArrayList<>(Arrays.asList(columnList)));
        }
        return data;
    }
/*
    public ArrayList<String[]> readTableFromExcel(String filePath1, String sheetName1) throws IOException {
        FileInputStream file = new FileInputStream(new File("\\src\\main\\resources\\TestData.xlsx", "Table"));
        int columnCount = 3;
        ArrayList<String[]> rowsFromExcel = readTableFromExcel(filePath1, sheetName1);
        for (int i = 0; i < rowsFromExcel.size(); i++) {  //For each rows in excel
            for (int j = 0; i < rowsFromExcel.get(i).length; i++) { //For each cell in that row
                System.out.print(rowsFromExcel.get(i)[j]);
            }
        }
        ArrayList<String[]> ExcelRows = new ArrayList<String[]>();
        HSSFWorkbook wb = new HSSFWorkbook(file);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int cellIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            String[] cellValues = new String[columnCount];
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cellValues[cellIndex++] = cell.getStringCellValue();
                System.out.print(cell.getStringCellValue() + "\t\t");
            }
            ExcelRows.add(cellValues);
            cellIndex = 0;
        }
        file.close();
        return ExcelRows;
    }*/

}