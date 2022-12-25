package com.example.projectjavasimba.BlockTwoJava.Three;

/*
Создать интерфейс Shape с двумя методами perimeter и area, выводящими периметр и площадь фигуры соответственно,
 после чего реализовать и использовать для вывода периметра и площади следующие классы, реализующие интерфейс Shape:
Rectangle - прямоугольник с двумя свойствами: ширина и длина
Square - квадрат с одним свойством: длина стороны
Circle - круг с одним свойством: диаметр круга
* */

public interface Shape {

    void perimeter();
    void area();

}
