(ns tic-tac-toe.core
  (:require [tic-tac-toe.view :as view]
            [tic-tac-toe.io :as io]
            [tic-tac-toe.user-interface :as ui]
            [tic-tac-toe.move-validator :as validator]))

(defn -main []
  (view/prompt-player-move 
    (ui/create-user-interface (io/create-io)) "Mike")
  
  (view/get-player-move 
    (ui/create-user-interface (io/create-io)) [nil nil nil nil nil nil nil nil nil]))
