# Käyttöohje

## Ohjelman käynnistäminen
Siirry ensin pom.xml-tiedoston sisältävään kansioon fifteenpuzzlesolver. Komento `mvn package` rakentaa ohjelman. Tämän jälkeen jar-tiedoston pitäisi löytyä kansiosta target. Siirry kansioon ja suorita komento `java -jar fifteenpuzzlesolver-1.0-SNAPSHOT.jar`.

## Ohjelman käyttäminen
Ohjelma käynnistyessään antaa myös itse ohjeet toiminnallisuuksien käyttämiseen. Mahdollisuudet ovat:
* "w", "a", "s" ja "d" -laattojen liikuttamiseen laudalla
* "shuffle" sekoittaa laudan sadalla satunnaisella siirrolla
* "solve" näyttää mikä on optimaalinen ratkaisu pelille laudan tästä tilasta
* "x" sulkee ohjelman

Mikä tahansa muu komento ei aiheuta mitään toimenpiteitä. Näillä komennoilla voi testata algoritmin toimivuutta tai vaikka pelata 15-peliä ja löytää ratkaisu itse!
