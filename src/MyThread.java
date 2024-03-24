public class MyThread extends Thread{

    private int threadNumber;
    public MyThread(int threadNumber){
 this.threadNumber = threadNumber;
    }
    @Override
    public void run() { //пишем код который будет работать в отдельном потоке (все что хотим пишем):
       // super.run();
        for (;;){
            System.out.println(threadNumber);
        }
    }
}
