(ns tic-tac-toe.computer-move-generator
  (:require [tic-tac-toe.move-generator :as generator]
            [tic-tac-toe.user-interface :as user-interface]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.move-validator :as validator]))

(defrecord ComputerMoveGenerator [io]
  generator/MoveGenerator
  (select-space [x board]
    (let [move (rand-int (count board))]
      (loop [m move]
        (if (= (board m) nil)
          (str m)
          (recur (rand-int (count board))))))))

(defn mock-move [move]
  (map->ComputerMoveGenerator (atom move)))

(defn create-computer-move-generator [io]
  (map->ComputerMoveGenerator {:io io}))
