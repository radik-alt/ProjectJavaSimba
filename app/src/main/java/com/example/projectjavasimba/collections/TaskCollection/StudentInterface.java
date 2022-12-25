package com.example.projectjavasimba.collections.TaskCollection;


import com.example.projectjavasimba.collections.TaskCollection.dto.Group;
import com.example.projectjavasimba.collections.TaskCollection.dto.StudentDto;

public interface StudentInterface {

    void addStudent(StudentDto student);
    void averageScoreOfGroup(Group group);
    void getJuniorStudent();
    void getOldStudent();
}
