package com.example.projectjavasimba.BlockTwoJava.Two;

/*
*
* Создать enum Directions с возможными направлениями движения
Создать метод, принимающий координаты и одно из направлений и возвращающий координаты после перехода по направлению
Создать метод, осуществляющий несколько переходов от первоначальных координат и выводящий координаты после каждого перехода.
*  Для этого внутри метода следует определить переменную location с начальными координатами (0,0) и массив направлений,
*  содержащий элементы [up, up, left, down, left, down, down, right, right, down, right], и
* програмно вычислить какие будут координаты у переменной location после выполнения этой последовательности шагов.
* Для вычисленеия результата каждого перемещения следует использовать созданный ранее метод перемещения на один шаг.
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
