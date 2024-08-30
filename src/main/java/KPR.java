import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KPR {
    private static final ArrayList<String> kprList = new ArrayList<>();
    private static final HashMap<String,String> kprStMap = new HashMap<>();
    private static final String FLOW_KPR = "[[PП1:РоАЭС-Георг;1];[PП4:РоАЭС-Шахты;1];[PП5:РоАЭС-Ростовская;1];[PП6:АТ-1_РоАЭС;1];[PП11:Тихорецк-НчГРЭС;1];[PП19:Тихорецк-Крыловская;1];[PП20:Тихорецк-Ея_тяговая;1]]";
    public static void parseAndCreateKPR() {
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
            String regex = "([0-9]+)([+-]?)([0-9,]*)([(Д0-9-+)∙*]*)";
            String kprName = row.getCell(0).getStringCellValue().replaceAll("[«»]","")
                    .replaceAll("\\s+","_");
            kprList.add(kprName);
            StringBuilder stKPR= new StringBuilder();
            String formula = row.getCell(1).getStringCellValue();
            int gran = 0;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(formula);
            while (matcher.find()) {
                gran = Integer.parseInt(matcher.group(1));
            }
            for (int i =0; i<6;i++){
                if (i==0){
                    stKPR.append("[").append(gran).append(";ТИ_").append(kprName).append(";4;0;[Не задано]]");
                    continue;
                }
                gran+=100;
                stKPR.append(";[").append(gran).append(";ТИ_").append(kprName).append(";4;0;[Не задано]]");
            }
            String resultStKpr = "["+stKPR+"]";
            kprStMap.put(kprName,resultStKpr);
        }
        try {
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int rowNum=0;
        String pathOut = "data/KPR.xlsx";

        XSSFWorkbook workbook1 = new XSSFWorkbook();
        XSSFSheet sheet1 =workbook1.createSheet("KPR");
        for (String kprName : kprList){
            XSSFRow row = sheet1.createRow(rowNum++);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(kprName);
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(FLOW_KPR);
            XSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(kprStMap.get(kprName));
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(pathOut);
            workbook1.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
