package com.example.projectjavasimba.OtherTasks.BlockTwoJava.Three;

public class Circle implements Shape{

    private int diameter;

    public Circle(int diameter){
        this.diameter = diameter;
    }

    @Override
    public void perimeter() {
        double perimeter = Math.PI * diameter;
        System.out.println("Периметр круга: " + perimeter);
    }

    @Override
    public void area() {
        double area = Math.PI * Math.pow(diameter, 2);
        System.out.println("Площадь круга: " + area/4);
    }
}
