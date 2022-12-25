package com.example.projectjavasimba.classes.three;


/*
      III
      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */
public class Third implements ThirdInterface{

    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int h = 0;

    public Third(int a, int b, int c, int h){
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
    }

    @Override
    public void getSquare(){
        double square = (c*h)/2;
        System.out.println("Площадь: " + square);
    }

    @Override
    public void getMedian(){
        double pointIntersection = (Math.sqrt(2*Math.pow(a, 2) + 2 * Math.pow(b, 2) - Math.pow(c, 2))) / 2;
        System.out.println("Точка пересечения медианы: " + pointIntersection);
    }

    @Override
    public void getPerimeter(){
        int perimeter = a + b + c;
        System.out.println("Периметр: " + perimeter);
    }

    @Override
    public void print(){
        System.out.println("Треугольник со сторонами: a = " + a + ", b = " + b + ", c = "+ c + ". И высотой h = "+h);
        getSquare();
        getPerimeter();
        getMedian();
    }

}
