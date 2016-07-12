(ns tic-tac-toe.computer-move-generator
  (:require [tic-tac-toe.move-generator :as generator]
            [tic-tac-toe.ttt-board :as ttt-board]
            [tic-tac-toe.board :as b]
            [clojure.set :refer :all]))

(defn- get-opponent-marker [current-marker]
  (if (= current-marker "X")
    "O"
    "X"))

(defn- to-set [s]
  (if (set? s) s #{s}))

(defn- set-union [s1 s2]
  (union (to-set s1) (to-set s2)))

(defn- score-sets [scored-spaces]
  (reduce #(merge-with set-union %1 %2) {} scored-spaces))

(defn- score [board max-player]
  (if (and (ttt-board/is-winning-condition-met? board) max-player)
    10
    (if (and (ttt-board/is-winning-condition-met? board) (not max-player))
      -10
      0)))

(defn- minimax [current-marker board index max-player depth original-depth]
  (let [ttt-board-rules (ttt-board/create-ttt-board)
        board (b/place-piece ttt-board-rules board (str index) current-marker)
        spaces (b/find-open-spaces ttt-board-rules board)]
    (if (ttt-board/is-game-over? board)
      (* depth (score board max-player))
      (let [max-player (not max-player)]
        (if max-player
          (apply max (map #(minimax (get-opponent-marker current-marker) board % max-player (dec depth) original-depth) spaces))
          (apply min (map #(minimax (get-opponent-marker current-marker) board % max-player (dec depth) original-depth) spaces)))))))

(def mem-minimax (memoize minimax))

(defn- minimax-scores [current-marker board]
  (let [ttt-board-rules (ttt-board/create-ttt-board)
        spaces (b/find-open-spaces ttt-board-rules board)
        depth (b/depth ttt-board-rules board)  
        score-map (sorted-map)
        board (ttt-board/replace-nil-with-index board)
        scored-spaces (map #(hash-map (mem-minimax current-marker board % true depth depth) %) spaces)]
    (into score-map (score-sets scored-spaces))))

(defn- best-spot [scores]
  (first (to-set (last (vals scores)))))

(defrecord ComputerMoveGenerator [marker]
  generator/MoveGenerator
    (select-space [x board]
      (let [scores (minimax-scores marker board)]
        (best-spot scores))))

(defn mock-move [move]
  (map->ComputerMoveGenerator (atom move)))

(defn create-computer-move-generator [marker]
  (map->ComputerMoveGenerator {:marker marker}))