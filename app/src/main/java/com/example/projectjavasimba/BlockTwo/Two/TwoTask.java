package com.example.projectjavasimba.BlockTwo.Two;

/*
*
* Движения по пикселям, через класс Directions (up, down, left, right)
*
* */
public class TwoTask {

    private final int[] location = new int[2];

    public void move(Directions directions){
        switch (directions) {
            case up: location[0] += 1;
            case down: location[0] -= 1;
            case left: location[1] -= 1;
            case right: location[1] += 1;
        }

        printLocation();
    }

    private void printLocation(){
        System.out.print("Y-позиция:" + location[0] + " X-позиция:" + location[1]);
        System.out.println();
    }
}
