import java.util.*;
import java.util.function.Predicate;

interface AkcijaInterface extends Comparable<AkcijaInterface>{
	public void runAction();
	public String getOpis();
	public int getPrioritet();
	public boolean check(List<Predicate<AkcijaInterface>> pp);
}