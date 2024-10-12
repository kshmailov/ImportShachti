import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseExcellScheme {
    protected static HashMap<String, String> parseExcel(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        HashMap<String, String> schemes = new HashMap<>();
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(
                    fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int count = workbook.getNumberOfSheets();
        ArrayList<Sheet> sheets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            sheets.add(workbook.getSheetAt(i));
        }
        for (Sheet list : sheets) {

            String schemesKpr = list.getRow(0).getCell(4).getStringCellValue();
            System.out.println(schemesKpr);
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


            for (Row row : list) {
                String cellValue = row.getCell(0).getStringCellValue();
                System.out.println(cellValue);
                if (cellValue.contains("Нормальная схема")) {
                    String keyNameScheme = "[Нормальная_схема" + schemesGroup + "]";
                    System.out.println(keyNameScheme);
                    schemes.put(keyNameScheme, "");
                } else if (cellValue.contains("или")) {
                    String[] schemesFrag = cellValue.split("или");
                    for (String frag : schemesFrag) {
                        if (!frag.isEmpty()) {
                            String nameScheme = modifiedNameSchemes(frag);
                            String tsRemes = tsRemes(nameScheme);
                            String keyNameScheme = "[" + nameScheme + schemesGroup + "]";
                            schemes.put(keyNameScheme, tsRemes);
                            System.out.println(keyNameScheme);
                            System.out.println(tsRemes);
                        }
                    }
                } else if (!modifiedNameSchemes(cellValue).isEmpty()) {
                    String nameScheme = modifiedNameSchemes(cellValue);
                    String tsRemes = tsRemes(nameScheme);
                    String keyNameScheme = "[" + nameScheme + schemesGroup + "]";
                    schemes.put(keyNameScheme, tsRemes);
                    System.out.println(keyNameScheme);
                    System.out.println(tsRemes);
                }
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

    public static String tsRemes(String nameSchemes) {
        ArrayList<String> tsRemList = new ArrayList<>() {
            {
                add("[Р1:РоАЭС-Шахты]");
                add("[Р2:РоАЭС-Тихорецк_№1]");
                add("[Р3:РоАЭС-Тихорецк_№2]");
                add("[Р4:РоАЭС-Георг]");
                add("[Р5:РоАЭС-Ростовская]");
                add("[Р6:Шахты-Ростовская]");
                add("[Р7:Волга-Георг]");
                add("[Р8:Георг-Трубная]");
                add("[Р9:Ростовская-Тамань]");
                add("[Р10:Фроловская-Шахты]");
                add("[Р11:Победа-Шахты]");
                add("[Р12:ВолжГЭС-Фроловская]");
                add("[Р14:АТ-1_500/220_РоАЭС]");
                add("[Р15:АТГ-1_500/220_ПС_Ростовская]");
                add("[Р16:АТ-3_500/220_ПС_Шахты]");
                add("[Р17:АТ-4_500/220_ПС_Шахты]");
                add("[Р18:Бл-1_РоАЭС]");
                add("[Р19:Бл-2_РоАЭС]");
                add("[Р20:Бл-3_РоАЭС]");
                add("[Р21:Бл-4_РоАЭС]");
                add("[Р22:СМВ-220_ПС_Андреановская]");
                add("[Р23:НчГРЭС-Ростовская]");
                add("[Р24:НчГРЭС-Тихорецк]");
                add("[Р25:СВ-220_ПС_А30]");
                add("[Р26:АТГ-2_330/220_ПС_Ростовская]");
                add("[Р27:Волга-Заливская]");
                add("[Р28:Волгодонск-ГОК-Заливская]");
                add("[Р29:Заливская-ГОК]");
                add("[Р30:Волгодонск-ГОК]");
                add("[Р61:ШСВ-220_ПС_ГОК]");
                add("[Р31:РоАЭС-Котельниково-Заливская]");
                add("[Р32:РоАЭС-Котельниково]");
                add("[Р33:Заливская-Котельниково]");
                add("[Р62:СВ-220_ПС_Котельниково]");
                add("[Р34:Арч-Сатар-Андр-Веш2-Б10]");
                add("[Р35:Андреановская-Вешенская2]");
                add("[Р36:Вешенская-2-Б-10]");
                add("[Р37:Сатаровская-Андреановская]");
                add("[Р38:Арчеда-Сатаровская]");
                add("[Р63:СМВ-220_ПС_Сатаровская]");
                add("[Р39:А20-А30-Старом-Канев-Брюх]");
                add("[Р40:Брюховецкая-Каневская]");
                add("[Р41:Староминская-Каневская]");
                add("[Р42:Староминская-А30]");
                add("[Р43:А20-А30]");
                add("[Р93:СМВ-220_ПС_Каневская]");
                add("[Р44:Саль-Песчан-ЕяТяг-Тихор]");
                add("[Р45:Тихорецк-Ея_тяговая]");
                add("[Р46:Сальская-Песчанокопская]");
                add("[Р47:Ея_тяговая-Песчанокопская_Св]");
                add("[Р94:СМВ-220_ПС_Песчанокопская]");
                add("[Р95:СВ-220_ПС_ЕяТяговая]");
                add("[Р48:Койсуг-Зерновая-Сальская]");
                add("[Р49:Зерновая-Сальская]");
                add("[Р50:Койсуг-Зерновая]");
                add("[Р51:Тихорецк-Крыловская]");
                add("[Р52:Койсуг-Крыловская]");
                add("[Р53:НчГРЭС-Шахты_I_цепь]");
                add("[Р54:НчГРЭС-Шахты_II_цепь]");
                add("[Р55:АТ-1_ПС_Крыловская]");
                add("[Р56:Волгодонск-Сальская]");
                add("[Р57:РоАЭС-Волгодонск_1ц]");
                add("[Р58:РоАЭС-Волгодонск_2ц]");
                add("[Р59:ЦимлянскаяГЭС-Шахты]");
                add("[Р60:ЦимГЭС-ВолгТЭЦ2]");
                add("[Р87:Балаш-ЛипецЗападная_НВАЭС]");
                add("[Р88:Балаш-ЛипецВосточная]");
                add("[Р89:БалАЭС-Трубная]");
                add("[Р90:Балаш-Фроловская]");
                add("[Р91:Балаш-Волга]");
                add("[Р97:ВВ-500_№503_ПС_Фроловская]");
                add("[Р98:ВВ-500_№521_ПС_Фроловская]");
                add("[Р99:III_СШ500_РоАЭС]");
                add("[Р100:IV_СШ500_РоАЭС]");
                add("[Р101:В-41_РоАЭС]");
                add("[Р102:В-42_РоАЭС]");
                add("[Р103:3с.500_ПС_Шахты]");
                add("[Р104:4с.500_ПС_Шахты]");
                add("[Р105:В-61_РоАЭС]");
                add("[Р106:В-62_РоАЭС]");
                add("[Р131:ВолГЭС-Волга]");
                add("[Р132:ВЭ-10_Шахты]");
                add("[Р133:ВЭ-11_Шахты]");
                add("[Р134:В-51_РоАЭС]");
                add("[Р135:В-52_РоАЭС]");
                add("[Р136:ЦимлГЭС-ВолгТЭЦ-1]");
                add("[Р137:Схема_2_и_более_Север]");
            }
        };
        String ts = "";
        if (nameSchemes.contains("+")) {
            String[] pairNames = nameSchemes.split("\\+");
            List<String> remSchemeList = new ArrayList<>();
            for (String pair : pairNames) {
                for (String tsRem : tsRemList) {
                    int end = tsRem.indexOf(":");
                    String substring = tsRem.substring(1, end);
                    if (substring.equals(pair)) {
                        remSchemeList.add(tsRem);
                    }
                }
            }
            String tsList = String.join(";", remSchemeList);
            ts = "[" + tsList + "]";

        } else {
            for (String tsRemes : tsRemList) {
                int end = tsRemes.indexOf(":");
                String substring = tsRemes.substring(1, end);
                if (substring.equals(nameSchemes)) {
                    ts = tsRemes;
                    break;
                }
            }
        }
        return ts;
    }


}
