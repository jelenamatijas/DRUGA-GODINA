package net.etfbl.client.interfejsi;
import java.io.*;
import java.net.*;
import java.util.*;

public interface UpravljaIzvjestajima {
  
  public void makeReport();
  
  public void showDirectory() throws Exception;
  
  public void showReport(String name) throws Exception;
  
}