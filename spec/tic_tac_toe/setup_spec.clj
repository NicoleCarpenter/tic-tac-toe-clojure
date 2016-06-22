(ns tic-tac-toe.setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.setup :as setup]
            [tic-tac-toe.view]))

(describe "create-initial-board"

  (it "creates an empty vector of board size"
    (let [board-size 9]
      (should= [nil nil nil nil nil nil nil nil nil] (setup/create-initial-board board-size)))))

  (it "creates an empty vector of board size"
    (let [board-size 16]
      (should= [nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil] (setup/create-initial-board board-size))))

(describe "create-players"
  (it "should create two players"
    (should= (count (setup/create-players)) 2))

  (it "should have Player 1 as first player name"
    (should= (:name (nth (setup/create-players) 0)) "Player 1"))

  (it "should have X as nth player marker"
    (should= (:marker (nth (setup/create-players) 0)) "X"))

  (it "should have Computer as first player name"
    (should= (:name (nth (setup/create-players) 1)) "Computer"))

  (it "should have O as second player marker"
    (should= (:marker (nth (setup/create-players) 1)) "O")))