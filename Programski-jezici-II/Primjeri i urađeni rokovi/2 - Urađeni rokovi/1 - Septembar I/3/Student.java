import java.util.Objects;

public class Student implements Podatak {

    private String ime;
    private String prezime;
    private String indeks;

    public Student(String ime, String prezime, String indeks) {
        this.ime = ime;
        this.prezime = prezime;
        this.indeks = indeks;
    }

    public String getIme() {
        return this.ime;
    }

    public String getPrezime() {
        return this.prezime;
    }

    public String getIndeks() {
        return this.indeks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ime, this.prezime, this.indeks);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        else if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        else {
            Student student = (Student) object;
            if (this.ime == student.ime && this.prezime == student.prezime && this.indeks == student.indeks) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "Student{ime=" + this.ime + ", prezime=" + this.prezime + ", indeks=" + this.indeks + "}";
    }
}