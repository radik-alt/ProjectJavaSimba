package com.example.projectjavasimba.OtherTasks.BlockTwoJava.Three;

public class Rectangle implements Shape{

    private int height;
    private int width;

    public Rectangle(int height, int width){
        this.height = height;
        this.width = width;
    }

    @Override
    public void perimeter() {
        System.out.println("Периметр прямоугольника: " + 2*(height+width));
    }

    @Override
    public void area() {
        System.out.println("Площадь прмоугольника: " + (height*width));
    }
}
