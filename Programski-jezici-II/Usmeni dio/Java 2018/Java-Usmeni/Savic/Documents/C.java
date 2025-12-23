import java.lang.instrument.Instrumentation;

public class C {
    private int x;
    private int y;

    public static void main(String [] args) {
        System.out.println(ObjectSizeFetcher.getObjectSize(new C()));
    }
}
class ObjectSizeFetcher {
    private static Instrumentation instrumentation = new Instrumentation();

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
