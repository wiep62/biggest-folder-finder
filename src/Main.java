import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        System.out.println(getHumanReadableSize(2939165973));
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
   static String strVal;
    public static String getHumanReadableSize(long size){


        if (size < 1000){
          strVal = size + "B";
        } else if (size / (Math.pow(2, 10)) < 1000 && size >=1000) {
            strVal = Math.round(size / Math.pow(2, 10)) + "kb";
        }else if (size / (Math.pow(2, 20)) < 1000 && size >=Math.pow(2, 10)) {
            strVal = Math.round(size / Math.pow(2, 20)) + "Mb";
        }else if (size / (Math.pow(2, 30)) < 1000 && size >=Math.pow(2, 20)) {
            strVal = Math.round(size / Math.pow(2, 30)) + "Gb";
        }else if (size / (Math.pow(2, 40)) < 1000 && size >=Math.pow(2, 30)) {
            strVal = Math.round(size / Math.pow(2, 40)) + "Tb";
        }
        return strVal;
    }
    //todo написать метод, который преобразует  24B,234kb,36Mb,34Gb,42Tb в байты:
    public long getSizeFromHumanReadable(String size){
        return 0;
    }
}