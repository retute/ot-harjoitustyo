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


## Tietojen pysyväistallennus

### Tiedostot

### Päätoiminnallisuudet

## Ohjelman rakenteen heikkoudet

![Luokkakaavio](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/holidayluokkakaavio.png)
