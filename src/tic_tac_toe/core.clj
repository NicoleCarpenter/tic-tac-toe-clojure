(ns tic-tac-toe.core
  (:require [tic-tac-toe.io :as io]
            [tic-tac-toe.user-interface :as ui]
            [tic-tac-toe.setup :as setup]
            [tic-tac-toe.human-move-generator :as hmg]
            [tic-tac-toe.computer-move-generator :as cmg]
            [tic-tac-toe.game :as game]))

(defn -main []
  (let [game-io (io/create-io)
        game-ui (ui/create-user-interface game-io)
        board (setup/create-initial-board 9)
        default-markers ["X" "O"]
        default-players [{:name "Player 1" :marker (get default-markers 0) :move-generator (hmg/create-human-move-generator game-ui)}
                         {:name "Computer" :marker (get default-markers 1) :move-generator (cmg/create-computer-move-generator default-markers)}]
        players (setup/create-players default-players)]
    (game/play-game board players game-ui)))