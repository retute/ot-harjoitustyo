# Arkkitehtuurikuvaus

## Rakenne
Ohjelman koodissa on kolme eli pakkausta:
- lomabudjetti.ui
- lomabudjetti.dao
- lomabudjetti.domain
Pakkauksessa "lomabudjetti.ui" on ohjelman käyttöliittymä. Toistaiseksi 
käyttöliittymässä on aivan liikaa koodia näkymien suhteen.
Pakkauksessa "lomabudjetti.dao" puolestaan löytyy luokat, jotka 
vastaavat tiedon tallentamisesta ja hakemisesta.
Pakkaus "lomabudjetti.domain" puolestaan sisältää sovelluslogiikan.


## Käyttöliittymä
Käyttöliittymässä on useita eri näkymiä
- kirjautuminen
- käyttäjätunnuksen luominen
- lomalista (toteutettu osittain)
- loman lisääminen 
- yksittäisen loman katselu (ei toteutettu)
- aktiviteetin lisääminen (ei toteutettu)
Jokainen näkymä on luotu Scene-oliona ja toteutettu luokassa 
lomabudjetti.ui.HolidayUi. Scene-olioon on lisätty HBox-, GridPane- ja 
BorderPane-olioita. Kun näkymä vaihtuu, niin halutun näkymän Scene-olio 
lisätään sovelluksen stageen.

## Sovelluslogiikka

## Tietojen pysyväistallennus

### Tiedostot

### Päätoiminnallisuudet

## Ohjelman rakenteen heikkoudet

![Luokkakaavio](https://github.com/retute/ot-harjoitustyo/blob/master/Lomabudjetti/dokumentaatio/holidayluokkakaavio.png)
