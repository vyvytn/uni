package bankprojekt.verarbeitung;
public enum Waehrung {
    EUR(1), BGN(1.95583), KM(1.95583), LTL(3.4528);

    private Waehrung(double zahl) {
        kurs = zahl;
    }

    private double kurs;

    public double euroInWaehrungUmrechnen(double betrag) {
        return betrag * kurs;
    }

    public double betragInEuroUmrechnen(double betrag) {
        return (betrag / Waehrung.EUR.kurs);
    }
}
