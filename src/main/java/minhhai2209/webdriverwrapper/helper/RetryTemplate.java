package minhhai2209.webdriverwrapper.helper;

public class RetryTemplate {

  public static <T> T retry(Function<T> function) {
    return retry(0, function);
  }

  private static <T> T retry(long timeout, Function<T> function) {
    long start = System.currentTimeMillis();
    while (true) {
      T result = function.apply();
      if (result != null) {
        return result;
      }
      if (timeout > 0) {
        long end = System.currentTimeMillis();
        long duration = end - start;
        if (duration > timeout) {
          return null;
        }
      }
    }
  }
}
