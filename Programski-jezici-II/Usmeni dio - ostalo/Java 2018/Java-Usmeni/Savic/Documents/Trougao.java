public class Trougao
{
  public Trougao(int x, int y)
  {
      this.x = x;
      this.y = y;
      matrix = new char[x][];
      for(int i = 0; i < x; i++)
      {
	matrix[i] = new char[i+1];
	for(int j = 0; j < matrix[i].length; j++)
	  matrix[i][j] = '*';
      }
  }
  private int x,y;
  private char matrix[][];
  public String toString()
  {
      String result = "";
      for(int i = 0; i < matrix.length; i++)
	{
	  for(int j = 0; j < matrix[i].length; j++)
	    result += matrix[i][j];
	  result += "\n";
	}
      return result;
  }
  public static void main(String [] args)
  {
      Trougao t1 = new Trougao(3, 4);
      Trougao t2 = new Trougao(6, 8);
      System.out.println(t1);
      System.out.println(t2);
  }
}