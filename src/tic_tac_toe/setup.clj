(ns tic-tac-toe.setup
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.human-move-generator :as human-move-generator]
            [tic-tac-toe.computer-move-generator :as computer-move-generator]
            [tic-tac-toe.io :as io]))

(defn create-initial-board [board-size]
  (into [] (repeat board-size nil)))

(defn create-players []
 [(player/create-player "Player 1" "X" (human-move-generator/create-human-move-generator (io/create-io)))
  (player/create-player "Computer" "O" (computer-move-generator/create-computer-move-generator "O"))])