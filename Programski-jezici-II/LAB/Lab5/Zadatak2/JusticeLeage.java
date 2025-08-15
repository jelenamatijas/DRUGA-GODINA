import java.util.Random;

public class JusticeLeage {
   
    private static String checkPosition(BadCitizen bad){
        if(bad.getPositionX() < 30){
            return new String("voda");
        }else if(bad.getPositionX() >= 30 && bad.getPositionX() < 60){
            return new String("zemlja");
        }else {
            return new String("vazduh");
        }
    }
    public static void main(String args[]){
        boolean isThereBadGuys = false;
        Citizen supercity[][] = new Citizen[90][30];

        System.out.println("Postavljanje zlikovaca: ");
        BadCitizen badGuys[] = new BadCitizen[6];
        for(int i = 0; i < 6; i++){
            badGuys[i] = new BadCitizen();
            badGuys[i].setName("Bad citizen number " + (i+1));
            supercity[badGuys[i].getPositionX()][badGuys[i].getPositionY()] = badGuys[i];
            System.out.println(badGuys[i]);
        }

        GoodCitizen goodGuys[] = {new WonderWoman(), new Superman(), new SuperGirl(), new Aquaman(), new Batman(), new GreenArrow()};

        Random rand = new Random();


        for(int i=0; i< 90; i++){
            for(int j=0; j<30;j++){
                if(supercity[i][j] instanceof BadCitizen){
                    String place = JusticeLeage.checkPosition((BadCitizen)supercity[i][j]);
                    if(place.equals("voda")){
                        System.out.println("Zlikovac: " 
                                            + supercity[i][j].getName()
                                            + " je unisten! Superheroj "
                                            + goodGuys[3].getName());
                        supercity[i][j] = goodGuys[3];
                    }else if(place.equals("zemlja")){
                        boolean sl = rand.nextBoolean();
                        if(sl){
                            System.out.println("Zlikovac: " 
                                            + supercity[i][j].getName()
                                            + " je unisten! Superheroj "
                                            + goodGuys[5].getName());
                            supercity[i][j] = goodGuys[5];
                        }else{
                            System.out.println("Zlikovac: " 
                                            + supercity[i][j].getName()
                                            + " je unisten! Superheroj "
                                            + goodGuys[4].getName());
                            supercity[i][j] = goodGuys[4];
                        }
                    }else{
                        int sl = rand.nextInt(2);
                        if(sl == 1 && ((BadCitizen)supercity[i][j]).getGreenKriptonyte()){
                            System.out.println("Superman je unisten!");
                            isThereBadGuys = true;
                        }else{
                            System.out.println("Zlikovac: " 
                                            + supercity[i][j].getName()
                                            + " je unisten! Superheroj "
                                            + goodGuys[sl].getName());
                            supercity[i][j] = goodGuys[sl];
                        }
                    }
                }
            }
        }
         if( !isThereBadGuys){
            System.out.println("Justice Leage je opet spasila svijet!");
        }else{
            System.out.println("Zlikovci su pobijedili!");
        }
    }

   
}
