package com.acme.basic;

public class HelloWorld {

  void sayHello() {
    System.out.println("Hello World!");
  }

  void notCovered() {
    System.out.println("This method is not covered by unit tests");
  }
  
  private static byte char64(char x) {
    if ((int)x < 0 || (int)x > index_64.length)
      return -1;
    return index_64[(int)x];
    }


  public static void main(String[ ] args) {
    try {
      int[] myNumbers = {1, 2, 3};
      System.out.println(myNumbers[10]);
    } catch (Exception e) {
      //System.out.println("Something went wrong.");
    }
  }
}
