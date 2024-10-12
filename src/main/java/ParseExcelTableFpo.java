import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseExcelTableFpo {

    private static TreeMap<String,String> keyMapTs = new TreeMap<>();
    public static HashSet<String> parseExcelTableFpo(String path, int list) throws IOException {
        FileInputStream fis = new FileInputStream(path);

        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(
                    fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HashSet<String> schemes = new HashSet<>();
        Sheet sheet = workbook.getSheetAt(list);
        String schemesKpr = sheet.getRow(0).getCell(4).getStringCellValue();

        String schemesGroup = getMapGroup(schemesKpr);
        for (Row row : sheet) {
            String cellValue = row.getCell(0).getStringCellValue();
            if (cellValue.contains("Нормальная схема")) {
                String keyNameScheme = "Нормальная_схема" + schemesGroup;
                schemes.add(keyNameScheme);
            } else if (cellValue.contains("или")) {
                String[] schemesFrag = cellValue.split("или");
                for (String frag : schemesFrag) {
                    if (!frag.isEmpty()) {
                        String nameScheme = modifiedNameSchemes(frag);
                        String keyNameScheme = nameScheme + schemesGroup;
                        schemes.add(keyNameScheme);
                    }
                }
            } else if (!modifiedNameSchemes(cellValue).isEmpty()) {
                String nameScheme = modifiedNameSchemes(cellValue);
                String keyNameScheme = nameScheme + schemesGroup;
                schemes.add(keyNameScheme);
            }

        }

        fis.close();
        return schemes;
    }

    public static String modifiedNameSchemes(String name) {
        String regex = "\\(([^)]+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        String modifiedNameSchemes = "";
        while (matcher.find()) {
            modifiedNameSchemes = matcher.group(1).replaceAll("\\s+","");
        }
        return modifiedNameSchemes;
    }
    public static String getMapGroup(String schemesKpr){
        String schemesGroup = "";
        if (schemesKpr.contains("«Волгоград-Ростов»")) {
            schemesGroup = "{ВР}";
        } else if (schemesKpr.contains("«Волгоград-Ростов+Блок 1»")) {
            schemesGroup = "{ВР_БЛ1}";
        } else if (schemesKpr.contains("«Волгоград-Ростов+Блок 2»")) {
            schemesGroup = "{ВР_БЛ2}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+Блок 3»")) {
            schemesGroup = "{ВР_БЛ3}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+Блок 4»")) {
            schemesGroup = "{ВР_БЛ4}";
        }else if (schemesKpr.contains("«Ростов-Волгоград»")) {
            schemesGroup = "{РВ}";
        }else if (schemesKpr.contains("«Ростов-Волгоград+ВЛ 500 кВ Победа - Шахты»")) {
            schemesGroup = "{РВ_ПШ}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 1»")) {
            schemesGroup = "{В_БЛ1}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 2»")) {
            schemesGroup = "{В_БЛ2}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 3»")) {
            schemesGroup = "{В_БЛ3}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 4»")) {
            schemesGroup = "{В_БЛ4}";
        }else if (schemesKpr.contains("«Фроловское +Блок 1»")) {
            schemesGroup = "{Ф_БЛ1}";
        }else if (schemesKpr.contains("«Фроловское +Блок 2»")) {
            schemesGroup = "{Ф_БЛ2}";
        }else if (schemesKpr.contains("«Фроловское +Блок 3»")) {
            schemesGroup = "{Ф_БЛ3}";
        }else if (schemesKpr.contains("«Фроловское +Блок 4»")) {
            schemesGroup = "{Ф_БЛ4}";
        }else if (schemesKpr.contains("«Волгоградское из ОЭС Юга+ ВЛ 500 кВ Победа - Шахты»")) {
            schemesGroup = "{В_ПШ}";
        }else if (schemesKpr.contains("«Фроловское из ОЭС Юга+ ВЛ 500 кВ Победа - Шахты»")) {
            schemesGroup = "{Ф_ПШ}";
        }else if (schemesKpr.contains("«Север»")) {
            schemesGroup = "{С}";
        }else if (schemesKpr.contains("«Шахты»")) {
            schemesGroup = "{Ш}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+ Блок 1+Блок 2»")) {
            schemesGroup = "{ВР_БЛ12}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+ Блок 1+Блок 3»")) {
            schemesGroup = "{ВР_БЛ13}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+ Блок 1+Блок 4»")) {
            schemesGroup = "{ВР_БЛ14}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+ Блок 2+Блок 3»")) {
            schemesGroup = "{ВР_БЛ23}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+ Блок 2+Блок 4»")) {
            schemesGroup = "{ВР_БЛ24}";
        }else if (schemesKpr.contains("«Волгоград-Ростов+ Блок 3+Блок 4»")) {
            schemesGroup = "{ВР_БЛ34}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 1 + Блок 2»")) {
            schemesGroup = "{В_БЛ12}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 1 + Блок 3»")) {
            schemesGroup = "{В_БЛ13}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 2 + Блок 3»")) {
            schemesGroup = "{В_БЛ23}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 1 + Блок 4»")) {
            schemesGroup = "{В_БЛ14}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 2 + Блок 4»")) {
            schemesGroup = "{В_БЛ24}";
        }else if (schemesKpr.contains("«Волгоградское+Блок 3 + Блок 4»")) {
            schemesGroup = "{В_БЛ34}";
        }else if (schemesKpr.contains("«Фроловское +Блок 1 +Блок 2»")) {
            schemesGroup = "{Ф_БЛ12}";
        }else if (schemesKpr.contains("«Фроловское +Блок 1 +Блок 3»")) {
            schemesGroup = "{Ф_БЛ13}";
        }else if (schemesKpr.contains("«Фроловское +Блок 2 +Блок 3»")) {
            schemesGroup = "{Ф_БЛ23}";
        }else if (schemesKpr.contains("«Фроловское +Блок 1 +Блок 4»")) {
            schemesGroup = "{Ф_БЛ14}";
        }else if (schemesKpr.contains("«Фроловское +Блок 2 +Блок 4»")) {
            schemesGroup = "{Ф_БЛ24}";
        }else if (schemesKpr.contains("«Фроловское +Блок 3 +Блок 4»")) {
            schemesGroup = "{Ф_БЛ34}";
        }
        return schemesGroup;
    }
    public static void getMapTs(){
        keyMapTs.put("{ВР}","Шунтировка_Волг-Рост_Выведена");
        keyMapTs.put("{ВР_БЛ1}","Шунтировка_Волг-Рост+Б1_Выведена");
        keyMapTs.put("{ВР_БЛ2}","Шунтировка_Волг-Рост+Б2_Выведена");
        keyMapTs.put("{ВР_БЛ3}","Шунтировка_Волг-Рост+Б3_Выведена");
        keyMapTs.put("{ВР_БЛ4}","Шунтировка_Волг-Рост+Б4_Выведена");
        keyMapTs.put("{РВ}","Шунтировка_Рост-Волг_Выведена");
        keyMapTs.put("{РВ_ПШ}","Шунтировка_Рост-Волг+П-Ш_Выведен");
        keyMapTs.put("{В_БЛ1}","Шунтировка_Волг-е+Б1_Выведена");
        keyMapTs.put("{В_БЛ2}","Шунтировка_Волг-е+Б2_Выведена");
        keyMapTs.put("{В_БЛ3}","Шунтировка_Волг-е+Б3_Выведена");
        keyMapTs.put("{В_БЛ4}","Шунтировка_Волг-е+Б4_Выведена");
        keyMapTs.put("{Ф_БЛ1}","Шунтировка_Фрол-е+Б1_Выведена");
        keyMapTs.put("{Ф_БЛ2}","Шунтировка_Фрол-е+Б2_Выведена");
        keyMapTs.put("{Ф_БЛ3}","Шунтировка_Фрол-е+Б3_Выведена");
        keyMapTs.put("{Ф_БЛ4}","Шунтировка_Фрол-е+Б4_Выведена");
        keyMapTs.put("{В_ПШ}","Шунт-ка_Волг_изЭСЮга+ПШ_Выведена");
        keyMapTs.put("{Ф_ПШ}","Шунт-ка_Фрол_изЭСЮга+ПШ_Выведена");
        keyMapTs.put("{С}","Шунтировка_Север_Выведена");
        keyMapTs.put("{Ш}","Шунтировка_Шахты_Выведена");
        keyMapTs.put("{ВР_БЛ12}","Шунт-ка_Волг_Рост+Б1+Б2_Выведена");
        keyMapTs.put("{ВР_БЛ13}","Шунт-ка_Волг_Рост+Б1+Б3_Выведена");
        keyMapTs.put("{ВР_БЛ23}","Шунт-ка_Волг_Рост+Б2+Б3_Выведена");
        keyMapTs.put("{ВР_БЛ14}","Шунт-ка_Волг_Рост+Б1+Б4_Выведена");
        keyMapTs.put("{ВР_БЛ24}","Шунт-ка_Волг_Рост+Б2+Б4_Выведена");
        keyMapTs.put("{ВР_БЛ34}","Шунт-ка_Волг_Рост+Б3+Б4_Выведена");
        keyMapTs.put("{В_БЛ12}","Шунт-ка_Волг+Б1+Б2_Выведена");
        keyMapTs.put("{В_БЛ13}","Шунт-ка_Волг+Б1+Б3_Выведена");
        keyMapTs.put("{В_БЛ23}","Шунт-ка_Волг+Б2+Б3_Выведена");
        keyMapTs.put("{В_БЛ14}","Шунт-ка_Волг+Б1+Б4_Выведена");
        keyMapTs.put("{В_БЛ24}","Шунт-ка_Волг+Б2+Б4_Выведена");
        keyMapTs.put("{В_БЛ34}","Шунт-ка_Волг+Б3+Б4_Выведена");
        keyMapTs.put("{Ф_БЛ12}","Шунт-ка_Фрол+Б1+Б2_Выведена");
        keyMapTs.put("{Ф_БЛ13}","Шунт-ка_Фрол+Б1+Б3_Выведена");
        keyMapTs.put("{Ф_БЛ23}","Шунт-ка_Фрол+Б2+Б3_Выведена");
        keyMapTs.put("{Ф_БЛ14}","Шунт-ка_Фрол+Б1+Б4_Выведена");
        keyMapTs.put("{Ф_БЛ24}","Шунт-ка_Фрол+Б2+Б4_Выведена");
        keyMapTs.put("{Ф_БЛ34}","Шунт-ка_Фрол+Б3+Б4_Выведена");
    }
    public static ArrayList<String> createTableFpoString(HashSet<String> firstSheet, HashSet<String> secondSheet,String schemesGroup, String schemesKpr, String sezon){
        getMapTs();
        String tsTableString = keyMapTs.get(schemesGroup);
        ArrayList<String> listSchemeFpo = new ArrayList<>();
        String po = "*ФПО";
        for (String scheme : firstSheet){
            if (!secondSheet.contains(scheme)){
                StringBuilder tableString = new StringBuilder(String.join(" ", scheme, tsTableString,sezon, po, schemesKpr, "[безКпрДоз]"));
                for (int i =0; i<32; i++){
                    tableString.append(" []");
                }
                listSchemeFpo.add(tableString.toString());
            }
        }
        return listSchemeFpo;
    }
}
