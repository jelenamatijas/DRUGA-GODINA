import java.rmi.*;


public interface TelefonskiImenik extends Remote
{
  String[] pregledSadrzaja() throws RemoteException;
  String pretrazivanjePoBrojuTelefona(String s) throws RemoteException;
  String[] prikazSortiranPoPrezimenu() throws RemoteException;
  void dodavanjeUImenik(String s) throws RemoteException;
}