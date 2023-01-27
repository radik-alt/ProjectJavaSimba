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
        Long minScore = minMarkOfFaculty(studen.getFaculty());
        List<Integer> markStudent = studen.getScore();

        Long allScore = 0L;
        for (Integer score : markStudent) {
            allScore += setMarkFromTeacher(score);
        }

        Long midMark = allScore/markStudent.size();

        return midMark >= minScore;
    }

    private Long setMarkFromTeacher(Integer score){
        return (long) (score / 100) * 5;
    }

    private Long minMarkOfFaculty(Faculty faculty){
        switch (faculty){
            case FIST:
                return 4L;
            case Gum:
                return 3L;
            case Econom:
                return 2L;
            case IE:
                return 1L;
            default:
                return null;
        }
    }

    @Override
    public void printDataStudent(){
        System.out.println(studen);
    }


}
