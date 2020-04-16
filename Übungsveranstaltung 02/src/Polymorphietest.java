
public class Polymorphietest {

	public static void main(String[] args) {
		Oberklasse ober = new Oberklasse();
		
		ober.methode();
		ober.nochEineMethode();
		ober.eineLetzteMethode();
		
		Unterklasse unter = new Unterklasse();
		unter.methode();
		unter.nochEineMethode();
		unter.eineLetzteMethode();
		
		Oberklasse ober2 = new Unterklasse();
		
		ober2.methode();
		ober2.nochEineMethode();
		ober2.eineLetzteMethode();
		

	}

}
