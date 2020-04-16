package bankprojekt.verarbeitung;

/**
 * Eine Sammlung aller angebotenen Kontoarten
 * 
 * @author Dorothea Hubrich
 *
 */
public enum Kontoart {
	/**
	 * steht f?r ein Girokonto
	 */
	GIROKONTO("voll gro?er Dispo"),
	/**
	 * ein Sparbuch
	 */
	SPARBUCH("viele Zinsen"),
	/**
	 * ein Festgeldkonto, was wir hier aber gar nicht haben...
	 */
	FESTGELDKONTO("gibts eh nich");
	
	private String werbebotschaft;
	
	Kontoart(String werbung)
	{
		this.werbebotschaft = werbung;
	}
	
	public String getWerbebotschaft()
	{
		return this.werbebotschaft;
	}
}
