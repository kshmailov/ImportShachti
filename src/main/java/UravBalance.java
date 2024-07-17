import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UravBalance {
    public static void createUrBal(){
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
}
