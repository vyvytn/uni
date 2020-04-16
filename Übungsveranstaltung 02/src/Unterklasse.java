
public class Unterklasse extends Oberklasse {
	private int x;
	
	public Unterklasse()
	{
		this.x = 11;
	}
	
	public void methode()
	{
		System.out.println(this.x);
	}
	
	public void eineLetzteMethode()
	{
		super.eineLetzteMethode();
		System.out.println(this.x);
	}
}
