import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Node
{
    private File folder;

    //todo делаем список :
    private ArrayList<Node> children;



    private long size;

public Node(File folder){ //todo будем передавать не только папку но и ноду
this.folder = folder;
children = new ArrayList<>(); // список, куда будем добавлять детей
}

    public File getFolder() {
        return folder;
    }

    //todo метод добавления новой ноды:
    public void addChild(Node node){
    children.add(node);
    }
    //todo метод возращающий Аррейлист от Чилдрен:

    public ArrayList<Node> getChildren(){
    return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    //правильно распечатаем ноду:
    public String toString(){
    //размер НОДЫ:
  //   String size =   SizeCalculator.getHumanReadableSize(getSize());
        StringBuilder builder = new StringBuilder();
        String size =   SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName() + " - " + size + "\n");
        //проходимся по подпапкам и делаем тоже самое
        for (Node child : children){
            builder.append("\t" + child.toString());
        }

return builder.toString();
    }






}
