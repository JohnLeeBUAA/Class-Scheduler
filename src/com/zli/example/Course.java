package com.zli.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
  private String dept;
  private int number;
  private String title;
  private String day;
  private List<Integer> days;
  private String room;
  private Date startTime;
  private Date endTime;
  private String teacher;

  public Course() {
    days = new ArrayList<>();
  }

  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
    String tempDay = day;
    if (tempDay.contains("Th")) {
      days.add(3);
      tempDay = tempDay.replace("Th", "");
    }
    if (tempDay.contains("M")) {
      days.add(0);
      tempDay = tempDay.replace("M", "");
    }
    if (tempDay.contains("T")) {
      days.add(1);
      tempDay = tempDay.replace("T", "");
    }
    if (tempDay.contains("W")) {
      days.add(2);
      tempDay = tempDay.replace("W", "");
    }
    if (tempDay.contains("F")) {
      days.add(4);
      tempDay = tempDay.replace("F", "");
    }
  }

  public List<Integer> getDays() {
    return days;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public Date getStartTime() {
    return startTime;
  }

  public String getStartTimeString() {
    return Time.dateToString(startTime);
  }

  public void setStartTime(String startTime) {
    this.startTime = Time.stringToDate(startTime);
  }

  public Date getEndTime() {
    return endTime;
  }

  public String getEndTimeString() {
    return Time.dateToString(endTime);
  }

  public void setEndTime(String endTime) {
    this.endTime = Time.stringToDate(endTime);
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  @Override
  public String toString() {
    return dept + " " + Integer.toString(number) + Value.HTML_BR + title + Value.HTML_BR + day + " "
        + getStartTimeString() + " - " + getEndTimeString() + Value.HTML_BR + room + Value.HTML_BR
        + teacher;
  }
}
