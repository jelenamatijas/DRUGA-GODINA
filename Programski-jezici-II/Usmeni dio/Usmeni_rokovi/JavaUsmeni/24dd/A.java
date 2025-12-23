public class A{
public static void main(String args[]){
int i = 1, j = 0;
switch(i){
case 2: j += 6;
case 4: j += 1;
case 1: j += 10;
default: {j += 2;}
case 0: j += 4;
case 3: j += 3;
}
System.out.println("j = " + j);
} }