public class RequestProcessor implements Runnable{

    @Override
    public void run() {
        while(true){
            Elevator elevator=Elevator.getInstance();
            int floor=elevator.nextFloor();
            int currentFloor=elevator.getCurrentFloor();
            try{
                if(floor>=0){
                    if(currentFloor>floor){
                        while(currentFloor>floor){
                            elevator.setCurrentFloor(--currentFloor);
                        }
                    }else{
                        while(currentFloor<floor){
                            elevator.setCurrentFloor(++currentFloor);
                        }
                    }
                    System.out.println("Welcome to Floor: "+elevator.getCurrentFloor());
                }
            } catch (InterruptedException e) {
                //if current request interpetd already goind on process
                // then need to check and add back to floor

                if(elevator.getCurrentFloor()!=floor){
                    elevator.addFloor(floor);
                }
            }
        }
    }
}
