(ns tic-tac-toe.mocks.mock-user-interface
  (:require [tic-tac-toe.view :as view]))

(defrecord MockUserInterface [mock-io]
  view/View
  (prompt-player-move [x player-name]
    (str "Prompt player move called with " player-name))

  (get-player-move [x board]
    @(:value mock-io))

  (display-tie-message [x]
    "Display tie message called")

  (display-winning-message [x winner-name]
    (str "Display winning message called with " winner-name))

  (print-board [x board]
    (str "Print board called with " board))

  (clear-screen [x]
    "Clear screen called"))

(defn mock-value [input]
  (->MockUserInterface (atom input)))

(defn create-mock-user-interface [io]
  (MockUserInterface. io))