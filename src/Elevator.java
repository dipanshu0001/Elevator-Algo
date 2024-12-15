import java.util.TreeSet;



public class Elevator {
    private static Elevator elevator=null;
    private TreeSet requestSet=new TreeSet();
    private int currentFloor=0;
    private Directions direction=Directions.UP;

    private Elevator(){}

    private Thread requestProcessorThread;


    static Elevator getInstance(){
        if(elevator==null){
            elevator=new Elevator();
        }
        return elevator;
    }


    public synchronized void addFloor(int f){
        requestSet.add(f);
        if(requestProcessorThread.getState()==Thread.State.WAITING){
            notify();
        }else{
            requestProcessorThread.interrupt();
        }
    }

    public synchronized int nextFloor(){
        Integer floor=null;
        if(Directions.UP.equals(direction)){
            if(requestSet.ceiling(currentFloor)!=null){
                floor= (Integer) requestSet.ceiling(currentFloor);
            }else{
                floor= (Integer) requestSet.floor(currentFloor);
            }
        }else if(Directions.DOWN.equals(direction)){
            if(requestSet.floor(currentFloor)!=null){
                floor= (Integer) requestSet.floor(currentFloor);
            }else{
                floor= (Integer) requestSet.ceiling(currentFloor);
            }
        }
        if (floor == null) {
            try {
                System.out.println("Waiting at Floor :" + getCurrentFloor());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Remove the request from Set as it is the request in Progress.
            requestSet.remove(floor);
        }
        return (floor == null) ? -1 : floor;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }
    public void setDirection(Directions direction){
        this.direction=direction;
    }

    public void setCurrentFloor(int currentFloor) throws InterruptedException {
        if(this.currentFloor>currentFloor){
            setDirection(Directions.DOWN);
        }else if(this.currentFloor<currentFloor){
            setDirection(Directions.UP);
        }
        this.currentFloor=currentFloor;
        System.out.println("Floor: "+currentFloor);
        Thread.sleep(3000);
    }

    public Directions getDirection(){
        return direction;
    }

    public Thread getRequestProcessorThread(){
        return requestProcessorThread;
    }
    public void setRequestProcessorThread(Thread requestProcessorThread){
        this.requestProcessorThread=requestProcessorThread;
    }

    public TreeSet getRequestSet(){
        return requestSet;
    }
    public void setRequestSet(TreeSet requestSet) {
        this.requestSet = requestSet;
    }
}
