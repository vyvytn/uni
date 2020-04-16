package bankprojekt.verarbeitung;

import bankprojekt.verarbeitung.*;

import java.util.*;

public class Bank {
    /**
     * Bankleitzahl der Bank
     */
    private long bankleitzahl;

    private long memberKontonummer = 0;
    Map<Long, Konto> KontoMap = new HashMap<>();

    public Bank(long blz) {
        this.bankleitzahl = blz;
    }

    public long getBankleitzahl() {
        return bankleitzahl;
    }

    public long girokontoErstellen(Kunde inhaber) {
        if (inhaber == null) {
            throw new IllegalArgumentException("Bitte Kunde eintragen!");
        }
        Girokonto giroNeu;
        giroNeu = new Girokonto(inhaber, memberKontonummer, 500);
        KontoMap.put(giroNeu.getKontonummer(), giroNeu);
        memberKontonummer += 1;
        return giroNeu.getKontonummer();
    }

    public long sparbuchErstellen(Kunde inhaber) {
        if (inhaber == null) {
            throw new IllegalArgumentException("Bitte Kunde eintragen!");
        }
        Sparbuch sparNeu;
        sparNeu = new Sparbuch();
        KontoMap.put(sparNeu.getKontonummer(), sparNeu);
        return sparNeu.getKontonummer();
    }

    public String getAlleKonten() {
        String listOfNumbers = "";
        for (long k : KontoMap.keySet()) {
            listOfNumbers += "Name: " + KontoMap.get(k).getInhaber() + "Kontonummer: " + KontoMap.get(k).getKontonummer() + "Kontostand: " + KontoMap.get(k).getKontostand();
        }
        return listOfNumbers;
    }

    public List<Long> getAlleKontonummern() {
        List<Long> listOfNumbers = new ArrayList<>();
        for (long k : KontoMap.keySet()) {
            listOfNumbers.add(KontoMap.get(k).getKontonummer());
        }
        return listOfNumbers;
    }

    public boolean geldAbheben(long von, double betrag) {
        if (betrag < 0) {
            throw new IllegalArgumentException("Betrag muss ?ber null sein!");
        }
        if (KontoMap.get(von) == null) {
            throw new IllegalArgumentException("Konto existiert nicht");
        }
        try {
            return KontoMap.get(von).abheben(betrag);

        } catch (GesperrtException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void geldEinzahlen(long auf, double betrag) {
        if (betrag < 0) {
            throw new IllegalArgumentException("Betrag muss ?ber null sein!");
        }
        if (KontoMap.get(auf) == null) {
            throw new IllegalArgumentException("Konto existiert nicht");
        }
        KontoMap.get(auf).einzahlen(betrag);
    }

    public boolean kontoLoeschen(long kontonummer) {
        if (KontoMap.get(kontonummer) == null) {
            return false;
        }
        KontoMap.remove(kontonummer);
        return true;
    }

    public double getKontostand(long nummer){
        if (KontoMap.get(nummer) == null) {
            throw new IllegalArgumentException("Konto exisitert nicht!");
        }
        return KontoMap.get(nummer).getKontostand();
    }

    public boolean geldUeberweisen (long von, long auf, double betrag, String verwendungsZweck){
        if (KontoMap.get(von) == null || KontoMap.get(auf)==null) {
            throw new IllegalArgumentException("Konto exisitert nicht!");
        }
        if(betrag<0){
            throw new IllegalArgumentException("Nur positive Betr?ge ?berweisen!");
        }
        if(verwendungsZweck==null){
            throw new NullPointerException("Kein Verwendungszweck angegeben!");
        }
        if(!(KontoMap.get(von) instanceof Girokonto)|| !(KontoMap.get(auf) instanceof Girokonto)){
            return false;
        }
        try {
            if (KontoMap.get(von).abheben(betrag)){
                KontoMap.get(auf).einzahlen(betrag);
                return true;
            }
            return false;

        } catch (GesperrtException e) {
            e.printStackTrace();
        }
        return false;
    }
}
