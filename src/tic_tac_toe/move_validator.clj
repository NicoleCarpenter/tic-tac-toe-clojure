(ns tic-tac-toe.move-validator)

(defn- is-valid-number? [move]
  (and (not= move "") (not= move " ") (integer? (read-string move))))

(defn- is-valid-space? [move board]
  (if (is-valid-number? move)
    (and (> (read-string move) 0) (< (read-string move) (count board)))
    false))

(defn- is-open-space? [move board]
  (if (is-valid-number? move)
    (= (get board (read-string move)) nil)
    false))

(defn is-valid? [move board]
  (if (is-valid-number? move)
    (every? true? [(is-valid-number? move) (is-valid-space? move board) (is-open-space? move board)])
    false))