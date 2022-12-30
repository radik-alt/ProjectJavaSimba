package com.example.projectjavasimba.classes.two;

import static java.util.Arrays.sort;

import java.util.ArrayList;
import java.util.List;
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

    private final ArrayList<Integer> arr;

    public Second(int n){
        arr = new ArrayList<>(n);
    }

    @Override
    public void fillArray(){
        for (int i = 0; i < arr.size(); i++){
            arr.set(i, ((int) (Math.random() * 31) - 15));
        }
    }

    @Override
    public void shuffle(){
        for (int i = arr.size() -1; i >= 1; i--){
            int j = new Random().nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    private void swap(List<Integer> arr, int left, int right){
        int temp = arr.get(left);
        arr.set(left, arr.get(right));
        arr.set(right, temp);
    }

    @Override
    public void findChangeElementsOfArray(){
        int count = 1;
        List<Integer> sortArray = arr;
        sortList(sortArray);

        for (int i = 0; i < sortArray.size()-1; i++) {
            if(sortArray.get(i) != sortArray.get(i + 1)) {
                count++;
            }
        }

        System.out.println("Кол-во различных элементов: " + count);
    }

    private void sortList(List<Integer> list){

    }

    @Override
    public void printArray(){
        for (int i = 0; i < arr.size(); i++){
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();;
    }
}
