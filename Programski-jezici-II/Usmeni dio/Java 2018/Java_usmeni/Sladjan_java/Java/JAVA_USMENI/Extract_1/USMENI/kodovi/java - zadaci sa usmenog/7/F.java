class F extends Thread{
 private String naziv="";
 
 F(String s){
  naziv=s;
 }
 
 public void run(){
  for(int i=0; i<2; i++){
   try{
    sleep(1000); //spava nit, ta jedna, jer nema konkurentnog-nema start()
   } catch(InterruptedException e){
   }
   yield(); //pusti drugu nit, ali posto je nema, ona opet dobija proesor
   System.out.println((i+1)+". "+naziv);
  }
 }
}