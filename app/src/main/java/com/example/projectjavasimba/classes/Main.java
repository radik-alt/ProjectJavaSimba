package com.example.projectjavasimba.classes;

import com.example.projectjavasimba.classes.first.First;
import com.example.projectjavasimba.classes.five.AbonentDto;
import com.example.projectjavasimba.classes.five.Five;
import com.example.projectjavasimba.classes.four.Four;
import com.example.projectjavasimba.classes.seven.Client;
import com.example.projectjavasimba.classes.seven.Order;
import com.example.projectjavasimba.classes.seven.Product;
import com.example.projectjavasimba.classes.seven.SellerAdmin;
import com.example.projectjavasimba.classes.six.Exam;
import com.example.projectjavasimba.classes.six.Faculty;
import com.example.projectjavasimba.classes.six.Studen;
import com.example.projectjavasimba.classes.three.Third;
import com.example.projectjavasimba.classes.two.Second;
import com.example.projectjavasimba.collections.TaskCollection.CollectionTask;
import com.example.projectjavasimba.collections.TaskCollection.dto.Group;
import com.example.projectjavasimba.collections.TaskCollection.dto.MarkSubject;
import com.example.projectjavasimba.collections.TaskCollection.dto.StudentDto;
import com.example.projectjavasimba.collections.TaskCollection.dto.Subject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class Main {


    public static void main(String[] args) {

    }

    private static void seven(){
        Client client = new Client();
        SellerAdmin admin = new SellerAdmin();
        Order order = new Order();
        Product product = new Product("Наушники", 1000);
        Product product2 = new Product("Ноутбук", 80000);

        admin.createNewProduct(product);
        admin.createNewProduct(product2);
        admin.printAllProduct();


        client.getOrder(order);
        order.addProdToOrder(product);

        // добавить клиента в черный списко
        // admin.addBlackList(client);

        admin.registerOrder(client);
        order.setRegister(true);

        client.pay();
        client.take();
        client.showOrder();
    }

    private static void six(){
        List<Integer> markStudent = new ArrayList<>(3);
        markStudent.add(100);
        markStudent.add(60);
        markStudent.add(80);

        Studen studen = new Studen(0L, "Радик", "Абдулхаков", Faculty.FIST, markStudent);
        Exam exam = new Exam(studen);
    }

    public static void collectionTask(){

        Subject[] list = new Subject[5];
        list[0] = Subject.Math;
        list[1] = Subject.OOP;
        list[2] = Subject.OC;
        list[3] = Subject.React;
        list[4] = Subject.Algo;


        CollectionTask task = new CollectionTask();
        Calendar birthday = new GregorianCalendar();
        List<MarkSubject> studentMarkSubject = new ArrayList<MarkSubject>();
        for (int i = 0; i < Subject.values().length; i++){
            double randomNum = new Random().nextInt(5);
            MarkSubject markSubject = new MarkSubject(list[i], randomNum);
            studentMarkSubject.add(markSubject);
        }

        birthday.set(Calendar.YEAR, 2004);
        StudentDto student = new StudentDto(0L, "Радик", "Абдлухаков", "-", birthday, 3, Group.PI, studentMarkSubject);
        task.addStudent(student);

        Calendar birthday2 = new GregorianCalendar();
        birthday2.set(Calendar.YEAR, 2000);
        StudentDto student2 = new StudentDto(1L, "Илья", "Андреев", "-", birthday2, 1, Group.PI, studentMarkSubject);

        List<MarkSubject> studentMarkSubject2 = new ArrayList<MarkSubject>();
        for (int i = 0; i < Subject.values().length; i++){
            MarkSubject markSubject = new MarkSubject(list[i], 5D);
            studentMarkSubject2.add(markSubject);
        }
        StudentDto student3 = new StudentDto(2L, "Марк", "Синячев", "-", birthday2, 1, Group.PI, studentMarkSubject2);

        task.addStudent(student2);
        task.addStudent(student3);

        task.printList();

        task.getJuniorStudent();
        task.getOldStudent();

        task.averageScoreOfGroup(Group.PI);
        task.getBestStudent();
    }

    private static void first(){
        First first = new First();
        first.changeValues(5, 10);
        first.cmp();
        first.print();
    }

    private static void two(){
        Second two = new Second(5);
        two.fillArray();
        two.printArray();
        two.shuffle();
        two.printArray();
        two.shuffle();
        two.printArray();
    }

    private static void three(){
        Third three = new Third(10, 15, 20, 20);
        three.print();
    }

    private static void four(){
        Four four = new Four(25, 10, 59);
        four.printTime();
    }

    public static void five(){
        AbonentDto dto = new AbonentDto(0L, "89041933985", "Радик", "Абдулхаков", "Раисович",
                "Откбр  32", "312312", "4234324", 0L, 65L);

        Five five = new Five(dto);
        for (int i =0; i < 10; i++){
            int randomNum = new Random().nextInt(100);
            AbonentDto dtoTest = new AbonentDto((long) i, "89041933985", "Юлия" + i, "Бел" + i, "P" + i,
                    "Откбр  32", "312312", "4234324", (long) randomNum, (long) randomNum);
            five.addUser(dtoTest);
        }
        five.getListData();
        five.getListDataOfUrbanTime();
        five.getListDataInternational();
    }

}
