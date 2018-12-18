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
lomabudjetti.ui.HolidayUi. Scene-olioon on lisätty HBox-, GridPane- ja 
BorderPane-olioita. Kun näkymä vaihtuu, niin halutun näkymän Scene-olio 
lisätään sovelluksen stageen.

## Sovelluslogiikka
![Luokkakaavio](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/holidayluokkakaavio.png)


## Tietojen pysyväistallennus

### Tiedostot

### Päätoiminnallisuudet

#### Sisäänkirjautiminen
![Sisäänkirjautuminen](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/login.png)

#### Uuden käyttäjän luominen
![Uusikäyttäjä](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/newuser.png)

#### Loman lisääminen
![Uusiloma](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/addholiday.png)

#### Loman poistaminen
![Poistaloma](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/kuvat/deleteholiday.png)

#### Loman avaaminen


## Ohjelman rakenteen heikkoudet
