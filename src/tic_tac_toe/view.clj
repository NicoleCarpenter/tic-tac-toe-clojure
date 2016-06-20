(ns tic-tac-toe.view)
  
  (defprotocol View
    (prompt-player-move [x name])
    (get-player-move [x]))