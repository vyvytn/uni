package bankprojekt.verarbeitung;
/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto implements Comparable<Konto> {
    /**
     * der Kontoinhaber
     */
    private Kunde inhaber;

    /**
     * die Kontonummer
     */
    private final long nummer;

    /**
     * der aktuelle Kontostand
     */
    private double kontostand;

    /**
     * setzt den aktuellen Kontostand
     *
     * @param kontostand neuer Kontostand
     */
    protected void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    /**
     * Wenn das Konto gesperrt ist (gesperrt = true), k?nnen keine Aktionen daran mehr vorgenommen werden,
     * die zum Schaden des Kontoinhabers w?ren (abheben, Inhaberwechsel)
     */
    private boolean gesperrt;

    /**
     * aktuelle Waehrung, in der das Konto gef?hrt wird
     */
    public static Waehrung aktuelleWaehrung=Waehrung.EUR;

    public void setAktuelleWaehrung(Waehrung aktuelleWaehrung) {
        this.aktuelleWaehrung = aktuelleWaehrung;
    }

    public Waehrung getAktuelleWaehrung() {
        return aktuelleWaehrung;
    }

    public void waehrungsWechsel(Waehrung alt, Waehrung neu){
        setKontostand(neu.euroInWaehrungUmrechnen(alt.betragInEuroUmrechnen(getKontostand())));
        setAktuelleWaehrung(neu);
    }
    /**
     * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
     * der anf?ngliche Kontostand wird auf 0 gesetzt.
     *
     * @param inhaber     der Inhaber
     * @param kontonummer die gew?nschte Kontonummer
     * @throws IllegalArgumentException wenn der Inhaber null
     */
    public Konto(Kunde inhaber, long kontonummer) {
        if (inhaber == null)
            throw new IllegalArgumentException("Inhaber darf nicht null sein!");
        this.inhaber = inhaber;
        this.nummer = kontonummer;
        this.kontostand = 0;
        this.gesperrt = false;
    }

    /**
     * setzt alle Eigenschaften des Kontos auf Standardwerte
     */
    public Konto() {
        this(Kunde.MUSTERMANN, 1234567);
    }

    /**
     * liefert den Kontoinhaber zur?ck
     *
     * @return der Inhaber
     */
    public final Kunde getInhaber() {
        return this.inhaber;
    }

    /**
     * setzt den Kontoinhaber
     *
     * @param kinh neuer Kontoinhaber
     * @throws GesperrtException        wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn kinh null ist
     */
    public final void setInhaber(Kunde kinh) throws GesperrtException {
        if (kinh == null)
            throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
        if (this.gesperrt)
            throw new GesperrtException(this.nummer);
        this.inhaber = kinh;

    }

    /**
     * liefert den aktuellen Kontostand
     *
     * @return double
     */
    public final double getKontostand() {
        return kontostand;
    }

    /**
     * liefert die Kontonummer zur?ck
     *
     * @return long
     */
    public final long getKontonummer() {
        return nummer;
    }

    /**
     * liefert zur?ck, ob das Konto gesperrt ist oder nicht
     *
     * @return true, wenn das Konto gesperrt ist
     */
    public final boolean isGesperrt() {
        return gesperrt;
    }

    /**
     * Erh?ht den Kontostand um den eingezahlten Betrag.
     *
     * @param betrag double
     * @throws IllegalArgumentException wenn der betrag negativ ist
     */
    public void einzahlen(double betrag) {
        if (betrag < 0 || Double.isNaN(betrag)) {
            throw new IllegalArgumentException("Falscher Betrag");
        }
        setKontostand(getKontostand() + betrag);
    }

    /**
     * Gibt eine Zeichenkettendarstellung der Kontodaten zur?ck.
     */
    @Override
    public String toString() {
        String ausgabe;
        ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
                + System.getProperty("line.separator");
        ausgabe += "Inhaber: " + this.inhaber;
        ausgabe += "Aktueller Kontostand: " + this.kontostand + " Euro ";
        ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
        return ausgabe;
    }

    /**
     *pr?ft ob betrag in gleicher w?hrung ist
     * hebt das geld ab und aktualisiert konotstand
     */
    public boolean abheben(double betrag, Waehrung w) {
        if (betrag > 0) {
            if (w != this.getAktuelleWaehrung()) {
              setKontostand(w.euroInWaehrungUmrechnen(getAktuelleWaehrung().betragInEuroUmrechnen(betrag)));
            }
            return true;
        }
        return false;
    }

    public void einzahlen(double betrag, Waehrung w){
        if (betrag > 0) {
            if (w != this.getAktuelleWaehrung()) {
                double temp = getAktuelleWaehrung().betragInEuroUmrechnen(betrag);
                betrag = getAktuelleWaehrung().euroInWaehrungUmrechnen(temp);
            }
            setKontostand(getKontostand() + betrag);
        }
    }

    /**
     * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
     *
     * @param betrag double
     * @return true, wenn die Abhebung geklappt hat,
     * false, wenn sie abgelehnt wurde
     * @throws GesperrtException        wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn der betrag negativ ist
     */
    public abstract boolean abheben(double betrag)
            throws GesperrtException;

    /**
     * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr m?glich.
     */
    public final void sperren() {
        this.gesperrt = true;
    }

    /**
     * entsperrt das Konto, alle Kontoaktionen sind wieder m?glich.
     */
    public final void entsperren() {
        this.gesperrt = false;
    }


    /**
     * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
     *
     * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
     */
    public final String getGesperrtText() {
        if (this.gesperrt) {
            return "GESPERRT";
        } else {
            return "";
        }
    }

    /**
     * liefert die ordentlich formatierte Kontonummer
     *
     * @return auf 10 Stellen formatierte Kontonummer
     */
    public String getKontonummerFormatiert() {
        return String.format("%10d", this.nummer);
    }

    /**
     * liefert den ordentlich formatierten Kontostand
     *
     * @return formatierter Kontostand mit 2 Nachkommastellen und W?hrungssymbol ?
     */
    public String getKontostandFormatiert() {
        return String.format("%10.2f Euro", this.getKontostand());
    }

    /**
     * Vergleich von this mit other; Zwei Konten gelten als gleich,
     * wen sie die gleiche Kontonummer haben
     *
     * @param other das Vergleichskonto
     * @return true, wenn beide Konten die gleiche Nummer haben
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        if (this.nummer == ((Konto) other).nummer)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
    }

    public int compareTo(Konto other) {
        if (other.getKontonummer() > this.getKontonummer())
            return -1;
        if (other.getKontonummer() < this.getKontonummer())
            return 1;
        return 0;
    }

    /**
     * diese Methode geh?rt eigentlich nicht in eine
     * Verarbeitungsklasse
     */
    public void kontoAusgeben() {
        System.out.println(this.toString());
    }
}
