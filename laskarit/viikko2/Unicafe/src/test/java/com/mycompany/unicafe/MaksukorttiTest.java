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
        assertEquals("saldo: 10.0", kortti.toString());
    }
 
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(300);
        assertEquals("saldo: 13.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKunRahaaTarpeeksi() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenKunRahaaEiTarpeeksi() {
        kortti.otaRahaa(1100);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void palauttaaTrueKunOttaminenOnnistuuMuutenFalse() {
        assertTrue( kortti.otaRahaa(5));
    }
    
    @Test
    public void palauttaaSaldonOikein() {
        assertEquals(1000, kortti.saldo());
    }
    
//    @Test
//    public void PalauttaaFalseKunOttaminenEiOnnistu() {
//        assertFalse(kortti.otaRahaa(15));
//    }
    
}
