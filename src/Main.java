import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

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
}