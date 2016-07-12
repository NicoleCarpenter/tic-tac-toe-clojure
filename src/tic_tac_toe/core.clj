(ns tic-tac-toe.core
  (:require [tic-tac-toe.io :as io]
            [tic-tac-toe.user-interface :as ui]
            [tic-tac-toe.setup :as setup]
            [tic-tac-toe.game :as game]))

(defn -main []
  (let [game-io (io/create-io)
        game-ui (ui/create-user-interface game-io)
        board (setup/create-initial-board 9)
        players (setup/create-players game-ui)]
    (game/play-game board players game-ui)))