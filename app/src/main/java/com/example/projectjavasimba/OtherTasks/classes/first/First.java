package com.example.projectjavasimba.OtherTasks.classes.first;


/*
      I
      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
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
