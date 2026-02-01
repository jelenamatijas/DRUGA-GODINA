class Test {
	public static void main(String []args) {
		new MyThread("nit");
	}
}
class MyThread extends Thread {
	public MyThread(String name) {
//		this.setName(name);
		start();
		System.out.println("MyThread " + getName());
	}
	public void start() {
		System.out.println("start " + getName());
	}
	public void run() {
		System.out.println("run " + getName());
}}