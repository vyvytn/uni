import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        Bruch a = new Bruch(1, 2);
        Bruch b = new Bruch(2, 5);
        Bruch c = new Bruch(2, 6);
        Bruch d = new Bruch(2, 7);
        Bruch e = new Bruch(2, 8);
        Bruch f = new Bruch(2, 9);
        Bruch g = new Bruch(2, 10);
        Bruch h = new Bruch(2, 11);
        Bruch i = new Bruch(2, 12);
        Bruch j = new Bruch(2, 13);

        Bruch[] listOfBrueche = {a, b, c, d, e, f, g, h, i, j};

        System.out.println(b.ausrechnen());
        for (int k = 0; k < listOfBrueche.length; k++) {
            System.out.print(listOfBrueche[k].ausrechnen() + ", ");
        }

        Arrays.sort(listOfBrueche);
        for (int k = 0; k < listOfBrueche.length; k++) {
            System.out.println(listOfBrueche[k].ausrechnen());
        }


    }
}
