# mortgag-calculator

A mortgage calculator built using Luminus version "3.57" (https://luminusweb.com/)

## Prerequisites

Clojure 1.8.0 (https://repo1.maven.org/maven2/org/clojure/clojure/1.8.0/)

Leiningen >=2.0 (https://github.com/technomancy/leiningen)

Java OpenJDK 1.8.0.232

Git

WebStorm (or IntelliJ or Visual Studio Code)

## Running

First, you must clone the dev_cljs branch into your local machine. 
    git clone https://github.com/yangaxnkohla/mortgag-calculator.git 

To start a web server for the application, in your favourite IDE -> cd ../mortagag-calculator into the project and run the following command in the terminal:

    lein run 

Once the server has started open Gooogle Chrome and go to http//localhost:3000. Voilà!...or is something broken? 👀

If all went accordingly you should be able to perform calculations and get your results. 😊

## Code

The Clojure code used for doing calculations can be found in /src/clj/mortgag_calculator/routes/home.clj.

The HTML page can be found in /resources/html/base.html.

The scripts for creating the database can be found in /resources/migrations/

Finally, the sql queries saving and getting calculations can be found in /resources/sql/queries.sql.

***
