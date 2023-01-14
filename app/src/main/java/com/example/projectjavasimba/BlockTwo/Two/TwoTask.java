package com.example.projectjavasimba.BlockTwo.Two;

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

    public int[] move(Directions directions){
        switch (directions) {
            case UP: location[0] += 1;
            case DOWN: location[0] -= 1;
            case LEFT: location[1] -= 1;
            case RIGHT: location[1] += 1;
        }

        return location;
    }

    public void multiMove(Directions[] directions){
        for (Directions direction : directions) {
            switch (direction) {
                case UP:
                    location[0] += 1;
                case DOWN:
                    location[0] -= 1;
                case LEFT:
                    location[1] -= 1;
                case RIGHT:
                    location[1] += 1;
            }
            printLocation();
        }
    }

    private void printLocation(){
        System.out.print("Y-позиция:" + location[0] + " X-позиция:" + location[1]);
        System.out.println();
    }
}
