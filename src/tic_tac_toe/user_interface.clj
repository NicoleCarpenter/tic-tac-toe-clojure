(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.system-util :as system]
            [tic-tac-toe.view :as view]))

(defrecord UserInterface [io]
  view/View
  (prompt-player-move [x name]
    (system/display io (str name ", select a position for your move: ")))

  (get-player-move [x]
    (system/get-user-input io)))

(defn create-user-interface [io]
  (map->UserInterface {:io io}))