package com.example.projectjavasimba.OtherTasks.classes.five;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.sort;


/*
    Пускай время городских переговоров превышает заданное больше 60! (Радик, мой комментарий)
    Сведения относительно абонентов, которые пользовались междугородной связью пусть -
    это пусть будет просто больше 0 (Радик)

      V
      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

public class Five {

    private ArrayList<AbonentDto> listUsers = new ArrayList<>();

    public Five(AbonentDto user){
        addUser(user);
    }

    public void addUser(AbonentDto user){
        listUsers.add(user);
        Collections.sort(listUsers);
    }

    public void getListData(){
        System.out.println("Все юзеры: ");
        for (AbonentDto data : listUsers){
            System.out.println(data);
        }
        System.out.println();
    }

    public void getListDataOfUrbanTime(){
        System.out.println("Юзеры у которых время городского разговора превышает заданного (60):");
        for (AbonentDto data : listUsers){
            if (data.getTimeUrbanConversation() > 60L){
                System.out.println(data);
            }
        }
        System.out.println();
    }

    private void sortAlpha(){
        getListData();
    }

    public void getListDataInternational(){
        System.out.println("Юзеры которые пользовались междугородной связью:");
        for (AbonentDto data : listUsers){
            if (data.getTimeIntarnationalConversation() > 0){
                System.out.println(data);
            }
        }
        System.out.println();
    }
}
