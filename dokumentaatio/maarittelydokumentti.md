# Määrittelydokumentti

## Yleistä
Harjoittelutyöni aihe on tehdä ratkaisija [15-pelille](https://fi.wikipedia.org/wiki/15-peli). Kielenä käytän Javaa.

![15-peli ratkaistussa tilassa](https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/15-puzzle.svg/240px-15-puzzle.svg.png "15-peli ratkaistussa tilassa")

## Algoritmi
Ongelman voi ajatella verkkona, jossa kaikki laudan mahdolliset järjestykset ovat solmuja ja laatan liikuttaminen laudalla vastaa kaarta pitkin etenemistä. Tiloja on kuitenkin (16!/2) eli n. 10^13 [Spaans, s. 34], mikä on tietysti liian suuri määrä koko verkon kartoittamiseksi. Tämän ongelman ratkaisemiseksi algoritmi käyttää A*-hakua.

Algoritmin käyttämä heuristiikkafunktio laskee laudan Manhattan-etäisyyden, joka tämän ongelman tapauksessa tarkoittaa kunkin yksittäisen laatan etäisyyttä halutulta paikaltaan. Heuristiikkafunktion avulla algoritmi priorisoi haussaan sellaiset laudan tilat, jotka näyttävät todennäköisemmin vievän kohti ratkaisua.

## Tietorakenteet
Toteuttamani tietorakenteet määrittyvät myöhemmin kun algoritmin lopullinen muoto (ja sen vaatimat tietorakenteet) hahmottuu.

## Aikavaativuus
15-peli on mahdollista ratkaista polynomisessa ajassa. Optimaalisen ratkaisun löytäminen on NP-täydellinen ongelma. [Spaans, s.35] A*-haullakin ratkaisun löytäminen voi kestää useita sekunteja, kuten medium.com-sivulle tehdystä blogista voi havaita [Jensen]. Mahdollisia keinoja parantaa algoritmia on mm. IDA*-algoritmin käyttö, heuristiikkafunktion viilaaminen ottamalla huomioon laattojen lineaarinen konflikti ja haun jo käydyssä solmussa etenemisen estäminen [Spaans, s. 35], näihin palaan mikäli aika kurssilla riittää.

## Syötteet
Käyttöliittymässä on pelattavissa oleva versio pelistä. Käyttäjä voi haluamansa mukaan sekoittaa kaudan itse tai käyttää automaattista sekoitusominaisuutta. Mistä tahansa laudan asennosta käyttäjä voi määrätä ohjelmaa ratkaisemaan laudan, jolloin ohjelma näyttää ratkaisun välivaiheineen.

## Lähteet
* Jensen, Preston: _Solving the 15-puzzle_, https://medium.com/@prestonbjensen/solving-the-15-puzzle-e7e60a3d9782.
* Spaans, Ruben: _Solving sliding-block puzzles_, 2009.
