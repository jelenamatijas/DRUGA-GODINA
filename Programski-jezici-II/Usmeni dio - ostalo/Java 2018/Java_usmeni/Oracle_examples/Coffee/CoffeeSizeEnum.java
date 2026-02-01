enum CoffeeSizeEnum {
  BIG(8),
  HUGE(10),
  OVERWHELMING(16) {   // start a code block that defines the "body" for this constant
   public String getLidCode() {  // override the method defined in CoffeeSize
   return "A";
   }
  };     // the semicolon (";") is REQUIRED when more code follows
  
  CoffeeSizeEnum(int ounces) {
   this.ounces = ounces;
  }

  private int ounces;
  public int getOunces() {
   return ounces;
  }
  public String getLidCode() {
   return "B";   // the default value we want to return for CoffeeSize constants   
  }
 }
