(ns tic-tac-toe.ttt-board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :as b]
            [tic-tac-toe.ttt-board :as ttt-board]))

(defn test-board []
  (ttt-board/create-ttt-board))

(describe "place-piece"

  (it "places a marker at a spot on the board"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= [nil "X" nil nil nil nil nil nil nil] (b/place-piece (test-board) board "1" "X")))))

(describe "find-open-spaces"

  (it "returns all spaces for an empty board"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= [0 1 2 3 4 5 6 7 8] (b/find-open-spaces (test-board) board))))

  (it "returns no spaces for a full board"
    (let [board ["X" "O" "X" "O" "X" "O" "X" "O" "X"]]
      (should= [] (b/find-open-spaces (test-board) board))))

  (it "returns only open spaces"
    (let [board [nil "X" nil "O" nil "X" nil "O" nil]]
      (should= [0 2 4 6 8] (b/find-open-spaces (test-board) board)))))

(describe "row-count"

  (it "returns 3 for a 3x3 board"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= 3 (b/row-count (test-board) board))))

  (it "returns 4 for a 4x4 board"
    (let [board [nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil]]
      (should= 4 (b/row-count (test-board) board)))))

(describe "depth"

  (it "returns 9 for 9 open spaces"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= 9 (b/depth (test-board) board))))

  (it "returns 5 for 5 open spaces"
    (let [board ["X" "X" nil "O" "O" nil nil nil nil]]
      (should= 5 (b/depth (test-board) board))))

  (it "returns 0 for 0 open spaces"
    (let [board ["X" "X" "O" "O" "O" "X" "X" "X" "O"]]
      (should= 0 (b/depth (test-board) board)))))

(describe "board-positions"

  (it "returns a list of positions regardless if they are nil"
    (let [board ["X" "X" nil "O" "O" "X" nil "X" "O"]]
      (should= '(0 1 2 3 4 5 6 7 8) (ttt-board/board-positions board))))

  (it "returns a list of positions for any size board"
    (let [board [nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil]]
      (should= '(0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15) (ttt-board/board-positions board)))))

(describe "replace-nil-with-index"

  (it "returns a list of indices where there were nil"
    (let [board ["X" nil nil "O" "O" "X" nil "X" nil]]
      (should= '("X" 1 2 "O" "O" "X" 6 "X" 8) (ttt-board/replace-nil-with-index board))))

  (it "returns original list when none are nil"
    (let [board ["X" "X" "O" "O" "O" "X" "X" "X" "O"]]
      (should= '("X" "X" "O" "O" "O" "X" "X" "X" "O") (ttt-board/replace-nil-with-index board))))

  (it "returns all indices when all are nil"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= '(0 1 2 3 4 5 6 7 8) (ttt-board/replace-nil-with-index board)))))

(describe "separate-rows"

  (it "should return three rows for a 3x3 board"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= [[nil nil nil] [nil nil nil] [nil nil nil]] (ttt-board/separate-rows board))))

  (it "should return four rows for a 4x4 board"
    (let [board [nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil]]
      (should= [[nil nil nil nil] [nil nil nil nil] [nil nil nil nil] [nil nil nil nil]] (ttt-board/separate-rows board)))))

(describe "is-tie-condition-met?"

  (it "returns false if tie condition is not met"
    (let [board ["X" "O" "O" "O" "X" "X" "X" "O" nil]]
      (should= false (ttt-board/is-tie-condition-met? board))))

  (it "returns false if tie condition is not met"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= false (ttt-board/is-tie-condition-met? board))))

  (it "returns true if tie condition is met"
    (let [board ["X" "O" "O" "O" "X" "X" "X" "O" "O"]]
      (should= true (ttt-board/is-tie-condition-met? board)))))

(describe "find-winning-marker"

  (it "returns the winner if there is a completed row"
    (let [board ["X" "X" "X" nil nil nil nil nil nil]]
      (should= "X" (ttt-board/find-winning-marker board)))
    
    (let [board [nil nil nil "X" "X" "X" nil nil nil]]
      (should= "X" (ttt-board/find-winning-marker board)))
    
    (let [board [nil nil nil nil nil nil "X" "X" "X"]]
      (should= "X" (ttt-board/find-winning-marker board))))

  (it "returns the winner if there is a completed column"
    (let [board ["X" nil nil "X" nil nil "X" nil nil]]
      (should= "X" (ttt-board/find-winning-marker board)))
    
    (let [board [nil "X" nil nil "X" nil nil "X" nil]]
      (should= "X" (ttt-board/find-winning-marker board)))
    
    (let [board [nil nil "X" nil nil "X" nil nil "X"]]
      (should= "X" (ttt-board/find-winning-marker board))))

  (it "returns the winner if there is a completed diagonal"
    (let [board ["X" nil nil nil "X" nil nil nil "X"]]
      (should= "X" (ttt-board/find-winning-marker board)))
    
    (let [board [nil nil "X" nil "X" nil "X" nil nil]]
      (should= "X" (ttt-board/find-winning-marker board))))

  (it "returns nil when there is no winner"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= nil (ttt-board/find-winning-marker board)))))

(describe "is-winning-condition-met?"

  (it "should return true if there is a winner"
    (let [board ["X" "X" "X" nil nil nil nil nil nil]]
      (should= true (ttt-board/is-winning-condition-met? board))))

  (it "should return false if there is not a winner"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= false (ttt-board/is-winning-condition-met? board)))))

(describe "is-game-over?"

  (it "should return true if there is a winner"
    (let [board ["X" "X" "X" nil nil nil nil nil nil]]
      (should= true (ttt-board/is-game-over? board))))

  (it "should return true if there is a tie"
    (let [board ["X" "O" "O" "O" "X" "X" "X" "O" "O"]]
      (should= true (ttt-board/is-game-over? board))))

  (it "should return false if there is neither a tie nor a winner"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= false (ttt-board/is-game-over? board)))))