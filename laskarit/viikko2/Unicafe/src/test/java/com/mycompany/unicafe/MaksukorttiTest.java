package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
 
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti = new Maksukortti(1000);
        kortti.lataaRahaa(300);
        assertEquals("Kortilla on rahaa 13.0 euroa", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKunRahaaTarpeeksi() {
        kortti = new Maksukortti(1000);
        kortti.otaRahaa(500);
        assertEquals("Kortilla on rahaa 5.0 euroa", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKunRahaaEiTarpeeksi() {
        kortti = new Maksukortti(1000);
        kortti.otaRahaa(1100);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void PalauttaaTrueKunOttaminenOnnistuu() {
        kortti = new Maksukortti(1000);
        assertEquals("Metori palauttaa true", kortti.otaRahaa(500));
    }
    
    @Test
    public void PalauttaaFalseKunOttaminenEiOnnistu() {
        kortti = new Maksukortti(10);
        assertEquals("Metori palauttaa false", kortti.otaRahaa(15));
    }
    
}
