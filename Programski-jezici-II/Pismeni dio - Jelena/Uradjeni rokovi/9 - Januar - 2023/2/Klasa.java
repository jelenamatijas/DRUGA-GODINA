import java.util.Objects;

public class Klasa {
public String ime;
protected int broj;
public void testMetoda(String ime){}

public Klasa(){}
public Klasa(int broj){
this.broj = broj;
}
public int getBroj(){ return broj; }
public void setBroj(int broj){ this.broj = broj; }
@Override
public String toString(){
return "Klasa [broj=" + broj + "]";
}
@Override
public boolean equals(Object o){
if(this == o) return true;
if(o == null || getClass() != o.getClass()) return false;
Klasa that = (Klasa) o;
if(!Objects.equals(broj, that.broj)) return false;
return true;
}
}