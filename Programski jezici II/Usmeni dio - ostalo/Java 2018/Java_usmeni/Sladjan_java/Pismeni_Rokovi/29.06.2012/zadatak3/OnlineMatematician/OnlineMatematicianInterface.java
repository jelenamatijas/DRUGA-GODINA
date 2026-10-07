import java.rmi.*;
import java.util.Scanner;
import java.lang.String;
import java.lang.Math;

interface OnlineMatematicianInterface extends Remote
{
  
  String volume(Figure f) throws RemoteException;
  String surface (Figure f) throws RemoteException;
  
  
  
  
}