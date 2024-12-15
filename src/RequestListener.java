import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestListener implements  Runnable{

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        while (true) {
            String floorNumberStr=null;
            try{
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
                floorNumberStr=bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isValidFloorNumber(floorNumberStr)){
                System.out.println("User Pressed : "+floorNumberStr);
                Elevator elevator=Elevator.getInstance();
                elevator.addFloor(Integer.parseInt(floorNumberStr));
            }else{
                System.out.println("Floor Request Invaild : "+floorNumberStr);
            }
        }
    }
    public boolean isValidFloorNumber(String s){
        return (s!=null) && s.matches("\\d{1,2}");
    }
}
