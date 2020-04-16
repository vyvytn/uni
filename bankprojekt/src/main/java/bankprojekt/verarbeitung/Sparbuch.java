package bankprojekt.verarbeitung;

import javax.swing.plaf.BorderUIResource;
import java.time.LocalDate;

/**
 * ein Sparbuch
 * @author Doro
 *
 */
public class Sparbuch extends Konto {
	/**
	 * Zinssatz, mit dem das Sparbuch verzinst wird. 0,03 entspricht 3%
	 */
	private double zinssatz;
	
	/**
	 * Monatlich erlaubter Gesamtbetrag f?r Abhebungen
	 */
	public static final double ABHEBESUMME = 2000;
	
	/**
	 * Betrag, der im aktuellen Monat bereits abgehoben wurde
	 */
	private double bereitsAbgehoben = 0;
	/**
	 * Monat und Jahr der letzten Abhebung
	 */
	private LocalDate zeitpunkt = LocalDate.now();
	
	public Sparbuch() {
		zinssatz = 0.03;
	}

	@Override
	public void waehrungsWechsel(Waehrung alt, Waehrung neu) {
		double temp=neu.euroInWaehrungUmrechnen(alt.betragInEuroUmrechnen(bereitsAbgehoben));
		bereitsAbgehoben=temp;
		super.waehrungsWechsel(alt, neu);
	}

	public Sparbuch(Kunde inhaber, long kontonummer) {
		super(inhaber, kontonummer);
		zinssatz = 0.03;
	}
	
	@Override
	public String toString()
	{
    	String ausgabe = "-- SPARBUCH --" + System.lineSeparator() +
    	super.toString()
    	+ "Zinssatz: " + this.zinssatz * 100 +"%" + System.lineSeparator();
    	return ausgabe;
	}

	@Override
	public boolean abheben (double betrag) throws GesperrtException {
		double tempBetrag= getAktuelleWaehrung().euroInWaehrungUmrechnen(betrag);
		betrag= tempBetrag;
		if (betrag < 0 || Double.isNaN(betrag)) {
			throw new IllegalArgumentException("Betrag ung?ltig");
		}
		if(this.isGesperrt())
		{
			GesperrtException e = new GesperrtException(this.getKontonummer());
			throw e;
		}
		LocalDate heute = LocalDate.now();
		if(heute.getMonth() != zeitpunkt.getMonth() || heute.getYear() != zeitpunkt.getYear())
		{
			this.bereitsAbgehoben = 0;
		}
		if (getKontostand() - betrag >= 0.50 && 
				 bereitsAbgehoben + betrag <= Sparbuch.ABHEBESUMME)
		{
			setKontostand(getKontostand() - betrag);
			bereitsAbgehoben += betrag;
			this.zeitpunkt = LocalDate.now();
			return true;
		}
		else
			return false;
	}

}
