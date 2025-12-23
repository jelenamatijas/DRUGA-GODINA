package model;

public enum DataTypeEnum {

    NARUDZBENICA("Narudzbenica"),
    NARUDZBENICA_STAVKA("NarudzbenicaStavka"),
    FAKTURA("Faktura"),
    FAKTURA_STAVKA("FakturaStavka");
    
    private String message;
    DataTypeEnum(String name) {
        this.message = name;
    }

    @Override
    public String toString() {
        return this.message;
    }
}