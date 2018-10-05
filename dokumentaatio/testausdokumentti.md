# Testausdokumentti

Ohjelmassa on kattavat yksikkötestit kullekkin tärkeälle luokalle. Myös käyttöliittymänä toimivan main-metodin koodikattavuus näyttää ikävästi nollaa prosenttia, koska lähinnä tulostuksia tekevän luokan testaaminen ei tuntunut kovin mielekkäältä. Muiden luokkien testikattavuus on kuitenkin 100%.
![code coverage](https://github.com/MWargelin/15-puzzle_solver/blob/master/dokumentaatio/pictures/coverage.png "code coverage")

## Suorituskykytestaus
Ohjelman suorituskyky ei valitettavasti ole aivan halutulla tasolla, tosin ei aivan kamalakaan. Tämä tosin oli tiedossa jo projektia aloittaessani, sillä optimaalisen ratkaisun löytäminen 15-pelille on NP-täydellinen ongelma.

8-pelin algoritmi ratkaisee melko kivuttomasti, keskimäärin 207,56 millisekunnissa. Myös 15-peli ratkeaa toisinaan hyvinkin nopeasti, mutta tietyillä erityisen vaikeilla laudan tiloilla ratkaisu odotuttaa itseään kiusallisen pitkään.

![8-pelin suorituskyky](https://github.com/MWargelin/15-puzzle_solver/blob/master/dokumentaatio/pictures/8-peli%20suorituskyky.png "8-pelin suorituskyky")
* keskiarvo: 207,56 ms
* mediaani: 104,28 ms

Kaaviosta voi nähdä, että peli ratkeaa usein melko nopeasti, mutta jotkin laudat näyttävät olevan erityisen hankalia tälle algoritmille. Suoritusaikojen mediaani onkin keskiarvoa vielä selkeästi alempi.
