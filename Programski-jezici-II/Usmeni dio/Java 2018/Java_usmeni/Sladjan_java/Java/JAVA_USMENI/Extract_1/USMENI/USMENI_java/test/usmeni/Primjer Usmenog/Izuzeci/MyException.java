class MyException extends Exception
{
  MyException()
  {
    System.out.println("Greska...");
  }
}

class E extends MyException
{}