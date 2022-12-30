package com.example.projectjavasimba.classes.four;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/*
      IV
      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */
public class Four implements  FourInterface{

    private Calendar time;

    public Four(int hour, int minute, int second){
        time = new GregorianCalendar();
        changeTime(time.get(Calendar.HOUR), time.get(Calendar.MINUTE), time.get(Calendar.SECOND));
    }

    public void changeTime(int hour, int minute, int second){
        if (!validTime(hour, minute, second)){
            try {
                throw new Exception("Ошибка! Не корректно указано время!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Не корректное время!");
            }
        } else {
            time.set(Calendar.HOUR, hour);
            time.set(Calendar.MINUTE, minute);
            time.set(Calendar.SECOND, second);
        }
    }

    public void changeHour(int hour){
        if (!validHour(hour)){
            try {
                throw new Exception("Ошибка! Не корректно указаны часы!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Не корректное время!");
            }
        }

        time.set(Calendar.HOUR, hour);
    }

    public void changeMinute(int minute){
        if (!validMinute(minute)){
            try {
                throw new Exception("Ошибка! Не корректно указаны часы!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Не корректное время!");
            }
        }

        time.set(Calendar.MINUTE, minute);
    }

    public void changeSecond(int second){
        if (!validSecond(second)){
            try {
                throw new Exception("Ошибка! Не корректно указаны часы!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Не корректное время!");
            }
        }

        time.set(Calendar.SECOND, second);
    }

    private boolean validTime(int hour, int minute, int second){
        return hour <= 24 && minute <= 60 && second <= 60;
    }

    private boolean validHour(int hour){
        return hour <= 24;
    }

    private boolean validMinute(int minute){
        return minute <= 60;
    }

    private boolean validSecond(int second){
        return second <= 60;
    }

    public void printTime(){
        System.out.println("Hour: " + time.get(Calendar.HOUR) + "\n" +
                "Минуты: " + time.get(Calendar.MINUTE) + "\n" +
                "Секунды: "+ time.get(Calendar.SECOND));
    }

}

