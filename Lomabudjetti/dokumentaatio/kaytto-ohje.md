# Käyttöohje

## Ohjelman lataaminen
Ĺataa ohjelma 
[Lomabudjetti.jar](https://github.com/retute/ot-harjoitustyo/releases/tag/Lopullinen)


## Käynnistäminen
Ohjelma käynnistyy komennolla
> java -jar Lomabudjetti.jar

## Kirjautuminen
Käyttäjä kirjautuu ohjelmaan kirjoittamalla *tekstikenttään* oman 
käyttäjätunnuksensa ja painamalla nappia *"Login"*.
Jos tunnus on olemassa ja se on kirjoitettu oikein, niin käyttäjä 
kirjataan sisään ja hänen tulevien lomiensa listanäkymä avautuu.
Jos käyttäjätunnusta ei ole ohjelma valittaa, että jokin meni 
pieleen. Käyttäjä voi siirtyä tunnuksen 
luomissivulle napilla *"Sign in"*.

## Tunnuksen luominen
Tunnuksen luomiseen pääsee kirjautumissivun napista *"Sign in"*.
Käyttäjän tulee valita itselleen joku vähintään 4 merkkiä pitkä tunnus.
Kun tunnus on tarpeeksi pitkä ja se ei ole varattu, niin ohjelma 
ilmoittaa tunnuksen luomisen onnistunen. Nyt käyttäjä voi 
siirtyä kirjautumissivulle ja kirjautua sisään uudella tunnuksellaan.

## Lomalistasivu
Käyttäjä voi:
- kirjautua ulos näkymän oikealla ylälaidassa olevasta napista *"Log out"*.
- lisätä listaan uuden loman näkymän ylälaidassa olevasta napista *"Add new"*.
- nähdä kaikki tulevat lomat ja perua niitä napeista *"Cancel"*.
- avata yksittäisen loman sivun kirjoittamalla loman sijainnin *tekstikenttään*
 ja painamalla sen jälkeen nappia *"Open"*.

Lomalistasivulla on näkyvissä kaikki käyttäjän lomat ja niiden budjetit.

## Loman avaaminen
Yksittäisen loman saa avattua lomalistanäkymästä, jonka alalaidasta 
löytyy *tekstikenttä*. Tähän tekstikenttään käyttäjä kirjoittaa lomansa 
sijainnin ja painaa nappia *"Open"*. Jos loman nimi on kirjoitettu 
väärin tai lomaa ei ole olemassa, niin ohjelma valittaa tästä ja pyytää 
käyttäjää tarkastamaan kirjoitusasun uudestaan.

## Loman poistaminen
Loman poistaminen onnistuu lomalistanäkymässä. Jokaisen loman perässä on 
*"Cancel"*-nappi, joka poistaa loman käyttäjän tiedoista.

## Aktiviteettien lisääminen
Lomalle voi lisätä aktiviteettejä, kun loman oma näkymä on avattu. 
Näkymän oikeassa laidassa on kaksi *tekstikenttää*, joista ensimmäiseen 
laitetaan aktiviteetin nimi ja toiseen hinta. Molemmissa tekstikentissä 
täytyy olla sisältöä ja hinta tulee olla annettuna kokonaislukuna. Jos 
näin ei ole, niin ohjelma valittaa eikä lisää aktiviteettiä listaan.

Jos aktiviteetin hinta ylittää budjetin, niin lomaa ei lisätä 
listaan. Tällöin ohjelma valittaa, että budjetti ei riitä ja kertoo, kuinka paljon 
rahaa on jäljellä.

## Aktiviteettien poistaminen
Jokaisen aktiviteetin perässä on *"Delete"*-nappi. Tätä painamalla 
käyttäjä voi poistaa aktiviteetin lomalistasta. Poistetun aktiviteetin hinta lisätään 
takaisin jäljellä olevaan budjettiin.


## Ohjelman heikkoudet
- Käyttäjällä, lomilla, aktiviteeteilla ei ole id-tunnusta. Jos useampi käyttäjä 
lisää samannimisen loman, niin ohjelma lisää aktiviteetit molemmille lomille

