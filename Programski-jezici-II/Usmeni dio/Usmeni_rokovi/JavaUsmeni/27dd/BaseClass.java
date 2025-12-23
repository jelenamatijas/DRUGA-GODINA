class BaseClass {
private void foo(){ System.out.println("In BaseClass.foo()"); }
void bar(){ System.out.println("In BaseClass.bar()"); }
public static void main(String[] args) {
DerivedClass po = new DerivedClass();
((BaseClass)po).foo();
((BaseClass)po).bar();
}
}
class DerivedClass extends BaseClass {
void foo(){ System.out.println("In Derived.foo()"); }
void bar(){ System.out.println("In Derived.bar()"); }
}