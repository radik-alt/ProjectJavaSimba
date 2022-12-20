package com.example.projectjavasimba.collections.TaskCollection.dto;


import java.util.Calendar;
import java.util.List;

public class StudentDto implements Comparable <StudentDto>{

    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Calendar birthday;
    private Integer course;
    private Group numberGroup;
    private List<MarkSubject> markOfMarkSubject;

    public StudentDto(Long id, String lastName, String firstName, String patronymic, Calendar birthday, Integer course, Group numberGroup, List<MarkSubject> markOfMarkSubject) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.course = course;
        this.numberGroup = numberGroup;
        this.markOfMarkSubject = markOfMarkSubject;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public void setNumberGroup(Group numberGroup) {
        this.numberGroup = numberGroup;
    }

    public void setMarkOfSubject(List<MarkSubject> markOfMarkSubject) {
        this.markOfMarkSubject = markOfMarkSubject;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public Integer getCourse() {
        return course;
    }

    public Group getNumberGroup() {
        return numberGroup;
    }

    public List<MarkSubject> getMarkOfSubject() {
        return markOfMarkSubject;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday.getTime() +
                ", course=" + course +
                ", numberGroup=" + numberGroup +
                ", markOfMarkSubject=" + markOfMarkSubject +
                '}';
    }

    @Override
    public int compareTo(StudentDto student) {
        int result = this.firstName.compareTo(student.firstName);

        if (result == 0) {
            result = this.lastName.compareTo(student.lastName);
        }
        return result;
    }
}
