# Full Stack -harjoitus - Java S20

## Pohjaprojekti

Pohjaprojektissa on toteutettu tietokantakäsittely väreille. 

**Luokat**

- `Color`: Yksi väri, vastaa tietokannan `color`taulua
- `ColorDAO`: Värikannan käsittelyn DAO-luokka
- `JavaS20FshPohjaApplication`: Application-luokka, mukana myös `CommandLineRunner` Bean, jossa
pieniä esimerkkejä DAOn käytöstä  

**Tietokanta**

Tietokantana tarkoitus käyttää PostgreSQL-palvelinta. Palvelin alustaa käynnistyessään 
*color*-taulun *fsbase* nimiseen tietokantaan ja lisäksi lisää tauluun joitakin värejä.

**Tietokannan konfigurointi**

Jotta kantakäsittely toimisi oikein, on syytä tehdä muutama toimenpide:

- Luo tietokanta. (Komentoriviltä: `createdb -U postgres fsbase`)
- Anna kirjautumistunnukset: Muokkaa tiedostoa `src/resources/application.properties`. Käy antamassa 
sinne käyttäjätunnus ja salasana millä kirjaudut sisään tietokantapalvelimelle
- Kun haluat että Spring Boot palvelimen käynnistys ei enää luo uudelleen *color*-taulua ja
alusta sitä oletusdatalla, niin muokkaa samaa `application.properties` tiedostoa kuin yllä, ja pane 
kommentteihin rivi \
       `spring.datasource.initialization-mode=always`

## REST Palvelin

Toteuta kontrolleri, ja siihen vähintään kaikkien värien palautus.

- `/api/colors` GET: *palauta kaikki värit*
- `/api/colors/:id` GET *palauta yksi väri id:n perusteella*

Voit ehtiessäsi toteuttaa esimerkiksi:

- `/api/colors` POST: *luo uusi väri*
- `/api/colors/:id` DELETE *poista yksi väri id:n perusteella*
- `/api/colors/:id` PUT *muokkaa väriä id:n perusteella*

Muista myös 404 Not Found tarvittaessa.

## HTML-käyttöliittymä

Lisää projektiin `src/resources/static` hakemistoon haluamasi HTML-tiedostot,
index.html sieltä jo löytyy, mutta hyvin alkeellisena.

Tee siis vähintään sivu, joka hakee JavaScriptillä REST-palvelimelta värit
ja näyttää ne taulussa(`<table>`) tai listassa (`<ul>` tai `<ol>`). 

## Pilveen

Vie projekti pilveen. Ohjeet löydät Herokusta

- luo tunnus
- luo projekti, lisää siihen Postgres tietokanta

