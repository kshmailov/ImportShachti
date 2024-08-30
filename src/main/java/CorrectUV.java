import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class CorrectUV {
    private static final String[] uvOgList = {"УВ_ОГ150", "УВ_ОГ300", "УВ_Корр_ОГ450",
            "УВ_Корр_ОГ600", "УВ_Корр_ОГ750", "УВ_Корр_ОГ900"};
    private static final String[] uvOnREList = {"УВ_ОН1-РЭ", "УВ_ОН2-РЭ", "УВ_ОН3-РЭ",
            "УВ_ОН4-РЭ", "УВ_ОН5-РЭ", "УВ_ОН6-РЭ"};
    private static final String[] uvOnKEList = {"УВ_ОН1-КЭ", "УВ_ОН2-КЭ", "УВ_ОН3-КЭ",
            "УВ_ОН4-КЭ", "УВ_ОН5-КЭ", "УВ_ОН6-КЭ"};
    private static final String[] uvOnBHList = {"УВ_ОН1-ВЧ", "УВ_ОН2-ВЧ", "УВ_ОН3-ВЧ",
            "УВ_ОН4-ВЧ", "УВ_ОН5-ВЧ", "УВ_ОН6-ВЧ", "УВ_ОН7-ВЧ", "УВ_ОН8-ВЧ", "УВ_ОН9-ВЧ"};
    private static final String[] uvOnKR = {"УВ_ОН1+2-Крым"};
    public static void createCorrectUvOG(String name, String shunt, String tsWithout, String tsWith, String kpr){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< uvOgList.length; i++){
            String uvName = "ОГ"+150*(i+1);
            builder.append("[").append(name).append("_").append(uvName).append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append(shunt).append("\r\n");
            builder.append("\t").append("ts=").append(tsWithout).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335 ").append(kpr).append("\r\n");
            builder.append("\t").append("delays=4 4").append("\r\n");
            builder.append("\t").append("s2=").append(uvOgList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOgList[i]).append("\r\n\r\n");
            builder.append("[").append(name).append("_").append(uvName).append("_").append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append("*ФСч").append("\r\n");
            builder.append("\t").append("ts=").append(tsWith).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335").append("\r\n");
            builder.append("\t").append("delays=0").append("\r\n");
            builder.append("\t").append("s1=").append(uvOgList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOgList[i]).append("\r\n");
        }
        String resultText = builder.toString();
        try {
            // Creates a FileOutputStream
            FileOutputStream file = new FileOutputStream("data/pau.ini");

            // Creates an OutputStreamWriter
            OutputStreamWriter output = new OutputStreamWriter(file, "cp1251");

            // Writes string to the file
            output.write(resultText);

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void createCorrectUvOnRE(String name, String shunt, String tsWithout, String tsWith, String kpr){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< uvOnREList.length; i++){
            String uvName = "ОН"+(i+1)+"-РЭ";
            builder.append("[").append(name).append("_").append(uvName).append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append(shunt).append("\r\n");
            builder.append("\t").append("ts=").append(tsWithout).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335 ").append(kpr).append("\r\n");
            builder.append("\t").append("delays=4 4").append("\r\n");
            builder.append("\t").append("s2=").append(uvOnREList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnREList[i]).append("\r\n\r\n");
            builder.append("[").append(name).append("_").append(uvName).append("_").append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append("*ФСч").append("\r\n");
            builder.append("\t").append("ts=").append(tsWith).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335").append("\r\n");
            builder.append("\t").append("delays=0").append("\r\n");
            builder.append("\t").append("s1=").append(uvOnREList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnREList[i]).append("\r\n");
        }
        String resultText = builder.toString();
        try {
            // Creates a FileOutputStream
            FileOutputStream file = new FileOutputStream("data/pau.ini");

            // Creates an OutputStreamWriter
            OutputStreamWriter output = new OutputStreamWriter(file, "cp1251");

            // Writes string to the file
            output.write(resultText);

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void createCorrectUvOnKE(String name, String shunt, String tsWithout, String tsWith, String kpr){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< uvOnKEList.length; i++){
            String uvName = "ОН"+(i+1)+"-КЭ";
            builder.append("[").append(name).append("_").append(uvName).append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append(shunt).append("\r\n");
            builder.append("\t").append("ts=").append(tsWithout).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335 ").append(kpr).append("\r\n");
            builder.append("\t").append("delays=4 4").append("\r\n");
            builder.append("\t").append("s2=").append(uvOnKEList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnKEList[i]).append("\r\n\r\n");
            builder.append("[").append(name).append("_").append(uvName).append("_").append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append("*ФСч").append("\r\n");
            builder.append("\t").append("ts=").append(tsWith).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335").append("\r\n");
            builder.append("\t").append("delays=0").append("\r\n");
            builder.append("\t").append("s1=").append(uvOnKEList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnKEList[i]).append("\r\n");
        }
        String resultText = builder.toString();
        try {
            // Creates a FileOutputStream
            FileOutputStream file = new FileOutputStream("data/pau.ini");

            // Creates an OutputStreamWriter
            OutputStreamWriter output = new OutputStreamWriter(file, "cp1251");

            // Writes string to the file
            output.write(resultText);

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void createCorrectUvOnBH(String name, String shunt, String tsWithout, String tsWith, String kpr){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< uvOnBHList.length; i++){
            String uvName = "ОН"+(i+1)+"-ВЧ";
            builder.append("[").append(name).append("_").append(uvName).append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append(shunt).append("\r\n");
            builder.append("\t").append("ts=").append(tsWithout).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335 ").append(kpr).append("\r\n");
            builder.append("\t").append("delays=4 4").append("\r\n");
            builder.append("\t").append("s2=").append(uvOnBHList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnBHList[i]).append("\r\n\r\n");
            builder.append("[").append(name).append("_").append(uvName).append("_").append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append("*ФСч").append("\r\n");
            builder.append("\t").append("ts=").append(tsWith).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335").append("\r\n");
            builder.append("\t").append("delays=0").append("\r\n");
            builder.append("\t").append("s1=").append(uvOnBHList[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnBHList[i]).append("\r\n");
        }
        String resultText = builder.toString();
        try {
            // Creates a FileOutputStream
            FileOutputStream file = new FileOutputStream("data/pau.ini");

            // Creates an OutputStreamWriter
            OutputStreamWriter output = new OutputStreamWriter(file, "cp1251");

            // Writes string to the file
            output.write(resultText);

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void createCorrectUvOnKP(String name, String shunt, String tsWithout, String tsWith, String kpr){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< uvOnKR.length; i++){
            String uvName = "ОН1+2-Крым";
            builder.append("[").append(name).append("_").append(uvName).append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append(shunt).append("\r\n");
            builder.append("\t").append("ts=").append(tsWithout).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335 ").append(kpr).append("\r\n");
            builder.append("\t").append("delays=4 4").append("\r\n");
            builder.append("\t").append("s2=").append(uvOnKR[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnKR[i]).append("\r\n\r\n");
            builder.append("[").append(name).append("_").append(uvName).append("_").append("]").append("\r\n");
            builder.append("\t").append("class=УВКорректировка").append("\r\n");
            builder.append("\t").append("!label=Корректировка2024").append("\r\n");
            builder.append("\t").append("uv=").append("{").append(name).append("}").append(uvName).append("\r\n");
            builder.append("\t").append("slice=").append("*ФСч").append("\r\n");
            builder.append("\t").append("ts=").append(tsWith).append("\r\n");
            builder.append("\t").append("kpr=-79228162514264337593543950335").append("\r\n");
            builder.append("\t").append("delays=0").append("\r\n");
            builder.append("\t").append("s1=").append(uvOnKR[i]).append("\r\n");
            builder.append("\t").append("sdef=").append(uvOnKR[i]).append("\r\n");
        }
        String resultText = builder.toString();
        try {
            // Creates a FileOutputStream
            FileOutputStream file = new FileOutputStream("data/pau.ini");

            // Creates an OutputStreamWriter
            OutputStreamWriter output = new OutputStreamWriter(file, "cp1251");

            // Writes string to the file
            output.write(resultText);

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }


}
