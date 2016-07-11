(ns tic-tac-toe.board)

(defprotocol Board
  (place-piece [x board space marker])
  (find-open-spaces [x board])
  (row-count [x board])
  (depth [x board]))