import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Waehrung;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GirokontoTest {
    private Girokonto sender;
    private Girokonto empfaenger;

    @BeforeEach
    void prepare() {
        sender = new Girokonto();
        empfaenger = new Girokonto();
    }

    @Test
    void getDispoTest() {
        assertEquals(sender.getDispo(), 500);
        assertEquals(empfaenger.getDispo(), 500);
    }


    @Test
    void setDispoTest() {
        sender.setDispo(700);
        assertEquals(sender.getDispo(), 700);
    }

    @Test
    void waehrungsWechselTest() {
        double dispoUmgerechnet = Waehrung.BGN.euroInWaehrungUmrechnen(sender.getDispo());
        sender.waehrungsWechsel(sender.getAktuelleWaehrung(), Waehrung.BGN);
        assertEquals(sender.getDispo(), dispoUmgerechnet);
        assertEquals(sender.getAktuelleWaehrung(), Waehrung.BGN);
    }

    @Test
    void ueberweisungAbsendenTest() {
        try {
            assertTrue(sender.ueberweisungAbsenden(500, empfaenger.getInhaber().toString(), empfaenger.getKontonummer(), 123, "TestNormal"));
            sender.sperren();
            assertThrows(GesperrtException.class, () -> {
                        sender.ueberweisungAbsenden(500, empfaenger.getInhaber().toString(), empfaenger.getKontonummer(), 123, "TestGesperrt");
                    }
            );
            sender.entsperren();;
            assertThrows(IllegalArgumentException.class, () ->{
                sender.ueberweisungAbsenden(-5, empfaenger.getInhaber().toString(), empfaenger.getKontonummer(), 123, "TestUUnter0Euro");
            });

        } catch (Exception e) {

        }
    }

    @Test
    void ueberweisungEmpfangenTest(){
        double oldKontostand= empfaenger.getKontostand();
        empfaenger.ueberweisungEmpfangen(500,"Sender", sender.getKontonummer(), 124, "TestNormalEmpfanegn");
        assertEquals(empfaenger.getKontostand(), oldKontostand+500);
        assertThrows(IllegalArgumentException.class, () ->{
            empfaenger.ueberweisungEmpfangen(-5,"Sender", sender.getKontonummer(), 124, "TestNormalEmpfanegn");
        });
        assertThrows(IllegalArgumentException.class, () ->{
            empfaenger.ueberweisungEmpfangen(-5,null, sender.getKontonummer(), 124, "TestNormalEmpfanegn");
        });
        assertThrows(IllegalArgumentException.class, () ->{
            empfaenger.ueberweisungEmpfangen(-5,"Sender", sender.getKontonummer(), 124, null);
        });
    }

    @Test
    void abhebenTest(){
        double oldKontostand=sender.getKontostand();
        try {
            assertTrue(sender.abheben(50));
            assertEquals(sender.getKontostand(), oldKontostand-50);
            sender.sperren();
            assertThrows(GesperrtException.class, () ->{
                sender.abheben(50);
            });
            sender.entsperren();
            assertThrows(IllegalArgumentException.class, () ->{
                sender.abheben(-50);
            });
        }catch (Exception e){

        }

    }
}
