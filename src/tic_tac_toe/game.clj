(ns tic-tac-toe.game
  (:require [tic-tac-toe.ttt-board :as tttboard]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.user-interface :as ui]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.move-generator :as mg]
            [tic-tac-toe.human-move-generator :as hmg]
            [tic-tac-toe.computer-move-generator :as cmg]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.ttt-board-presenter :as presenter]))

(defn- player-move [current-player board]
  (let [generator (:move-generator current-player)]
    (mg/select-space generator board)))

(defn play-game [board players]
  (loop [board board players players]
    (let [current-player (nth players 0)]
      (cond 
        (not= (tttboard/find-winning-marker board) nil)
          (do
            (view/print-board (ui/create-user-interface (io/create-io)) board)
            (view/display-winning-message (ui/create-user-interface (io/create-io) ) (tttboard/find-winning-marker board)))
        (tttboard/is-tie-condition-met? board)
          (do
            (view/print-board (ui/create-user-interface (io/create-io)) board)
            (view/display-tie-message (ui/create-user-interface (io/create-io))))
        :else
          (do
            (view/print-board (ui/create-user-interface (io/create-io)) board)
            (recur (tttboard/place-piece board (player-move current-player board) (:marker current-player)) (reverse players) ))))))