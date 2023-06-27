package com.example.projectjavasimba.OtherTasks.collections.TaskCollection.dto;

public class MarkSubject {

    private Subject subject;
    private Double mark;

    public MarkSubject(Subject subject, Double mark) {
        this.subject = subject;
        this.mark = mark;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Subject getSubject() {
        return subject;
    }

    public Double getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "MarkSubject{" +
                "subject=" + subject +
                ", mark=" + mark +
                '}';
    }
}
