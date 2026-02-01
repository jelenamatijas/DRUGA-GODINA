public class Matrix {
   /* Konstruktor
      n - broj redova matrice
	  	m - broj kolona matrice*/
  public Matrix(int n, int m) {...}
  
  /* Postavlja sadrzaj i dimenzije matrice. */
  public void setData(double[][] x) {...}

  /* Vraca sadrzaj matrice. */
  public double[][] getData() {...}

  /* Mnozi sadrzaj matrice objekta koji je pozvan (this) sa
     sadrzajem matrice b (objekta koji je prosljedjen kao
     parametar). Rezultat mnozenja smjesta u novi objekat koga
     vraca kao rezultat metode. */
  public Matrix multiply(Matrix b) {...}

  /* Mnozi sadrzaj dvije date matrice i rezultat mnozenja
     vraca kao rezultat metode. Obratiti pažnju da je ovo
     staticka metoda! */
  public static Matrix multiply(Matrix a, Matrix b){...}
  
  /* Mnozi sadrzaj matrice objekta koji je pozvan (this) sa
     sadrzajem matrice b (objekta koji je prosljedjen kao
     parametar). Rezultat mnozenja se smjesta u matricu objekta
     koji je pozvan. Metoda ne vraca nikakav rezultat! */
  public void multiply2(Matrix b) {...}

  /* Vraca string reprezentaciju objekta. */
  public String toString() {...}

  /* Polazna tacka programa. Sluzi za testiranje funkcionalnosti
     ostalih metoda klase. Za potrebe testiranja formirati nekoliko
     visedimenzionalnih nizova. */
  public static void main(String[] args) {...}

  /* Sadrzaj matrice */
  private double[][] data;
  /* Dimenzije matrice */
  private int n, m;
}

