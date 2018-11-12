/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reettate
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @Test
    public void kassanRahamaaraVastaaMyytyjenLounaidenMaaraa() {

    }

    @Test
    public void kateisostoToimiiEdullistenOsaltaKunRahaaTarpeeksi() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void kateisostoToimiiEdullistenOsaltaKunRahaaEiTarpeeksi() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void kateisostoToimiiMaukkaidenOsaltaKunRahaaTarpeeksi() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void kateisostoToimiiMaukkaidenOsaltaKunRahaaEiTarpeeksi() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiostoToimiiEdullistenOsaltaKunRahaaTarpeeksi() {
        kortti = new Maksukortti(1000);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiostoToimiiEdullistenOsaltaKunRahaaEiTarpeeksi() {
        kortti = new Maksukortti(100);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiostoToimiiMaukkaidenOsaltaKunRahaaTarpeeksi() {
        kortti = new Maksukortti(1000);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiostoToimiiMaukkaidenOsaltaKunRahaaEiTarpeeksi() {
        kortti = new Maksukortti(100);
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void kortilleLadattaessaKassanSummaKasvaaJaKortinSaldoMuuttuu() {
        kortti = new Maksukortti(1000);
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
        assertEquals(100100, kassa.kassassaRahaa());
    }

    @Test
    public void kortilleLadattaessaKunSummaAlleNolla() {
        kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, -200);
        assertEquals(100, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaitaLounaitaMyytyPalauttaaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisiaLounaitaMyytyPalauttaaOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
}
