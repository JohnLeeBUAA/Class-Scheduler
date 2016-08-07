package com.zli.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Scheduler {
  private List<String> divList;
  private List<Course> courseList;
  private Date dayStartTime;

  public Scheduler() {
    divList = new ArrayList<>();
    courseList = new ArrayList<>();
    dayStartTime = Time.stringToDate(Value.DAY_START_TIME);
  }

  public void schedule() {
    initCalendar();
    parseCourse();
    courseToDiv();
    try {
      generateHTMLFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initCalendar() {
    divList.add(Value.getDiv(0, 0, Value.TIME_WIDTH, Value.DAY_HEIGHT, Value.COLOR_WHITE, ""));
    for (int i = 0; i < 5; i++) {
      divList.add(Value.getDiv(0, Value.TIME_WIDTH + i * Value.DAY_WIDTH, Value.DAY_WIDTH,
          Value.DAY_HEIGHT, Value.COLOR_WHITE, Value.DAYS[i]));
    }
    for (int i = 0; i < Value.DAY_HOURS; i++) {
      divList.add(Value.getDiv(Value.DAY_HEIGHT + i * Value.TIME_HEIGHT, 0, Value.TIME_WIDTH,
          Value.TIME_HEIGHT, Value.COLOR_WHITE, Time.dateToString(Time.addHour(dayStartTime, i))));
      for (int j = 0; j < 5; j++) {
        divList.add(Value.getDiv(Value.DAY_HEIGHT + i * Value.TIME_HEIGHT,
            Value.TIME_WIDTH + j * Value.DAY_WIDTH, Value.DAY_WIDTH, Value.TIME_HEIGHT,
            Value.COLOR_WHITE, ""));
      }
    }

  }

  private void parseCourse() {
    Path dir = Paths.get(Value.COURSE_FILE_DIR);
    if (Files.exists(dir)) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(dir.toFile()));
        String line;
        while ((line = br.readLine()) != null) {
          Course course = new Course();
          String[] titleList = line.replaceAll("\\s+", " ").trim().split(" ");
          course.setDept(titleList[0]);
          course.setNumber(Integer.parseInt(titleList[1]));
          String title = "";
          for (int i = 3; i < titleList.length; i++) {
            title += titleList[i] + " ";
          }
          course.setTitle(title.trim());

          line = br.readLine();
          String[] timeList = line.replaceAll("\\s+", " ").trim().split(" ");
          course.setDay(timeList[0]);
          course.setStartTime(timeList[1]);
          course.setEndTime(timeList[3]);

          line = br.readLine();
          course.setRoom(line.replaceAll("\\s+", " ").trim());

          line = br.readLine();
          course.setTeacher(line.replaceAll("\\s+", " ").trim());

          courseList.add(course);
          line = br.readLine();
        }
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      System.err.println("Course File Does Not Exist.");
      System.exit(1);
    }
  }

  private void courseToDiv() {
    for (int i = 0; i < courseList.size(); i++) {
      Course course = courseList.get(i);
      int top = Value.DAY_HEIGHT + Time.substractMinute(dayStartTime, course.getStartTime())
          * Value.TIME_HEIGHT_PER_MINUTE;
      int left = Value.TIME_WIDTH + (course.getNumber() >= 700 ? Value.COURSE_WIDTH : 0);
      int width = Value.COURSE_WIDTH;
      int height = Time.substractMinute(course.getStartTime(), course.getEndTime())
          * Value.TIME_HEIGHT_PER_MINUTE;
      String color = course.getNumber() >= 700 ? Value.COLOR_PINK : Value.COLOR_LIGHT_GREEN;

      for (int j = 0; j < course.getDays().size(); j++) {
        divList.add(Value.getDiv(top, left + course.getDays().get(j) * Value.DAY_WIDTH, width,
            height, color, course.toString()));
      }
    }
  }

  private void generateHTMLFile() throws IOException {
    Path dir = Paths.get(Value.COURSE_HTML_DIR);
    if (Files.exists(dir)) {
      Files.delete(dir);
    }

    File fout = new File(Value.COURSE_HTML_DIR);
    FileOutputStream fos = new FileOutputStream(fout);

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

    bw.write(Value.HTML_START);
    bw.newLine();
    for (int i = 0; i < divList.size(); i++) {
      bw.write(divList.get(i));
      bw.newLine();
    }
    bw.write(Value.HTML_END);
    bw.newLine();

    bw.close();
  }

  public static void main(String[] args) {
    Scheduler scheduler = new Scheduler();
    scheduler.schedule();
  }
}
