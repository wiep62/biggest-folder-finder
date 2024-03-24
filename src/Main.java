import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
String folderPath = "D:\\JAVA\\CORE\\biggest-folder-finder\\BiggestFolderFinder\\data";
File file = new File(folderPath);

//размер директории: (размер блока данных лежащий в данной папке (не количество папок а объем ссылок на них))
        System.out.println(file.length());
Set keys = System.getProperties().keySet(); //в системных ПРОПЕРТИ ест путь к файлам
for (Object key : keys){
    System.out.println(key);
}

//System.out.println(System.getProperties().get("user.dir")); //todo показ. рабочую директорию

        System.out.println(getFolderSize(file));

    }
    //todo рекурсивная функция:
    //у корневой папаки вызываем getFolderSize и суммируем размеры всех файлов во всех папках
    public static long getFolderSize(File folder){ //todo считает объем папок
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