package com.example.projectjavasimba.OtherTasks.collections.TaskCollection;


import com.example.projectjavasimba.OtherTasks.collections.TaskCollection.dto.StudentDto;
import com.example.projectjavasimba.OtherTasks.collections.TaskCollection.dto.Group;

public interface StudentInterface {

    void addStudent(StudentDto student);
    void averageScoreOfGroup(Group group);
    void getJuniorStudent();
    void getOldStudent();
}
