import java.util.*;
public class Stack {
    int[] arr;
    int capacity;
    int stackTop;

    public Stack(int capacity){
        this.capacity=capacity;
        arr=new int[capacity];
        stackTop=-1;
    }

    private boolean isEmpty(){
        return stackTop<0;
    }
    private boolean isFull(){
        return stackTop==capacity;
    }

    public synchronized boolean push(int val){
        // this applying at method level is actually we are applying block level on complete
        // function and for lock this object act a lock too so no need to make new lock object
        if(isFull())return false;

        ++stackTop;
        try{Thread.sleep(1000);}catch (Exception e){}
        arr[stackTop]=val;
        return true;
    }
    public synchronized int pop() {
        if (isEmpty()) return -1;
        int ele = arr[stackTop];
                arr[stackTop] = Integer.MIN_VALUE;
        try{Thread.sleep(1000);}catch (Exception e){}
//        System.out.print(" "+stackTop);
        stackTop--;
        return ele;
    }
}
