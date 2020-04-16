import bankprojekt.verarbeitung.*;
import java.time.LocalDate;

/**
 * Testprogramm für Konten
 * @author Doro
 *
 */
public class Kontentest {

	/**
	 * Testprogramm für Konten
	 * @param args wird nicht benutzt
	 * @throws GesperrtException
	 */
	public static void main(String[] args) throws GesperrtException {
		int a = 100;
		int b = a;
		a += 50;
		System.out.println(b);  // 100
		
		
		Kunde ich = new Kunde("Dorothea", "Hubrich", "zuhause", LocalDate.parse("1976-07-13"));

		Girokonto meinGiro = new Girokonto(ich, 1234, 1000.0);
		Girokonto bGiro = meinGiro;
		meinGiro.einzahlen(50);
		System.out.println(bGiro.getKontostand());  //50
		
		//System.out.println(meinGiro);
		meinGiro.kontoAusgeben();
		
		Konto gecastet = (Konto) meinGiro;
		System.out.println("Jetzt nur den Konto-Teil ausgeben: - geht einfach nicht!");
		//System.out.println(gecastet);
		gecastet.kontoAusgeben();
		
		System.out.println(gecastet.abheben(10));
		
		
		Sparbuch meinSpar = new Sparbuch(ich, 9876);
		meinSpar.einzahlen(50);
		try
		{
			boolean hatGeklappt = meinSpar.abheben(70);
			System.out.println("Abhebung hat geklappt: " + hatGeklappt);
			System.out.println(meinSpar);
		}
		catch (GesperrtException e)
		{
			System.out.println("Zugriff auf gesperrtes Konto - Polizei rufen!");
		}
		
		Kontoart k;
		//k = new Kontoart();
		k = Kontoart.FESTGELDKONTO;
		k = Kontoart.valueOf("GIROKONTO");
		System.out.println(k.name() + " " + k.ordinal());
		Kontoart[] alle = Kontoart.values();
		for(int i =0; i<alle.length; i++)
			System.out.println(alle[i] + alle[i].getWerbebotschaft());
		
	}

}
