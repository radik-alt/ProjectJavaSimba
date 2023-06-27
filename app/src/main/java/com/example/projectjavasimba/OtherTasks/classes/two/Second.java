package com.example.projectjavasimba.OtherTasks.classes.two;

import static java.util.Arrays.sort;

import java.util.Random;


/*
      II
      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */
public class Second implements  SecondInterface{

    private int[] arr;

    public Second(int n){
        arr = new int[n];
    }

    @Override
    public void fillArray(){
        for (int i = 0; i < arr.length; i++){
            arr[i] = ((int)(Math.random() * 31) - 15);
        }
    }

    @Override
    public void shuffle(){
        for (int i = arr.length -1; i >= 1; i--){
            int j = new Random().nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    private void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    @Override
    public void findChangeElementsOfArray(){
        int count = 1;
        int[] sortArray = arr;
        sort(sortArray);

        for (int i = 0; i < sortArray.length-1; i++) {
            if(sortArray[i]!=sortArray[i+1]) {
                count++;
            }
        }

        System.out.println("Кол-во различных элементов: " + count);
    }

    @Override
    public void printArray(){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();;
    }
}
