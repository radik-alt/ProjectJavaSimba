package com.example.projectjavasimba.BlockTwoJava.Three;

public class Square implements Shape{

    private int a;

    public Square(int a){
        this.a = a;
    }

    @Override
    public void perimeter() {
        System.out.println("Периметр квадрата: " + 4*a);
    }

    @Override
    public void area() {
        System.out.println("Площадь квадрата: " + Math.pow(a, 2));
    }
}
