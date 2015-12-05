package minhhai2209.webdriverwrapper.helper;

import java.util.Arrays;

public class ArrayHelper {

  public static boolean theSame(byte[][] byteArrays) {
    boolean theSame = true;
    for (int i = 0; (i < byteArrays.length - 1) && theSame; i++) {
      byte[] left = byteArrays[i];
      byte[] right = byteArrays[i + 1];
      theSame = theSame && left != null && Arrays.equals(left, right);
    }
    return theSame;
  }

  public static int nextIndex(int i, int number) {
    i++;
    if (i == number) {
      i = 0;
    }
    return i;
  }
}
