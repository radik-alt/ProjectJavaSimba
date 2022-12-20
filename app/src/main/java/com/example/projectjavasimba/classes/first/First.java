package com.example.projectjavasimba.classes.first;

public class First implements FirstInterface {

    private int one = 0;
    private int two = 0;

    public void changeValues(int one, int two){
        this.one = one;
        this.two = two;
    }

    @Override
    public void sumValues(){
        System.out.println("Sum: " + one + two);
    }

    @Override
    public void cmp(){
        if (one > two){
            System.out.println("Набольшее: " + one);
        } else {
            System.out.println("Набольшее: " + two);
        }
    }

    @Override
    public void print(){
        System.out.println("One - " + one + ", Two - " + two);
    }
}
