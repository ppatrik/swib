package sk.upjs.ics.swib.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.exceptions.NieJeMozneSplacat;
import sk.upjs.ics.swib.logic.Calculator;
import sk.upjs.ics.swib.logic.KonstantyPreKlienta;
import sk.upjs.ics.swib.logic.OfiCalculator;

public class OfiCalculatorTest {

    @BeforeClass
    public static void setUp() {
        System.setProperty("testovaciRezim", "true");
    }

    private List<Bonus> inicializujBonusy() {
        List<Bonus> bonusy = new ArrayList<>();

        Bonus bonus = new Bonus();
        bonus.setId(1);
        bonus.setUverId(1);
        bonus.setNazov("Bonus podla prijmu");
        bonus.setJeVacsiAko(new BigDecimal("1800"));
        bonus.setKolkoJeBonus(new BigDecimal("0.08"));
        bonus.setOrderNumber(1);
        bonus.setMultiplikatorId(1);
        bonus.setPorovnavacId(1);

        bonusy.add(bonus);

        bonus = new Bonus();
        bonus.setId(2);
        bonus.setUverId(1);
        bonus.setNazov("Bonus podla prijmu");
        bonus.setJeVacsiAko(new BigDecimal("1200"));
        bonus.setKolkoJeBonus(new BigDecimal("0.09"));
        bonus.setOrderNumber(2);
        bonus.setMultiplikatorId(1);
        bonus.setPorovnavacId(1);

        bonusy.add(bonus);

        bonus = new Bonus();
        bonus.setId(3);
        bonus.setUverId(1);
        bonus.setNazov("Bonus podla prijmu");
        bonus.setJeVacsiAko(new BigDecimal("700"));
        bonus.setKolkoJeBonus(new BigDecimal("0.1"));
        bonus.setOrderNumber(3);
        bonus.setMultiplikatorId(1);
        bonus.setPorovnavacId(1);

        bonusy.add(bonus);

        bonus = new Bonus();
        bonus.setId(4);
        bonus.setUverId(1);
        bonus.setNazov("Bonus podla prijmu");
        bonus.setJeVacsiAko(BigDecimal.ZERO);
        bonus.setKolkoJeBonus(new BigDecimal("0.12"));
        bonus.setOrderNumber(4);
        bonus.setMultiplikatorId(1);
        bonus.setPorovnavacId(1);

        bonusy.add(bonus);

        bonus = new Bonus();
        bonus.setId(5);
        bonus.setUverId(1);
        bonus.setNazov("Bonus podla sumy");
        bonus.setJeVacsiAko(new BigDecimal("20000"));
        bonus.setKolkoJeBonus(new BigDecimal("0.01"));
        bonus.setOrderNumber(5);
        bonus.setMultiplikatorId(2);
        bonus.setPorovnavacId(2);

        bonusy.add(bonus);

        bonus = new Bonus();
        bonus.setId(6);
        bonus.setUverId(1);
        bonus.setNazov("Bonus podla priemern");
        bonus.setJeVacsiAko(new BigDecimal("3000"));
        bonus.setKolkoJeBonus(new BigDecimal("0.007"));
        bonus.setOrderNumber(6);
        bonus.setMultiplikatorId(2);
        bonus.setPorovnavacId(3);

        bonusy.add(bonus);

        return bonusy;
    }

    @Test
    public void uplatniBonusPrePlatDo700Test() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("700"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonus(nasobic, bonusy.get(3), konstantyPreKlienta);

        assertTrue(new BigDecimal("1.12").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusPrePlatNad700Do1200Test() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("701"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonus(nasobic, bonusy.get(2), konstantyPreKlienta);

        assertTrue(new BigDecimal("1.1").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusyPrePlatDo700Test() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("700"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonusy(nasobic, bonusy.subList(3, 4), konstantyPreKlienta);

        assertTrue(new BigDecimal("1.12").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusyPrePlatNad700Do1200Test() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("700"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonusy(nasobic, bonusy.subList(2, 3), konstantyPreKlienta);

        assertTrue(new BigDecimal("1").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusyPrePlatDo700Test2() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("700"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonusy(nasobic, bonusy.subList(0, 4), konstantyPreKlienta);

        assertTrue(new BigDecimal("1.12").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusyPrePlatNad700Do1200Test2() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("701"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonusy(nasobic, bonusy.subList(0, 4), konstantyPreKlienta);

        assertTrue(new BigDecimal("1.1").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusyPrePlatNad1800Test() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("1801"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("1000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonusy(nasobic, bonusy.subList(0, 4), konstantyPreKlienta);

        assertTrue(new BigDecimal("1.08").compareTo(nasobic) == 0);
    }

    @Test
    public void uplatniBonusyVsetkyPrePlatNad1800Test() {
        Calculator calculator = new OfiCalculator();
        BigDecimal nasobic = new BigDecimal(1);

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                new BigDecimal("30000"),
                new BigDecimal("1801"),
                new BigDecimal("300"),
                100,
                0,
                new BigDecimal("100"),
                null,
                new BigDecimal("10000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        nasobic = calculator.uplatniBonusy(nasobic, bonusy, konstantyPreKlienta);

        assertTrue(new BigDecimal("1.063").compareTo(nasobic) == 0);
    }

    @Test
    public void mesacnaUrokovaSadzbaTest1() {
        Calculator calculator = new OfiCalculator();

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                // kolko si ide klient poziciat
                new BigDecimal("60000"),
                // priemernyMesacnyPrijem
                new BigDecimal("3000"),
                // mozeNaMesiacMaximalneSplacat
                new BigDecimal("2000"),
                // dobaVMesiacoch
                100,
                // pocetDeti
                1,
                // bonusNaDieta
                new BigDecimal("200"),
                // bonusNaManzelku
                new BigDecimal("200"),
                // priemernyMesacnyZostatok
                new BigDecimal("10000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        BigDecimal mesacnaUrokovaSadzba = calculator.mesacnaUrokovaSadzba(konstantyPreKlienta, bonusy);

        assertTrue(new BigDecimal("637.8").compareTo(mesacnaUrokovaSadzba) == 0);
    }

    @Test(expected = NieJeMozneSplacat.class)
    public void mesacnaUrokovaSadzbaTest2() {
        Calculator calculator = new OfiCalculator();

        KonstantyPreKlienta konstantyPreKlienta = new KonstantyPreKlienta(
                // kolko si ide klient poziciat
                new BigDecimal("60000"),
                // priemernyMesacnyPrijem
                new BigDecimal("1000"),
                // mozeNaMesiacMaximalneSplacat
                new BigDecimal("400"),
                // dobaVMesiacoch
                200,
                // pocetDeti
                1,
                // bonusNaDieta
                new BigDecimal("200"),
                // bonusNaManzelku
                new BigDecimal("200"),
                // priemernyMesacnyZostatok
                new BigDecimal("10000")
        );
        List<Bonus> bonusy = inicializujBonusy();
        BigDecimal mesacnaUrokovaSadzba = calculator.mesacnaUrokovaSadzba(konstantyPreKlienta, bonusy);

        assertTrue(new BigDecimal("318,9").compareTo(mesacnaUrokovaSadzba) == 0);
    }
}
