(ns tic-tac-toe.human-move-generator
  (:require [tic-tac-toe.move-generator :as generator]
            [tic-tac-toe.view :as view]))

(defrecord HumanMoveGenerator [ui]
  generator/MoveGenerator
  (select-space [x board]
    (view/get-player-move ui board)))

(defn mock-move [move]
  (map->HumanMoveGenerator (atom move)))

(defn create-human-move-generator [ui]
  (map->HumanMoveGenerator {:ui ui}))