package com.example.projectjavasimba.OtherTasks.collections.TaskCollection;

import com.example.projectjavasimba.OtherTasks.collections.TaskCollection.dto.StudentDto;
import com.example.projectjavasimba.OtherTasks.collections.TaskCollection.dto.Group;
import com.example.projectjavasimba.OtherTasks.collections.TaskCollection.dto.MarkSubject;


import java.util.*;

/*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */

public class CollectionTask {

    private final List<StudentDto> studentList = new ArrayList<>();

    public void addStudent(StudentDto student){
        studentList.add(student);
        sortAlphabet();
    }

    public void sortAlphabet() {
        Collections.sort(studentList);
    }

    public void averageScoreOfGroup(Group group){
        List<Double> markSubject = new ArrayList<>();
        markSubject.addAll(List.of(0D, 0D, 0D, 0D, 0D));
        int countStudent = 0;
        for (StudentDto student: studentList){
            if (student.getNumberGroup() == group){
                List<MarkSubject> listMark = student.getMarkOfSubject();
                double mark = 0D;
                for (int i = 0; i < listMark.size(); i++){
                    mark = listMark.get(i).getMark() + markSubject.get(i);
                    markSubject.set(i, mark);
                }
                countStudent++;
            }
        }

        for (int i = 0; i < markSubject.size(); i++){
            markSubject.set(i, markSubject.get(i) / countStudent);
        }

        for (Double mark: markSubject){
            System.out.println("Средний балл группы по предмету = " + mark );
        }
    }

    public void getJuniorStudent(){
        StudentDto juniorStudent = studentList.get(0);
        for (StudentDto student : studentList){
            if (student.getBirthday().after(juniorStudent.getBirthday())){
                juniorStudent = student;
            }
        }

        System.out.println("Самый молодой студент: " + juniorStudent);
    }

    public void getOldStudent(){
        StudentDto oldStudent = studentList.get(0);
        for (StudentDto student : studentList){
            if (student.getBirthday().before(oldStudent.getBirthday())){
                oldStudent = student;
            }
        }

        System.out.println("Самый старый студент: " + oldStudent);
    }

    public void getBestStudent(){
        StudentDto bestStudent = studentList.get(0);
        for (StudentDto studentDto: studentList){
            double midMarkStudent = 0D;
            double midMarkBestStudent = 0D;
            for (int i = 0; i < studentDto.getMarkOfSubject().size(); i++){
                midMarkStudent += studentDto.getMarkOfSubject().get(i).getMark();
                midMarkBestStudent += bestStudent.getMarkOfSubject().get(i).getMark();
            }

            if (midMarkStudent > midMarkBestStudent){
                bestStudent = studentDto;
            }
        }

        System.out.println("Самый крутой в этой деревне: " + bestStudent);
    }

    public void printList(){
        System.out.println("Все студенты: ");
        for (StudentDto student: studentList){
            System.out.println(student);
        }
        System.out.println();
    }
}
