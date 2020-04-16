
public class Oberklasse {
	private int x;
	
	public Oberklasse()
	{
		this.x = 7;
	}
	
	public void methode()
	{
		System.out.println(this.x);
	}
	
	public void nochEineMethode()
	{
		System.out.println(this.x);
		this.methode();
	}
	
	public void eineLetzteMethode()
	{
		System.out.println(this.x);
	}

}
