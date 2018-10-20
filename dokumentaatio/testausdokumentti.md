# Testausdokumentti

## Yksikkötestaus
Ohjelmassa on kattavat yksikkötestit kullekkin luokalle. Testit on tehnty JUnitilla, ja on helposti toistettavissa. Myös käyttöliittymänä toimivan main-metodin koodikattavuus näyttää tosin ikävästi nollaa prosenttia, koska lähinnä tulostuksia tekevän luokan testaaminen ei tuntunut kovin mielekkäältä. Muiden luokkien testikattavuus on kuitenkin 100%. Koodikattavuutta ohjelmaseuraa jacoco-pluginilla.

![code coverage](https://github.com/MWargelin/15-puzzle_solver/blob/master/dokumentaatio/pictures/coverage.png "code coverage")

## Suorituskykytestaus
Ohjelman suorituskyky ei valitettavasti ole aivan halutulla tasolla, tosin ei aivan kamalakaan. Tämä tosin oli tiedossa jo projektia aloittaessani, sillä optimaalisen ratkaisun löytäminen 15-pelille on NP-täydellinen ongelma.

Suorituskykytestissä ratkaistiin 100 pelilautaa ja mitattiin kuhunkin ratkaisuun kulunut aika. 8-pelin, eli pelin muunnelman, jossa laudan sivu on kolmen mittainen, algoritmi ratkaisee melko kivuttomasti. Keskimäärin aikaa kuluu 207,56 millisekuntia. Myös 15-peli ratkeaa toisinaan hyvinkin nopeasti, mutta tietyillä erityisen vaikeilla laudan tiloilla ratkaisu odotuttaa itseään kiusallisen pitkään.

Suorituskykytestikin on toteutettu JUnitilla, joten se on helposti toistettavissa.

![8-pelin suorituskyky](https://github.com/MWargelin/15-puzzle_solver/blob/master/dokumentaatio/pictures/8-peli%20suorituskyky.png "8-pelin suorituskyky")
* ratkaistu: 100/100
* keskiarvo: 207,56 ms
* mediaani: 104,28 ms

Kaaviosta voi nähdä, että peli ratkeaa usein melko nopeasti, mutta jotkin laudat näyttävät olevan erityisen hankalia tälle algoritmille. Suoritusaikojen mediaani onkin keskiarvoa vielä selkeästi alempi.

![15-pelin suorituskyky](https://github.com/MWargelin/15-puzzle_solver/blob/master/dokumentaatio/pictures/15-peli%20suorituskyky.png "15-pelin suorituskyky")
* ratkaistu: 87/100
* keskiarvo: 1622,79 ms (ratkaistut laudat)
* mediaani: 253,5 ms (kaikki laudat)

Kaaviossa ratkaisija oli asetettu keskeyttämään suoritus 30 sekunnin jälkeen. 30000 millisekunnin rajalle asettuvat pisteet tarkoittavat siis lautoja, joita algoritmi ei onnistunut ratkaisemaan tässä ajassa. Testissä näkyy miten algoritmi ei enää pure kaikkiin lautoihin, kun peli laajennetaan 15-peliksi: enää 87% laudoista ratkeaa alle 30 sekunnissa. Edelleen lauta kuitenkin ratkeaa melko usein aivan kohtuullisessa ajassa.
