import com.sun.jdi.IntegerValue;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        System.out.println(getHumanReadableSize(29388888));
        //System.out.println(getSizeFromHumanReadable("235K"));
System.exit(0);
        String folderPath ="C:\\Users\\wiep6\\OneDrive";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();
        FolderSizeCalculator folderSizeCalculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool(); //сложная система запуска многопоточности
      long size =  pool.invoke(folderSizeCalculator); //.invoke vozvr. razmer
        System.out.println( " ====> Razmer : " + size);
        long duration =  System.currentTimeMillis() - start;
        System.out.println(duration + " Miliseconds" );
/**
//String folderPath = "D:\\JAVA\\CORE\\biggest-folder-finder\\BiggestFolderFinder\\data";
        String folderPath ="C:\\Users\\wiep6\\OneDrive";
                File file = new File(folderPath);

размер директории: (размер блока данных лежащий в данной папке (не количество папок а объем ссылок на них))
        System.out.println(file.length());
Set keys = System.getProperties().keySet(); //в системных ПРОПЕРТИ ест путь к файлам
for (Object key : keys){
    System.out.println(key);
}

//System.out.println(System.getProperties().get("user.dir")); //todo показ. рабочую директорию
long start = System.currentTimeMillis();
        System.out.println(getFolderSize(file));
long duration =  System.currentTimeMillis() - start;
System.out.println(duration + " Miliseconds");*/
    }
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

        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};

    /* if (size / (Math.pow(2, 10)) < 1000 && size >=1000) {
            long    strVal = Math.round(size / Math.pow(2, 10)) ;
            return   strValreturn = strVal + "k";

        }else if (size / (Math.pow(2, 20)) < 1000 && size >=Math.pow(2, 10)) {
            long   strVal = Math.round(size / Math.pow(2, 20));
            return   strValreturn = strVal + "M";
        }else if (size / (Math.pow(2, 30)) < 1000 && size >=Math.pow(2, 20)) {
            long    strVal = Math.round(size / Math.pow(2, 30)) ;
            return    strValreturn = strVal + "G";
        }else if (size / (Math.pow(2, 40)) < 1000 && size >=Math.pow(2, 30)) {
        long    strVal = Math.round(size / Math.pow(2, 40)) ;
            return     strValreturn = strVal + "T";
        }else {
            return   strValreturn = size + "B";

        }*/

    }
    //todo написать метод, который преобразует  24B,234kb,36Mb,34Gb,42Tb в байты:
    public static long getSizeFromHumanReadable(String size){
        HashMap<Character, Integer> char2multiplier = getMultipliers();
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