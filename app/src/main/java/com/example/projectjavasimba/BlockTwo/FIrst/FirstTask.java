package com.example.projectjavasimba.BlockTwo.FIrst;

public class FirstTask{

    /*
       Написать простое лямбда-выражение в переменной myClosure, лямбда-выражение должно выводить в консоль фразу "I love Java".
       Вызвать это лямбда-выражение. Далее написать функцию, которая будет запускать заданное лямбда-выражение заданное количество раз.
       Объявить функцию так: public void repeatTask (int times, Runnable task).
       Функция должна запускать times раз лямбда-выражение task . Используйте эту функцию для печати "I love Java" 10 раз.
    */
    public void repeatTask (int times, MyClosure task){
        for (int i =0; i < times; i++){
            System.out.println(task.print());
        }
    }

}
