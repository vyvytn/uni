package bankprojekt.verarbeitung;
public enum Waehrung {
    EUR(1), BGN(1.95583), LTL(3.4528), KM(1.95583);
    private final double  kurs;

    private Waehrung( double kurs){
        this.kurs=kurs;
    }

    public double euroInWaehrungUmrechnen (double betrag) {
        double summe=1;
        summe = this.kurs*betrag;
        return summe;
    }

    public double waehrungInEuroUmrechnen(double betrag) {
        double euroSumme=1;
        return euroSumme=betrag/this.kurs;
    }

}
