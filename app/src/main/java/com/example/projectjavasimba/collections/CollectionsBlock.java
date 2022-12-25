package com.example.projectjavasimba.collections;


import static java.util.Collections.reverse;
import static java.util.Collections.sort;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        List<T> fullList = new ArrayList<T>();
        fullList.addAll(firstList);
        fullList.addAll(secondList);
        sort(fullList);
        reverse(fullList);
        return fullList;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        List<T> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++){
            resultList.add(inputList.get(i));
            resultList.addAll(inputList.subList(0, i));
        }
        return resultList;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        if (firstList == null || secondList == null)
            throw new NullPointerException();
        if (firstList.isEmpty() && secondList.isEmpty())
            return true;

        for (int i =0; i < firstList.size(); i++){
            if (secondList.contains(firstList.get(i))){
               return true;
            }
        }
        return false;
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        if (inputList == null)
            throw new NullPointerException();

        if (inputList.isEmpty())
            return Collections.emptyList();

        int start = 0;
        int end = inputList.size()-1;

        List<Integer> newList = new ArrayList<>(4);
        newList.addAll(List.of(0, 0, 0, 0));

        for (int i = 0; i <= end; i++){
            if (n > 0){
                int newIndex = getNewIndex(i+n, end, start, true);
                newList.set(newIndex, (Integer) inputList.get(i));
            } else if (n < 0){
                int newIndex = getNewIndex(i+n, end, start, false);
                newList.set(newIndex, (Integer) inputList.get(i));
            }
        }
        return (List<T>) newList;
    }

    private static int getNewIndex(Integer index, int end, int start, boolean flag){
        if (flag && index > end){
            int newIndex = index - end -1;
            while (newIndex > end){
                newIndex = newIndex - end -1;
            }
            return newIndex;
        }
        else if (!flag && index < start) {
            int newIndex = end + index + 1;
            while (newIndex < start){
                newIndex = end + newIndex + 1;
            }
            return newIndex;
        }

        return index;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        if (inputList ==null || a == null || b == null)
            throw new NullPointerException();

        if (inputList.isEmpty())
            return inputList;

        for (int i = 0; i < inputList.size(); i++){
            if (inputList.get(i).contains(a)){
                if (inputList.get(i).contains(a)){
                    String temp = inputList.get(i).replaceAll(a, b);
                    inputList.set(i, temp);
                }
            }
        }
        return inputList;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
}
