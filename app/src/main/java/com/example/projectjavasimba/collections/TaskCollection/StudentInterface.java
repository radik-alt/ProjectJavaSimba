package com.example.projectjavasimba.collections.TaskCollection;

import TaskCollection.dto.Group;
import TaskCollection.dto.StudentDto;

public interface StudentInterface {

    void addStudent(StudentDto student);
    void averageScoreOfGroup(Group group);
    void getJuniorStudent();
    void getOldStudent();
}
