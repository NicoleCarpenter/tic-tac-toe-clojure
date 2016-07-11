(ns tic-tac-toe.setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.setup :as setup]
            [tic-tac-toe.mocks.mock-user-interface :as mock-ui]
            [tic-tac-toe.mocks.mock-io :as mock-io]
            [tic-tac-toe.view]))

(defn create-test-ui [mock-io]
  (mock-ui/create-mock-user-interface mock-io))

(describe "create-initial-board"

  (it "creates an empty vector of board size"
    (let [board-size 9]
      (should= [nil nil nil nil nil nil nil nil nil] (setup/create-initial-board board-size)))))

  (it "creates an empty vector of board size"
    (let [board-size 16]
      (should= [nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil] (setup/create-initial-board board-size))))

(describe "create-players"
  (it "should create two players"
    (should= (count (setup/create-players (create-test-ui (mock-io/create-mock-io)))) 2))

  (it "should have Player 1 as first player name"
    (should= (:name (nth (setup/create-players (create-test-ui (mock-io/create-mock-io))) 0)) "Player 1"))

  (it "should have X as nth player marker"
    (should= (:marker (nth (setup/create-players (create-test-ui (mock-io/create-mock-io))) 0)) "X"))

  (it "should have Computer as first player name"
    (should= (:name (nth (setup/create-players (create-test-ui (mock-io/create-mock-io))) 1)) "Computer"))

  (it "should have O as second player marker"
    (should= (:marker (nth (setup/create-players (create-test-ui (mock-io/create-mock-io))) 1)) "O")))