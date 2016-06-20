(ns tic-tac-toe.board)

(defn place-piece [board space marker]
  (assoc board space marker))

(defn find-open-spaces [board]
  (keep-indexed #(if (nil? %2) %1) board))