package com.example.projectjavasimba.OtherTasks.classes.six;

import java.util.List;

public class Studen {

    private Long id;
    private String firstName;
    private String lastName;
    private Faculty faculty;
    private List<Integer> score;

    public Studen(Long id, String firstName, String lastName, Faculty faculty, List<Integer> score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.score = score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setScore(List<Integer> score) {
        this.score = score;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public List<Integer> getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Studen{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", faculty=" + faculty +
                ", score=" + score +
                '}';
    }
}
