(ns tic-tac-toe.human-move-generator
  (:require [tic-tac-toe.move-generator :as generator]
            [tic-tac-toe.user-interface :as user-interface]
            [tic-tac-toe.view :as view]))

(defrecord HumanMoveGenerator [ui io]
  generator/MoveGenerator
  (select-space [x board]
    (view/get-player-move (user-interface/create-user-interface io) board)))

(defn mock-move [move]
  (map->HumanMoveGenerator (atom move)))

(defn create-human-move-generator [ui io]
  (map->HumanMoveGenerator {:ui ui :io io}))