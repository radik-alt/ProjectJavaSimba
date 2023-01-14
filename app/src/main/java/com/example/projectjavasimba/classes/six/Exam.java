package com.example.projectjavasimba.classes.six;

import java.util.List;

/*
     VI
     Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
     Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
     Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
    */
public class Exam implements ExamInterface {

    private Studen studen;

    public Exam(Studen studen){
        this.studen = studen;
        if (validStudent()){
            System.out.println("Студент: " + studen.getFirstName() + " прошел на факультет: " + studen.getFaculty());
        } else {
            System.out.println("Студент: " + studen.getFirstName() + " не прошел на факультет: " + studen.getFaculty());
        }
    }

    private boolean validStudent(){
        Integer minScore = minMarkOfFaculty(studen.getFaculty());
        List<Integer> markStudent = studen.getScore();

        Integer allScore = 0;
        for (Integer score : markStudent) {
            allScore += score;
        }

        return allScore >= minScore;
    }

    private Integer minMarkOfFaculty(Faculty faculty){
        switch (faculty){
            case FIST:
                return 230;
            case Gum:
                return 190;
            case Econom:
                return 200;
            case IE:
                return 180;
            default:
                return null;
        }
    }

    @Override
    public void printDataStudent(){
        System.out.println(studen);
    }


}
