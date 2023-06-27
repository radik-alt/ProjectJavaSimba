package com.example.projectjavasimba.OtherTasks.training;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public double averageValue(int firstValue, int secondValue) {
        double mid = firstValue + secondValue;
        return mid/2;
    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public double complicatedAmount(int firstValue, int secondValue, int thirdValue) {
        int first = firstValue * 2;
        int second = secondValue - 3;
        int third = thirdValue * thirdValue;
        return first + second + third;
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public int changeValue(int value) {
        value += value > 3 ? 10 : -10;
        return value;
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public int swapNumbers(int value) {
        if (value < 10)
            return value;

        String valueStr = String.valueOf(value);
        valueStr = valueStr.substring(valueStr.length()-1)+valueStr.substring(1, valueStr.length()-1)+valueStr.charAt(0);
        value = Integer.parseInt(valueStr);

        return value;
    }

    /**
     * Изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public int zeroEvenNumber(int value) {
        if (value < 10)
            return value;

        String valueStr = String.valueOf(value);
        StringBuilder strBuilder = new StringBuilder(valueStr);
        for (int i = 0; i < strBuilder.length(); i++){
            if (Character.isDigit(strBuilder.charAt(i)) && Integer.parseInt(String.valueOf(strBuilder.charAt(i))) % 2 == 0){
                strBuilder.replace(i, i+1, "0");
            }
        }

        return Integer.parseInt(String.valueOf(strBuilder));
    }
}
