package net.etfbl.pj2;

/**
 *
 * @author igor
 */
public class Prepreka {
    
    public static final String VATRA = "vatra";
    public static final String VODA = "voda";
    public static final String STIJENA = "stijena";
    
    private String vrsta;

    public Prepreka(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return "Prepreka{" + "vrsta=" + vrsta + '}';
    }   
    
}
