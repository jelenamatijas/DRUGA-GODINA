/*Koje klase ce biti uspjesno kompajlirane*/

public class T1{
  public abstract T1 metoda();
}

abstract class T2{
  public void T2() {}
}

abstract class T3{
  public void metoda();
}

private class T4{
  private final void metoda(){};
}

final class T5{
  final void metoda(){};
  void metoda2() {}
}

abstract class T6{
  public final void metoda(){};
}

interface TI{
  private void metoda();
}

interface TI2{
  interface TI3{
    void metoda();
  }
}

class T7{
  public void T7(){};
}

interface TI4 implements TI2{
  void metoda();
}