# Arkkitehtuurikuvaus

## Rakenne
Ohjelman koodissa on kolme eli pakkausta:
- lomabudjetti.ui
- lomabudjetti.dao
- lomabudjetti.domain

Pakkauksessa "lomabudjetti.ui" on ohjelman käyttöliittymä.
Pakkauksessa "lomabudjetti.dao" puolestaan löytyy luokat, jotka 
vastaavat tiedon tallentamisesta ja hakemisesta.
Pakkaus "lomabudjetti.domain" sisältää sovelluslogiikan.


## Käyttöliittymä
Käyttöliittymässä on useita eri näkymiä
- kirjautuminen
- käyttäjätunnuksen luominen
- lomalista
- loman lisääminen
- yksittäisen loman katselu

Jokainen näkymä on Scene-olio ja toteutettu luokassa 
lomabudjetti.ui.HolidayUi. Scene-olioon on lisätty VBox-, HBox-, GridPane-, ScrollPane- ja 
BorderPane-olioita. Kun näkymä vaihtuu, niin halutun näkymän Scene-olio 
lisätään sovelluksen stageen.

## Sovelluslogiikka

Sovellus rakentuu luokkien User, Holiday ja Activity ympärille. Yhdellä käyttäjällä voi olla useita lomia. Yhdellä 
lomalla puolestaan on mahdollisesti useampi aktiviteetti budjetin sallimissa rajoissa. Alla kuvaa logiikasta:

![Logiikka](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/lomabudjetti.png)

Pakkauksesta domain löytyy luokka HolidayService, joka vastaa ohjelman toiminnallisuudesta. Luokka tarjotaa seuraavat 
metodit:
- planHoliday
- cancelHoliday
- getHolidays
- planActivity
- getDestinations

![Luokkakaavio](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/holidayluokkakaavio.png)


## Tietojen pysyväistallennus
Sovelluksessa on pakkaus lomabudjetti.dao, josta löytyy tallennuksesta vastaavat luokat.
- UserDao
- HolidayDao
- ActivityDao
- FileUserDao
- FileHolidayDao
- FileActivityDao

Nämä luokat vastaavat siitä, että käyttäjä ja hänen listaamat lomat aktiviteetteineen tallennetaan ja muistetaan myös uloskirjautumisen 
jälkeen. Käyttäjät tallennetaan tiedostoon *users.txt*, lomat tiedostoon *holidays.txt" ja aktiviteetit tiedostoon *activities.txt*.

### Päätoiminnallisuudet

#### Sisäänkirjautiminen
Kun käyttäjä kirjautuu sisään hän syöttää ensin käyttäjänimensä tekstikenttään ja painaa nappia "Login". Napin painalluksen jälkeen ohjelma 
lähettää kirjautumispyynnön käyttöliittymälle, joka lähettää tiedon syötetystä käyttäjänimestä ja pyynnön luokalle HolidayService. Tämän 
jälkeen ohjelma käyttää luokan HolidayService metodia findByUsername("syötetty_nimi") ja lähettää pyynnon UserDao-luokalle. UderDao luokka 
tutkii, löytyykö vastaavaa nimeä tiedostosta users.txt. Jos nimi löytyy, niin UserDao palauttaa kyseisen käyttäjän ja kirjautumispyyntö 
hyväksytään ja näkymä eli stage saa uuden scenen stage.setScene(allHolidays). Jos käyttäjänimeä ei löydy tiedostoista, niin ohjelma 
palauttaa null ja kirjautumissivulle ilmestyy viesti "Something went wrong, try again!".

![Sisäänkirjautuminen](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/login.png)

#### Uuden käyttäjän luominen
Käyttäjä syöttää näkymän signinScene tekstikenttään haluamansa käyttäjänimen ja painaa nappia "Sign in". Jos käyttäjänimi on sopivan 
pitkä, niin napista lähtee pyyntö käyttöliittymälle uuden käyttäjän lisäämisestä. Käyttöliittymä puolestaan lähettää pyynnön ja annetun 
käyttäjänimen luokalle HolidayService, joka lähettää metodilla findByUsername("annettu_käyttäjänimi") pyynnön luokalle UserDao. UserDao 
palauttaa null, jos käyttäjää ei ole olemassa. Tällöin HolidayService lähettää uuden pyynnön, joka haluaa luoda uuden käyttäjän. Jos 
käyttäjän luonti onnistuu, niin ohjelma palauttaa true ja siirtyy kirjautumissivulle. Jos metodi findByUsername("nimi") puolestaan palauttaa 
löytyneen käyttäjän, niin ohjelma palauttaa false ja asettaa tunnuksenluontisivulle tekstin, joka ilmoittaa, että tunnus on varattu.

![Uusikäyttäjä](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/newuser.png)

#### Loman lisääminen
![Uusiloma](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/addholiday.png)

#### Loman poistaminen
![Poistaloma](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/deleteholiday.png)

#### Loman avaaminen


## Ohjelman rakenteen heikkoudet
- Loman avaaminen on hankalaa. En vain millään osannut tehdä nappia, jolla saisi kyseisen loman avattua helpommin.
