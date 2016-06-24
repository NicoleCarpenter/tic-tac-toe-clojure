(ns tic-tac-toe.view)
  
  (defprotocol View
    (prompt-player-move [x name])
    (get-player-move [x board])
    (display-tie-message [x])
    (display-winning-message [x winner-name])
    (print-board [x board])
    (clear-screen [x]))