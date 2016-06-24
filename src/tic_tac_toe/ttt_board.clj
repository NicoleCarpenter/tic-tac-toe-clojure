(ns tic-tac-toe.ttt-board
  (:require [tic-tac-toe.board :as board]))

(defrecord TTTBoard []
  board/Board
  (place-piece [x board space marker]
    (assoc board space marker))

  (find-open-spaces [x board]
    (keep-indexed #(if (nil? %2) %1) board)))

(defn row-count [board]
  (int (Math/sqrt (count board))))

(defn board-positions [board]
  (range (count board)))

(defn- find-winning-rows [board]
  (mapv vec (partition (row-count board) (board-positions board))))

(defn- find-winning-columns [board]
  (apply mapv vector (find-winning-rows board)))

(defn- find-forward-diagonal [board]
  (let [rows (find-winning-rows board) row-count (row-count board)]
    (for [i (range row-count)]
      (get (get rows i) i))))

(defn- find-backward-diagonal [board]
  (let [rows (find-winning-rows board) row-count (row-count board)]
    (for [i (range row-count)]
      (get (get rows i) (-(- row-count 1) i)))))

(defn- find-winning-diagonals [board]
  (let [board-positions (board-positions board)]
    (conj [(find-forward-diagonal board-positions)] (find-backward-diagonal board-positions))))

(defn- find-winning-combinations [board]
  (concat (find-winning-rows board) (find-winning-columns board) (find-winning-diagonals board)))

(defn- find-values-at-combo-positions [board combo]
  (mapv board combo))

(defn- has-unique-values? [combo-values]
  (= 1 (count (distinct combo-values))))

(defn- unmarked-combination? [combo-values]
  (= (first combo-values) nil))

(defn- matched-combination? [combo-values]
  (and (has-unique-values? combo-values) (not (unmarked-combination? combo-values))))

(defn- identify-winner [board combo]
  (let [combo-values (find-values-at-combo-positions board combo)]
    (if (matched-combination? combo-values) (first combo-values) nil)))

(defn create-ttt-board []
  (map->TTTBoard {}))

(defn place-piece [board space marker]
  (assoc board (read-string space) marker))

(defn separate-rows [board]
  (mapv vec (partition (row-count board) board)))

(defn is-tie-condition-met? [board]
  (empty? (board/find-open-spaces (create-ttt-board) board)))

(defn find-winning-marker [board]
  (let [winning-combinations (find-winning-combinations board)]
    (first (filter (complement nil?) 
      (for [combo winning-combinations]
        (identify-winner board combo))))))