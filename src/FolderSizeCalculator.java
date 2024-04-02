import org.w3c.dom.Node;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long>  {
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
     /*   if (folder.isFile()){
            return folder.length(); //если файл, то возвр-ем размер
        }*/
File folder = node.getFolder();
        if (folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>(); //todo LinkedList - связный список
       //ЕСЛИ ПАПКА:
        File[] files = folder.listFiles();
        for (File file  : files){
            Node child = new Node(file);
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            task.fork(); //отделяем  в отдельный поток
            subTasks.add(task); //добавляем в список подзадач
            node.addChild(child);
        }
        for (FolderSizeCalculator task : subTasks){  //todo собираем список подзадач
            sum += task.join(); //
        }
        node.setSize(sum);
        return sum;
    }
}
