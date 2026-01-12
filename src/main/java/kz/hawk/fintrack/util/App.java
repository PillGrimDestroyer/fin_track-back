package kz.hawk.fintrack.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

  public static Path appDir() {
    return Paths.get(System.getProperty("user.home")).resolve(srcName() + ".d");
  }

  public static String srcName() {
    return "fin_track-back";
  }

}
