(ns tic-tac-toe.game
  (:require [tic-tac-toe.ttt-board :as ttt-board]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.move-generator :as mg]
            [tic-tac-toe.board :as b]))

(defn- player-move [current-player board ui]
  (let [generator (:move-generator current-player)]
    (do
      (view/prompt-player-move ui (:name current-player))
      (mg/select-space generator board))))

(defn- display-current-board [board ui]
  (do
    (view/clear-screen ui)
    (view/print-board ui (ttt-board/board-positions board))
    (view/print-board ui board)))

(defn play-game [board players ui]
  (loop [board board players players]
    (let [current-player (nth players 0)]
      (cond 
        (not= (ttt-board/find-winning-marker board) nil)
          (do
            (display-current-board board ui)
            (view/display-winning-message ui (:name (nth players 1))))
        (ttt-board/is-tie-condition-met? board)
          (do
            (display-current-board board ui)
            (view/display-tie-message ui))
        :else
          (do
            (display-current-board board ui)
            (let [move (player-move current-player board ui)
                  marker (:marker current-player)
                  updated-board (b/place-piece (ttt-board/create-ttt-board) board (str move) marker)]
              (recur updated-board (reverse players))))))))