public class Prepreka {

    public static final String VATRA = "Vatra";
    public static final String VODA = "Voda";
    public static final String STIJENA = "Stijena";

    private String vrstaPrepreke;

    public Prepreka(String vrsta){
        vrstaPrepreke = vrsta;
    }

    public String getVrsta(){
        return vrstaPrepreke;
    }

    public void setVrstaPrepreke(String vrsta){
        vrstaPrepreke = vrsta;
    }

    @Override
    public String toString() {
        return "Prepreka{vrsta=" + vrstaPrepreke + '}';
    }   
    
}
