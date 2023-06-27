package com.example.projectjavasimba.OtherTasks.training;

import java.util.ArrayList;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder value = new StringBuilder();
        int n = text.length()-1;
        for (int i = 0; i <= n; i++){
            if (i % 2 == 1)
                value.append(text.charAt(i));
        }
        return value.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        if (text.isEmpty())
            return new int[0];

        int n = text.length()-1;
        List<Integer> valueIndex = new ArrayList<>();

        char last = text.charAt(n);
        for (int i =0; i < n; i++){
            char symbol = text.charAt(i);
            if (symbol == last){
                valueIndex.add(i);
            }
        }

        int[] value = new int[valueIndex.size()];
        for (int i = 0; i < valueIndex.size(); i++){
            value[i] = valueIndex.get(i);
        }

        return value;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int count = 0;
        for (int i = 0, len = text.length(); i < len; i++) {
            if (Character.isDigit(text.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        StringBuilder textValue = new StringBuilder(text);
        for (int i = 0; i < textValue.length(); i++){
            char getSymbol = textValue.charAt(i);
            if (Character.isDigit(getSymbol)){
                String replaceNumber = getReplaceNumber(getSymbol);
                textValue.replace(i, i+1, replaceNumber);
            }
        }

        return textValue.toString();
    }

    private String getReplaceNumber(Character number){
        switch (number){
            case '0':
                return "zero";
            case '1':
                return "one";
            case '2':
                return "two";
            case '3':
                return "three";
            case '4':
                return "four";
            case '5':
                return "five";
            case '6':
                return "six";
            case '7':
                return "seven";
            case '8':
                return "eight";
            case '9':
                return "nine";
            default:
                return "";
        }
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        StringBuilder value = new StringBuilder(text);
        for (int i = 0, len = text.length(); i < len; i++) {
            char symbol = text.charAt(i);
            if (Character.isLowerCase(symbol)) {
                value.replace(i, i+1, String.valueOf(Character.toUpperCase(symbol)));
            } else {
                value.replace(i, i+1, String.valueOf(Character.toLowerCase(symbol)));
            }
        }
        return value.toString();
    }

}
