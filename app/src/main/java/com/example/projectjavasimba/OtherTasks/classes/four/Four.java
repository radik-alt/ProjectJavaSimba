package com.example.projectjavasimba.OtherTasks.classes.four;

import java.util.Calendar;
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
        if (!validTime(hour, minute, second)){
            try {
                throw new Exception("Ошибка! Не корректно указано время!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Не корректное время!");
            }
        } else {
            changeTime(hour, minute, second);
        }
    }

    public void changeTime(int hour, int minute, int second){
        time.set(Calendar.HOUR, hour);
        time.set(Calendar.MINUTE, minute);
        time.set(Calendar.SECOND, second);
    }

    public void changeHour(int hour){
        time.set(Calendar.HOUR, hour);
    }

    public void changeMinute(int minute){
        time.set(Calendar.MINUTE, minute);
    }

    public void changeSecond(int second){
        time.set(Calendar.SECOND, second);
    }

    private boolean validTime(int hour, int minute, int second){
        return hour <= 24 && minute <= 60 && second <= 60;
    }

    public void printTime(){
        System.out.println("Hour: " + time.get(Calendar.HOUR) + "\n" +
                "Минуты: " + time.get(Calendar.MINUTE) + "\n" +
                "Секунды: "+ time.get(Calendar.SECOND));
    }

}

