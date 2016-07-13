(ns tic-tac-toe.setup
  (:require [tic-tac-toe.player :as player]))

(defn create-initial-board [board-size]
  (into [] (repeat board-size nil)))

(defn create-players [players]
 [(player/create-player (get (get players 0) :name) (get (get players 0) :marker) (get (get players 0) :move-generator))
  (player/create-player (get (get players 1) :name) (get (get players 1) :marker) (get (get players 1) :move-generator))])