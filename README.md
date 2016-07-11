[![Build Status](https://travis-ci.org/NicoleCarpenter/tic-tac-toe-clojure.svg?branch=master)](https://travis-ci.org/NicoleCarpenter/tic-tac-toe-clojure)

# Tic Tac Toe

Tic tac toe is a console application built with Clojure. The classic game of tic tac toe is a two player game where players trade turns placing a mark on a square board until either one player has won, or the game results in a draw. A player can win by placing three consecutive marks, either horizontally, vertically, or diagonally

```clojure
   X | X | X       X |   |         X |   |
  ===+===+===     ===+===+===     ===+===+===
     |   |         X |   |           | X |
  ===+===+===     ===+===+===     ===+===+===
     |   |         X |   |           |   | X
```

## Requirements

* [Clojure](https://clojure.org/)
* [Java](https://java.com/en/download/)
* [Leiningen](http://leiningen.org/)

## Running the Application

In your desired location in terminal, clone the repo

```
git clone git@github.com:NicoleCarpenter/tic-tac-toe-clojure.git
```

Then `cd` into the application's root directory

```
cd tic-tac-toe-clojure
```

From there, to run the application, type

```clojure
lein run
```

## Running the Tests

From the root directory, type

```clojure
lein spec
```