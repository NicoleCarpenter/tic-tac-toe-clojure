(ns tic-tac-toe.setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.setup :as setup]
            [tic-tac-toe.human-move-generator :as hmg]
            [tic-tac-toe.computer-move-generator :as cmg]
            [tic-tac-toe.mocks.mock-user-interface :as mock-ui]
            [tic-tac-toe.mocks.mock-io :as mock-io]))

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
  (let [mock-io (mock-io/mock-value "1")
        test-ui (create-test-ui mock-io)
        test-players [{:name "Player 1" :marker "X" :move-generator (hmg/create-human-move-generator test-ui)}
                      {:name "Computer" :marker "O" :move-generator (cmg/create-computer-move-generator "O")}]
        players (setup/create-players test-players)]
    (it "should create two players"
      (should= (count players) 2))

    (it "should have Player 1 as first player name"
      (should= (:name (nth players 0)) "Player 1"))

    (it "should have X as nth player marker"
      (should= (:marker (nth players 0)) "X"))

    (it "should have Computer as first player name"
      (should= (:name (nth players 1)) "Computer"))

    (it "should have O as second player marker"
      (should= (:marker (nth players 1)) "O"))))