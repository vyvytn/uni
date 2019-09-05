public class Bruch implements Comparable<Bruch> {
    private int nenner;
    private int zaehler;

    Bruch(int nenn, int zaehl) throws ArithmeticException {
        if (nenner != 0) {
            this.nenner = nenn;
            this.zaehler = zaehl;
        } else {
            System.out.println("Nenner ist Null.");
        }

    }

    @Override
    public int compareTo(Bruch o) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNenner() {
        return nenner;
    }

    public int getZaehler() {
        return zaehler;
    }

    public void setNenner(int nenner) {
        this.nenner = nenner;
    }

    public void setZaehler(int zaehler) {
        this.zaehler = zaehler;
    }

    public String toString() {
        return getZaehler() + "/" + getNenner();
    }

    public Bruch multiplizieren(Bruch b) throws ArithmeticException {
        Bruch ergebnis = null;
        if (this.getNenner() != 0 && b.getNenner() != 0) {
            ergebnis.setNenner(this.getNenner() * b.getNenner());
            ergebnis.setZaehler(this.getZaehler() * b.getZaehler());
        } else {
            System.out.println("Nenner ist 0");
        }
        return ergebnis;
    }

   /* public void ausrechnen() throws ArithmeticException {
        if (this.getNenner() == 0) {
            System.out.println("Nenner ist null");

        }
        return double fließzahl= (double)(this.getZaehler() / this.getNenner());

    }*/


    public void kuerzen() throws ArithmeticException {
        if (this.getNenner() != 0) {

        }
    }

/*    public Bruch kehrwert() throws ArithmeticException {
        if (this.getNenner() == 0) {
            System.out.println("Nenner ist null.");
        }
        Bruch b1 = new Bruch;
        return b1=(this.nenner, this.zaehler);
    }*/

    public Bruch dividieren(Bruch b) {
        //return this.multiplizieren(b.kehrwert());
        return new Bruch int b1;
        b1=(1;5);
    }

    public static void main(String[] args) {
        Bruch b1= new Bruch(1, 4);
        Bruch b2= new Bruch(2,4);
        System.out.println(b1.toString());



    }

}