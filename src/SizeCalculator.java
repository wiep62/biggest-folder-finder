import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    private static  HashMap<Character, Integer>
            char2multiplier = getMultipliers();
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    //todo рекурсивная функция  считает объем папок:
    //у корневой папаки вызываем getFolderSize и суммируем размеры всех файлов во всех папках
    public static long getFolderSize(File folder){
        if (folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        //получ. спирк файлов в папке%:
        File[] files = folder.listFiles();
        for (File file : files){
            sum += getFolderSize(file);
        }
        return  sum;
    }

    //todo 24B,234kb,36Mb,34Gb,42Tb
    static String strValreturn;

    public static String getHumanReadableSize(long size){

        for (int i = 0; i < sizeMultipliers.length; i++){
            double value = ((double) size) / Math.pow(1024.0, i);
            if (value < 1024) {
                return Math.round(value * 100) / 100 + " " + sizeMultipliers[i] +
                        (i > 0 ? "b" : "");
            }
        }
        return "very big!";

    }
    //todo написать метод, который преобразует  24B,234kb,36Mb,34Gb,42Tb в байты:
    public static long getSizeFromHumanReadable(String size){
        char sizeFactor = size
                .replaceAll("[0-9\\s+]", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);  //множитель
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", "")) ;
        return length;
    }

    private static HashMap<Character, Integer> getMultipliers()
    {
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipliers.length; i++){
            char2multiplier.put(
                    multipliers[i],
                    (int) Math.pow(1024, i)
            );
        }
        return char2multiplier;
        /*char2multiplier.put('B', 1);
        char2multiplier.put('K', 1024);
        char2multiplier.put('M', 1024 * 1024);
        char2multiplier.put('G', 1024 * 1024 * 1024);
        char2multiplier.put('T', 1024 * 1024 * 1024 * 1024);*/
    }
}
