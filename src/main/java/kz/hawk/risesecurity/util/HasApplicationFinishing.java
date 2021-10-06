package kz.hawk.risesecurity.util;

public interface HasApplicationFinishing {

  void onApplicationFinishing() throws Exception;

  default double stoppingOrderIndex() {
    return 10.0;
  }

}
