package net.etfbl.lab4.izuzetak;

import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;

public class PredmetException extends Exception
{
  public PredmetException(String msg)
  {
    super (msg);
  }
  
  public PredmetException()
  {
    System.out.println("Nekorektne dimezije predmeta!!!");
    System.out.println("Dimenzije moraju biti izmedju 1 i 100");
  }
  
}