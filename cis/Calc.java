package edu.uab.cis;

import java.util.Scanner;

import edu.uab.cis.calc.Calculator;

public class Calc
{
  /**
   * Generates a new calculator object (factory function)
   */
  public static Calculator calculator(String s)
  {
    return new Calculator(s);
  }

  /**
   * main - test driver
   *
   * Reads an expression from the standard input stream
   * and prints the output to the console
   *
   * @param args unused
   */
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLine())
    {
      Float res = calculator(sc.nextLine()).compute();

      System.out.println(res);
    }
  }
}
