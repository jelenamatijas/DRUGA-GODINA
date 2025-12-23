class Pokusaj
{
    static int i;
    public static void main()
    {
    
    }
    public void main(int x)
    {
    
    }
    public static void main(String [] args)	
    {
	B b = new B();
	B.main(args);
	System.out.println(i);
	if (i++>3) return;
	main(args);
    }
}

class B
{
    public static void main(String [] args)
    {
    
    }
}