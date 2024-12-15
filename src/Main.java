// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello and welcome!");
//        Stack s=new Stack(5);
//
//        Thread t1=new Thread(()->{
//            for(int i=0;i<10;i++){
//                System.out.println("Pushing element and currently on thread"+Thread.currentThread()+" "+s.push(100));
//
//            }
//        },"Thread1");
//
//        Thread t2=new Thread(()->{
//            for(int i=0;i<10;i++){
//                System.out.println("Pop element and currently on thread"+Thread.currentThread()+" "+s.pop());
//            }
//        },"Thread2");
//
//        t1.start();
//        t2.start();
        System.out.println("Welcome to Liftify");
        Thread requestListenerThread=new Thread(new RequestListener(),"RequestListener");
        Thread requestProcessorThread=new Thread(new RequestProcessor(),"RequestProcessor");

        Elevator.getInstance().setRequestProcessorThread(requestProcessorThread);
        requestListenerThread.start();
        requestProcessorThread.start();
    }
}