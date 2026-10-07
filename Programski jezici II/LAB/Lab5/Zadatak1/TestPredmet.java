public class TestPredmet {
    
    public static void main(String args[]){
        Sfera sfera[] = {new Sfera(10), new Sfera(20)};
        Kvadar kvadar[] = {new Kvadar(30), new Kvadar(40)};

        for(int i=0; i < sfera.length;){
            try{
                System.out.println("Sfera redni broj: " + (i+1));
                sfera[i].read();
                sfera[i].print();
                System.out.println("Zapremina: " + sfera[i].zapremina());
                System.out.println("Tezina: " + sfera[i].tezina());
                i++;
            }catch(PredmetException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("************************************");
        }

        for(int i=0; i < kvadar.length;){
            try{
                System.out.println("Kvadar redni broj: " + (i+1));
                kvadar[i].read();
                kvadar[i].print();
                System.out.println("Zapremina: " + kvadar[i].zapremina());
                System.out.println("Tezina: " + kvadar[i].tezina());
                i++;
            }catch(PredmetException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("************************************");
        }

        System.out.println("Poredjenje Sfera: ");
        int rez = Predmet.poredjenje(sfera[0], sfera[1]);
        if(rez == 1){
            System.out.println("Sfera 1 je veca.");
        }else if(rez == -1){
            System.out.println("Sfera 2 je veca.");
        }else{
            System.out.println("Sfere su jednake.");
        }


        System.out.println("Poredjenje Kvadra: ");
        rez = Predmet.poredjenje(kvadar[0], kvadar[1]);
        if(rez == 1){
            System.out.println("Kvadar 1 je veca.");
        }else if(rez == -1){
            System.out.println("Kvadar 2 je veca.");
        }else{
            System.out.println("Kvadri su jednake.");
        }

        System.out.println("************************************");

        System.out.println("Podaci u unesenim sferama:");
        for(int i=0; i< sfera.length ; i++){
            System.out.println(sfera[i].toString());
            System.out.println("************************************");
        }

        System.out.println("Podaci u unesenim kvadrima:");
        for(int i=0; i< kvadar.length ; i++){
            System.out.println(kvadar[i].toString());
            System.out.println("************************************");
        }
    }
}
