package com.example.projectjavasimba.OtherTasks.training;

import static java.util.Collections.swap;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {

        boolean isSort = false;
        while (!isSort){
            isSort = true;
            for (int i =0; i<valuesArray.length-1; i++){
                if (valuesArray[i] > valuesArray[i+1]){
                    swap(valuesArray, i, i+1);
                    isSort = false;
                }
            }
        }


        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        int max = 0;
        for (int i = 0; i < values.length; i++){
            if (max < values[i]){
                max = values[i];
            }
        }
        return max;
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int right = array.length-1;
        for (int i =0; i < array.length/2; i++){
            swap(array, i, right);
            right--;
        }
        return array;
    }


    /**
     * swap на массив поставил для задач
     **/
    private static void swap(int[] array, int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if (numbersCount <= 0){
            return new int[0];
        }

        int[] fibArr = new int[numbersCount];

        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int i = 2; i < numbersCount; ++i) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        return fibArr;
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        if (array.length == 0)
            return 0;

        sort(array);

        int last = array[0];
        int max = 0;
        int maxCount = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == last)
                maxCount++;
            else {
                max = Math.max(max, maxCount);
                maxCount = 1;
            }
            last = array[i];
        }

        max = Math.max(max, maxCount);
        return max;
    }
}
