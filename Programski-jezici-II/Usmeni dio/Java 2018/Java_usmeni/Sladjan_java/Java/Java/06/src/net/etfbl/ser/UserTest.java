package net.etfbl.ser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserTest {

 public static void main(String[] args) throws Exception {
//  User user = new User();
 // User2 user = new User2();
//  User3 user = new User3();
 // User4 user = new User4("default");
 // User5 user = new User5();
  User6 user = new User6();
  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.txt"));
  oos.writeObject(user);
  ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.txt"));
//  User user2 = (User) ois.readObject();
//  User2 user2 = (User2) ois.readObject();
//  User3 user2 = (User3) ois.readObject();
//  User4 user2 = (User4) ois.readObject();
//  User5 user2 = (User5) ois.readObject();
  User6 user2 = (User6) ois.readObject();
  System.out.println(user2.getUsername());
  System.out.println(user2.getPassword());
  oos.close();
  ois.close();
 }

}
