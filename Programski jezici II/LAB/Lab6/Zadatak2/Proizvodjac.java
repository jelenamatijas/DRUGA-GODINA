public class Proizvodjac {
    String naziv, drzava, email;
    Status statusPreduzeca;

    Proizvodjac(){
        super();
        statusPreduzeca = Status.AKTIVAN;
    }

    Proizvodjac(String naziv, String drzava, String email, Status statusPreduzeca){
        this.naziv = naziv;
        this.drzava = drzava;
        this.email = email;
        this.statusPreduzeca = statusPreduzeca;
    }

    Proizvodjac(Proizvodjac p){
        this.naziv = p.naziv;
        this.drzava = p.drzava;
        this.email = p.email;
        this.statusPreduzeca = p.statusPreduzeca;
    }

    public String getNaziv(){
        return naziv;
    }

    public String getDrzava(){
        return drzava;
    }

    public String getEmail(){
        return email;
    }

    public Status getStatusPreduzeca(){
        return statusPreduzeca;
    }

    public void setStatus(Status s){
        statusPreduzeca = s;
    }

    @Override
    public String toString(){
        return "Naziv: " + naziv + " Email: " + email + " Drzava: " + drzava + " Status preduzeca: " + statusPreduzeca;
    }
}
