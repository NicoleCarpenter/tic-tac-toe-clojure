(ns tic-tac-toe.ttt-board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.ttt-board :as ttt-board]))

(defn test-board []
  (ttt-board/create-ttt-board))

(describe "place-piece"

  (it "places a marker at a spot on the board"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= [nil \X nil nil nil nil nil nil nil] (board/place-piece (test-board) active-board 1 \X)))))

(describe "find-open-spaces"

  (it "returns all spaces for an empty board"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= [0 1 2 3 4 5 6 7 8] (board/find-open-spaces (test-board) active-board))))

  (it "returns no spaces for a full board"
    (let [active-board [\X \O \X \O \X \O \X \O \X]]
      (should= [] (board/find-open-spaces (test-board) active-board))))

  (it "returns only open spaces"
    (let [active-board [nil \X nil \O nil \X nil \O nil]]
      (should= [0 2 4 6 8] (board/find-open-spaces (test-board) active-board)))))

(describe "is-tie-condition-met?"

  (it "returns false if tie condition is not met"
    (let [active-board [\X \O \O \O \X \X \X \O nil]]
      (should= false (ttt-board/is-tie-condition-met? active-board))))

  (it "returns false if tie condition is not met"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (ttt-board/is-tie-condition-met? active-board))))

  (it "returns true if tie condition is met"
    (let [active-board [\X \O \O \O \X \X \X \O \O]]
      (should= true (ttt-board/is-tie-condition-met? active-board)))))

(describe "find-winning-marker"

  (it "returns the winner if there is a completed row"
    (let [active-board [\X \X \X nil nil nil nil nil nil]]
      (should= \X (ttt-board/find-winning-marker active-board)))
    
    (let [active-board [nil nil nil \X \X \X nil nil nil]]
      (should= \X (ttt-board/find-winning-marker active-board)))
    
    (let [active-board [nil nil nil nil nil nil \X \X \X]]
      (should= \X (ttt-board/find-winning-marker active-board))))

  (it "returns the winner if there is a completed column"
    (let [active-board [\X nil nil \X nil nil \X nil nil]]
      (should= \X (ttt-board/find-winning-marker active-board)))
    
    (let [active-board [nil \X nil nil \X nil nil \X nil]]
      (should= \X (ttt-board/find-winning-marker active-board)))
    
    (let [active-board [nil nil \X nil nil \X nil nil \X]]
      (should= \X (ttt-board/find-winning-marker active-board))))

  (it "returns the winner if there is a completed diagonal"
    (let [active-board [\X nil nil nil \X nil nil nil \X]]
      (should= \X (ttt-board/find-winning-marker active-board)))
    
    (let [active-board [nil nil \X nil \X nil \X nil nil]]
      (should= \X (ttt-board/find-winning-marker active-board))))

  (it "returns nil when there is no winner"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= nil (ttt-board/find-winning-marker active-board)))))