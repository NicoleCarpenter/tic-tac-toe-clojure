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

(defn- player-move [current-player board game-ui]
  (let [generator (:move-generator current-player)]
    (do
      (view/prompt-player-move game-ui (:name current-player))
      (mg/select-space generator board))))

(defn- display-current-board [board game-ui]
  (do
    (view/clear-screen game-ui)
    (view/print-board game-ui (tttboard/board-positions board))
    (view/print-board game-ui board)))

(defn- game-ui [io]
  (ui/create-user-interface io))

(defn play-game [board players]
  (loop [board board players players]
    (let [current-player (nth players 0)
          game-io (io/create-io)
          game-ui (game-ui game-io)]
      (cond 
        (not= (tttboard/find-winning-marker board) nil)
          (do
            (display-current-board board game-ui)
            (view/display-winning-message game-ui (:name (nth players 1))))
        (tttboard/is-tie-condition-met? board)
          (do
            (display-current-board board game-ui)
            (view/display-tie-message game-ui))
        :else
          (do
            (display-current-board board game-ui)
            (let [move (player-move current-player board game-ui)
                  marker (:marker current-player)
                  updated-board (tttboard/place-piece board (str move) marker)]
              (recur updated-board (reverse players))))))))