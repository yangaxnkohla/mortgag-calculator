# Mortgage Calculator

![alt text](https://github.com/yangaxnkohla/mortgag-calculator/blob/dev_cljs/resources/public/img/make_money_with_stocks.jpeg)

A mortgage calculator built using Luminus version "3.57" (https://luminusweb.com/)

## Prerequisites

<code>Clojure 1.8.0 (https://repo1.maven.org/maven2/org/clojure/clojure/1.8.0/)</code>

<code>Leiningen >=2.0 (https://github.com/technomancy/leiningen)</code>

<code>Java OpenJDK 1.8.0.232</code>

<code>Git</code>

<code>WebStorm (or IntelliJ/Visual Studio Code)</code>

## Running

First, you must clone the dev_cljs branch into your local machine: 
<code>git clone https://github.com/yangaxnkohla/mortgag-calculator.git</code>

To start a web server for the application, in your favourite IDE -> <code>cd ../mortagag-calculator</code> into the project and run the following command in the terminal:

    lein run 

Once the server has started open Gooogle Chrome and go to <code>http//localhost:3000</code>. Voilà!...or is something broken? 👀

If all went accordingly you should be able to perform calculations and get your results. 😊

## Code

The Clojure code used for doing calculations can be found in: 
<code>/src/clj/mortgag_calculator/routes/home.clj</code>

The HTML page can be found in: 
<code>/resources/html/base.html</code>

The scripts for creating the database can be found in: 
<code>/resources/migrations/..</code>

Finally, the sql queries saving and getting calculations can be found in: 
<code>/resources/sql/queries.sql</code>
***
