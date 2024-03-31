import org.w3c.dom.Node;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {
//private File folder;
/*public FolderSizeCalculator(File folder){
        this.folder = folder;
    }*/
private Node node;
    public FolderSizeCalculator(Node node){
    this.node = node;
}
    @Override
    protected Long compute() {

    //todo определяем папку:
        if (folder.isFile()){
            return folder.length(); //если файл, то возвр-ем размер
        }
        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>(); //todo LinkedList - связный список
       //ЕСЛИ ПАПКА:
        File[] files = folder.listFiles();
        for (File file  : files){
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); //отделяем  в отдельный поток
            subTasks.add(task); //добавляем в список подзадач
        }
        for (FolderSizeCalculator task : subTasks){  //todo собираем список подзадач
            sum += task.join(); //
        }
        return sum;
    }
}
