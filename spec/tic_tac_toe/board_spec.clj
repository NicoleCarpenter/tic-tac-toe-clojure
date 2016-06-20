(ns tic-tac-toe.user-interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :as ttt-board]))

(describe "place-piece"

  (it "places a marker at a spot on the board"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= [nil "X" nil nil nil nil nil nil nil] (ttt-board/place-piece board 1 "X")))))

(describe "find-open-spaces"

  (it "returns all spaces for an empty board"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= [0 1 2 3 4 5 6 7 8] (ttt-board/find-open-spaces board))))

  (it "returns no spaces for a full board"
    (let [board ["X" "O" "X" "O" "X" "O" "X" "O" "X"]]
      (should= [] (ttt-board/find-open-spaces board))))

  (it "returns only open spaces"
    (let [board [nil "X" nil "O" nil "X" nil "O" nil]]
      (should= [0 2 4 6 8] (ttt-board/find-open-spaces board)))))