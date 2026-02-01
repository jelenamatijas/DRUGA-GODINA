public class W implements O, P
{
    public int W()
    {
      return 4;
    }
    public static void main(String [] args)
    {
	String s = new String("xuz");
	System.out.println("" + "xuz".hashCode() + " " + s.hashCode());
    }
}

interface O
{
  int BRANE = 5;
}

interface P
{
  int BRANE = 6;
}
