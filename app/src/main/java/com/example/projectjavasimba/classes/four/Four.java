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
        try {
            if (validTime(hour, minute, second)){
                time.set(Calendar.HOUR, hour);
                time.set(Calendar.MINUTE, minute);
                time.set(Calendar.SECOND, second);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void changeHour(int hour){
        try {
            if (validHour(hour)){
                time.set(Calendar.HOUR, hour);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMinute(int minute){
        try {
            if (validMinute(minute)){
                time.set(Calendar.MINUTE, minute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSecond(int second){
        try {
            if (validSecond(second)){
                time.set(Calendar.SECOND, second);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validTime(int hour, int minute, int second) throws Exception {
        if (hour <= 24 && minute <= 60 && second <= 60){
            return true;
        }
        throw new Exception("Не корректно указаны время!");
    }

    private boolean validHour(int hour) throws Exception{
        if (hour <= 24) {
            return true;
        }
        throw new Exception("Не корректно указаны часы!");
    }

    private boolean validMinute(int minute) throws Exception {
        if (minute <= 60){
            return true;
        }
        throw new Exception("Не корректно указаны минуты!");
    }

    private boolean validSecond(int second) throws Exception {
        if (second <= 60) {
            return true;
        }
        throw new Exception("Не корректно указаны часы!");
    }

    public void printTime(){
        System.out.println("Hour: " + time.get(Calendar.HOUR) + "\n" +
                "Минуты: " + time.get(Calendar.MINUTE) + "\n" +
                "Секунды: "+ time.get(Calendar.SECOND));
    }

}

