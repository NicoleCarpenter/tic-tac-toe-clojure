(ns tic-tac-toe.setup
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.human-move-generator :as hmg]
            [tic-tac-toe.computer-move-generator :as cmg]))

(defn create-initial-board [board-size]
  (into [] (repeat board-size nil)))

(defn create-players [ui]
 [(player/create-player "Player 1" "X" (hmg/create-human-move-generator ui))
  (player/create-player "Computer" "O" (cmg/create-computer-move-generator "O"))])