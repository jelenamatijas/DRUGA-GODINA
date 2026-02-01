class TestEnum {
static Animals a;
public static void main(String[] args) {
  TestEnum te = new TestEnum();
 System.out.println(te.a.DOG.sound + " " + te.a.FISH.sound);
}
}

enum Animals {
DOG("woof"), CAT("meow"), FISH("burble");
 String sound;
Animals(String s) { sound = s; }
 }
