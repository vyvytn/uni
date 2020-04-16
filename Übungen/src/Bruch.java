import java.math.BigInteger;

public class Bruch implements Comparable<Bruch> {

    private int zaehler;

    private int nenner;

    public Bruch(){
        zaehler=1;
        nenner=1;
    }

    public Bruch(int zaehler, int nenner) {
        if (nenner == 0) {
            throw new IllegalArgumentException("Nenner darf nicht null sein!");
        }
        this.zaehler = zaehler;
        this.nenner = nenner;
    }


    public int getZaehler() {
        return this.zaehler;
    }

    private void setZaehler(int zaehler) {
        this.zaehler = zaehler;
    }

    public int getNenner() {
        return this.nenner;
    }

    private void setNenner(int nenner) {
        this.nenner = nenner;
    }

    @Override
    public int compareTo(Bruch o) {
        if(this.ausrechnen()<o.ausrechnen()){
            return -1;
        }
        if(this.ausrechnen()==o.ausrechnen()){
            return 0;
        }

        return 1;
    }

    public String toString(Bruch b) {
        String text;
        text = "Zaehler ist " + b.getZaehler() + " und Nenner ist " + b.getNenner();
        return text;
    }

    public Bruch multiplizieren(Bruch b) {
        if (b.getNenner() == 0) {
            throw new IllegalArgumentException("Nenner darf nicht null sein");
        }
        int resultZaehler = this.getZaehler() * b.getZaehler();
        int resultNenner = this.getNenner() * b.getNenner();
        return new Bruch(resultZaehler, resultNenner);
    }

    public double ausrechnen() {
        if (this.getNenner() == 0) {
            throw new IllegalArgumentException("Nenner darf nicht null sein.");
        }
        return (double)zaehler/(double)nenner;
    }

    public void kuerzen() {
        int faktor = ggt(this.getZaehler(), this.getNenner());
        int shortenZaehler = this.getZaehler() / faktor;
        int shortenNenner = this.getNenner() / faktor;
        if (nenner < 0) {
            setZaehler(-shortenZaehler);
            setNenner(-shortenNenner);
        } else {
            setZaehler(shortenZaehler);
            setNenner(shortenNenner);
        }
    }

    private int ggt(int a, int b) {
        int h;
        while (b != 0) {
            h = a % b;
            a = b;
            b = h;
        }
        return a;
    }

    public Bruch kehrwert(){
        return new Bruch(this.getNenner(), this.getZaehler());
    }

    public Bruch dividieren(Bruch b){
        int newZaehler=this.getZaehler()*b.kehrwert().getZaehler();
        int newNenner=this.getNenner()*b.kehrwert().getNenner();
        return new Bruch(newZaehler, newNenner);
    }
}
