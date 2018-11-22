# Alustava määrittelydokumentti

## Sovelluksen tarkoitus

Sovellut on budjettisovellus. Tarkemmin se on tarkoitettu lomamatkan tai 
-matkojen budjettien suunnitteluun. Käyttäjä voi luoda useita lomiaan ja 
asettaa jokaiselle oman budjetin. Jokaiselle lomalle on mahdollista 
lisätä aktiviteettaja ja mahdollisia menoja. Sen tarkoitus on auttaa 
tulevan loman suunnittelussa sille asetetun budjetin ehdoin. 

## Käyttäjät
Sovelluksella on toistaiseksi vain normaaleja käyttäjiä, jotka 
kirjautuvat sisään omilla tunnuksillaan. Sovellukseen lisätään 
mahdollisesti niin sanottuja lahjottajia, jotka voivat kasvattaa 
lomabudjettia esimerkiksi syntymäpäivän kunniaksi.

## Toiminnallisuudet
### Ennen kirjautumista
Käyttäjä voi luoda sovellukseen oman tunnuksensa. Tunnuksen oltava 
uniikki ja vähintään 4 merkkiä.
Käyttäjä voi kirjautua järjestelmään (tämä on etusivu). Jos tunnus on 
olemassa ja on oikea, niin kirjautuminen onnistuu. Muuten järjestelmä 
ilmoittaa ongelmasta.

### Kirjautumisen jälkeen
- Käyttäjä näkee omat lomansa
- Käyttäjä voi lisätä listaan uuden loman ja budjetin sille
- Loman näkee paremmin klikkaamalla lomaa
- Loman tiedoissa näkyy lista aktiviteeteista ja menoista, joita lomalle 
sunnittelee
- Käyttäjä voi lisätä lomalle lisää suunnitelmia, ja antaa niille 
priorteettinumeron 1-5, 1 korkein. 
- Käyttäjä on ainoa, joka voi muakata omia lomiaan
- Kuka vain voi nähdä lomat ja lomasuunnitelmat
- Käyttäjä on ainoa, joka voi nähdä lomabudjetin
- Käyttäjä voi merkitä loman vietetyksi tai poistaa aktiviteetteja
- Käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideat
- Lomalle annetaan ajankohta ja loma poistuu listasta itsestään ajan 
mentyä umpeen
- Järjestelmään voi kirjautua ja löytää haluamansa ihmisen lomat ja 
kasvattaa lomabujettia "lahjoituksilla"
