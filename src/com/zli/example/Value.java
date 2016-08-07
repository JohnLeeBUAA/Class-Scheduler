package com.zli.example;

public class Value {
  public static final int DAY_WIDTH = 250;
  public static final int DAY_HEIGHT = 20;
  public static final int TIME_WIDTH = 100;
  public static final int TIME_HEIGHT = 120;
  public static final int TIME_HEIGHT_PER_MINUTE = 2;
  public static final int COURSE_WIDTH = 125;
  public static final int DAY_HOURS = 10;
  public static final int HOUR = 3600 * 1000;
  public static final int MINUTE = 60 * 1000;
  public static final String DAY_START_TIME = "8:00AM";
  public static final String COURSE_FILE_DIR = "./course.txt";
  public static final String COURSE_HTML_DIR = "./course.html";
  public static final String COLOR_WHITE = "white";
  public static final String COLOR_LIGHT_GREEN = "lightgreen";
  public static final String COLOR_PINK = "pink";
  public static final String HTML_START =
      "<!DOCTYPE html>\n<html>\n<head>\n<title>Class Schedule</title>\n</head>\n<body>";
  public static final String HTML_END = "</body>\n</html>";
  public static final String HTML_BR = "<br>";

  public static String getDiv(int top, int left, int width, int height, String color, String text) {
    return "<div style=\"position: absolute; top: " + Integer.toString(top) + "px; left: "
        + Integer.toString(left) + "px; width: " + Integer.toString(width) + "px; height: "
        + Integer.toString(height) + "px; border:1px solid black; background-color: " + color
        + ";\">" + text + "</div>";
  }

  public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

  private Value() {}
}
