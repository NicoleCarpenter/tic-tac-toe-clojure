(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.system-util :as system]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.move-validator :as validator]
            [tic-tac-toe.ttt-board-presenter :as presenter]))

(defrecord UserInterface [io]
  view/View
  (prompt-player-move [x name]
    (system/display io (str name ", select a position for your move: ")))

  (get-player-move [x board]
    (let [move (system/get-user-input io)]
      (loop [m move]
        (if (validator/is-valid? m board)
          m
          (recur (system/get-user-input io))))))

  (display-tie-message [x]
    (system/display io (str "Game over. It's a tie.")))

  (display-winning-message [x winner-name]
    (system/display io (str "Game over. " winner-name " won!")))

  (print-board [x board]
    (system/display io (presenter/format-board-to-string board))))

(defn create-user-interface [io]
  (map->UserInterface {:io io}))