import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UravBalance {
    public static void prepareUrBal(){
        String path = "data/URAV_BAL.xlsx";
        String path2 = "data/URAV_BAL_out.xlsx";
        FileInputStream fis;
        Workbook workbook;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        int row = 1;
        int i=0;
        int endRow;
        while (true){
            endRow = sheet.getLastRowNum();
            if (endRow-row<6){
                sheet.shiftRows(row+6,endRow+6,-6);
                break;
            }
            if (i%6==0&&i>0){
                sheet.shiftRows(row+6,endRow,-6);
                row++;
            }
            i++;
        }
        try {
            fis.close();
            FileOutputStream out = new FileOutputStream(path2);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createUrBal(){
        String path = "data/URAV_BAL_out.xlsx";
        FileInputStream fis;
        Workbook workbook;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getCell(1).getCellType()== CellType.NUMERIC){
                continue;
            }
            String balPower = "PП12:ДефОЭС_Рост_Дон";
            String regex = "([0-9]+)([+-]?)([0-9,]*)([(Д0-9-+)∙*]*)";
            String kprName = row.getCell(0).getStringCellValue().replaceAll("[«»]","")
                    .replaceAll("\\s+","_");
            String resultKprName ="Расч_"+ kprName;
            row.createCell(2).setCellValue(resultKprName);
            String formulaKPR = row.getCell(1).getStringCellValue();
            String coefficient="";
            String resultSum="";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(formulaKPR);
            while (matcher.find()){
                float coeff;
                if(matcher.group(3).isEmpty()&&matcher.group(2).equals("+")){
                    coeff=1;
                }
                else if(matcher.group(3).isEmpty()&&matcher.group(2).equals("-")){
                    coeff=-1;
                }
                    else {
                    if (matcher.group(2).equals("+")){
                        coeff = Float.parseFloat(matcher.group(3).replaceAll(",","."));
                    } else {
                        coeff = (-1)*Float.parseFloat(matcher.group(3).replaceAll(",","."));
                    }
                }

                String formula = matcher.group(4).replaceAll("[()]","");
                String regex2 = "(Д)([+-]?)([0-9]*)";
                Pattern pattern1 = Pattern.compile(regex2);
                Matcher matcher1 = pattern1.matcher(formula);
                while (matcher1.find()){
                    float result;
                    if (matcher1.group(2).isEmpty()||matcher1.group(3).isEmpty()){
                        resultSum ="0,0";
                    }else {
                        if (matcher1.group(2).contains("+")){
                            result=Float.parseFloat(matcher1.group(3))*coeff;
                        }else {
                            result=Float.parseFloat(matcher1.group(3))*coeff*(-1);
                        }
                        resultSum=String.valueOf(result).replaceAll("\\.",",");
                    }
                }
                coefficient = String.valueOf(coeff*(-1)).replaceAll("\\.",",");

            }
            String uravBalance = "[[ТИ_"+kprName+";1,0;Нет;Да;Нет];["+balPower+";"+coefficient+";Нет;Нет;Нет]]";
            row.createCell(3).setCellValue(resultSum);
            row.createCell(4).setCellValue(uravBalance);
        }
        try {
            fis.close();
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
