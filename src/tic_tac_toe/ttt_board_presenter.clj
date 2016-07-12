(ns tic-tac-toe.ttt-board-presenter
  (:require [tic-tac-toe.board :as b]
            [tic-tac-toe.ttt-board :as ttt-board]))

(defn- add-leading-spaces [board]
  (for [placeholder board]
    (if (not= placeholder nil)
      (str " " placeholder)
      (str "  "))))

(defn- construct-vertical-fillers [number-of-rows]
  (conj (into [] (repeat (dec number-of-rows) " |")) nil))

(defn- construct-filled-row [row vertical-fillers]
  (into [](map vector row vertical-fillers)))

(defn- flatten-row [filled-row]
  (flatten filled-row))

(defn- append-row-with-new-line [flattened-row]
  (for [placeholder flattened-row]
    (if (= placeholder nil)
      (str "\n")
      (str placeholder))))

(defn- horizontal-filler [number-of-rows]
  (let [filler (apply str "===" (repeat (dec number-of-rows) "+==="))]
    (apply str filler "\n")))

(defn- create-filler-rows [number-of-rows]
  (repeat (horizontal-filler number-of-rows)))

(defn- add-horizontal-fillers [formatted-rows number-of-rows]
  (-> formatted-rows
      (conj (horizontal-filler number-of-rows))
      (conj "\n")))

(defn- format-row [row number-of-rows]
  (let [vertical-fillers (construct-vertical-fillers number-of-rows)
        filled-row (construct-filled-row row vertical-fillers)
        flattened-row (flatten-row filled-row)]
    (append-row-with-new-line flattened-row)))

(defn format-board-to-string [board]
  (let [formatted-board (add-leading-spaces board) 
        formatted-rows [] 
        number-of-rows (b/row-count (ttt-board/create-ttt-board) board) 
        rows (ttt-board/separate-rows formatted-board)]
    
    (->> 
      (create-filler-rows number-of-rows)
      (interleave (map #(format-row % number-of-rows) rows))
      (take (dec (* number-of-rows 2)))
      (flatten)
      (apply str))))