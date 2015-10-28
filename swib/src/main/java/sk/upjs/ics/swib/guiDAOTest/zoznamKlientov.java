/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.swib.guiDAOTest;

import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.swib.entity.Klient;

/**
 *
 * @author Uživateľ
 */
public class zoznamKlientov {
    private static List<Klient> zoznamKlientov;
    
    public static List<Klient> getList(){
        zoznamKlientov = new ArrayList<Klient>();
        
        Klient k1 = new Klient();
        k1.setId(1);
        k1.setMeno("Jozko");
        k1.setPriezvisko("Mrkvicka");
        k1.setCisloPreukazu("0001");
        k1.setRodneCislo("9005274897");
        
        Klient k2 = new Klient();
        k2.setId(2);
        k2.setMeno("Peter");
        k2.setPriezvisko("Hruska");
        k2.setCisloPreukazu("0002");
        k2.setRodneCislo("9008174727");
        
        Klient k3 = new Klient();
        k3.setId(3);
        k3.setMeno("Anička");
        k3.setPriezvisko("Čerešňová");
        k3.setCisloPreukazu("0003");
        k3.setRodneCislo("9051036475");
        
        zoznamKlientov.add(k1);
        zoznamKlientov.add(k2);
        zoznamKlientov.add(k3);
        
        return zoznamKlientov;
    }
}
