package pismeni.R_2021_06_16.Z01;

public class NosacAviona extends Plovilo {
    public Torpedo torpedo;
    public Raketa raketa;
    public boolean imaRaketniStit;
    public boolean imaStitTorpedo;

    public NosacAviona(String id, int x, int y, boolean imaRaketniStit, boolean imaStitTorpedo) {
        super(id, x, y);
        this.imaRaketniStit = imaRaketniStit;
        this.imaStitTorpedo = imaStitTorpedo;
    }
}