# Toteutusdokumentti

## Yleisrakenne
![Ohjelman luokkakaavio](https://yuml.me/3a85a961.png)
Ohjelman rakenne on melko yksinkertainen. Pääluokkana ja käyttöliittymänä toimii Fifteenpuzzlesolver. Muuttujaan N on tallennettu laudan sivun koko, jota vaihtamalla pelin voi helposti muuttaa vaikkapa 8-peliksi. Main-metodissa luodaan yksi pelilauta Board ja ratkaisija Solver ohjelman käyttöön.

Luokka Board mallintaa 15-pelin pelilautaa. Lautoja voi tosin olla olemassa monta yhtäaikaa, sillä ratkaisija luo aina uuden laudan yrittäessään uutta siirtoa kohti ratkaisua. Boardilla saattaa myös itsellään olla muuttujana tallessa yksi Board, johon on tallennettu laudan tila ennen edellistä siirtoa ratkaisualgoritmia varten.

Ratkaisija on luokassa Solver, jolle annetaan attribuuttina lauta siinä tilassa, mistä se halutaan ratkaista. Board ei kuitenkaan tallennu Solver-olion omaksi muuttujaksi missään vaiheessa, vaan se tallentaa laudan eri tiloja käyttämiinsä BoardStackiin ja BoardMinHeapiin, ja lopuksi palauttaa ratkaistun laudan kutsujalle.

Luokat BoardMinHeap ja BoardStack ovat itse toteutetut minimikeko ja pino.

## Aika- ja tilavaativuus
15-pelin optimaalisen ratkaisun löytäminen on NP-täydellinen ongelma, joten kovin nopeaa ratkaisua sille ei ole olemassakaan. [...]

## Puutteet ja parannusehdotukset
Algoritmi jäi lopulta hivenen liian tehottomaksi. Vaikka lauta usein ratkeaakin, toisinaan ratkaisu odotuttaa itseään liian pitkään. Vähiten siirtoja vaativaa optimaalista ratkaisua järkevämpää olisi saattanut olla tehdä algoritmi, joka löytää yhden minkä tahansa ratkaisun. Tällainen algoritmi on mahdollista tehdä paljon nopeammin.
