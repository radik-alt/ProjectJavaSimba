package com.example.projectjavasimba.BlockTwo.FIrst;

public class FirstTask{


    public void repeatTask (int times, MyClosure task){
        for (int i =0; i < times; i++){
            System.out.println(task.print());
        }
    }

}
