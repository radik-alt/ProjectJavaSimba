package com.example.projectjavasimba.classes.five;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.sort;

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
