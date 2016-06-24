(ns tic-tac-toe.mocks.mock-user-interface
  (:require [tic-tac-toe.view :as view]))

(defrecord MockUserInterface [value]
  view/View
  (prompt-player-move [x name]
    @value)

  (get-player-move [x board]
    @value)

  (display-tie-message [x])

  (display-winning-message [x winner-name])

  (clear-screen [x]))

(defn mock-value [input]
  (->MockUserInterface (atom input)))

(defn create-mock-user-interface []
  (map->MockUserInterface {}))