(ns tic-tac-toe.core
  (:require [tic-tac-toe.game :as game]
            [tic-tac-toe.setup :as setup]))

(defn -main []
  (let [board (setup/create-initial-board 9)
        players (setup/create-players)]
    (game/play-game board players)))