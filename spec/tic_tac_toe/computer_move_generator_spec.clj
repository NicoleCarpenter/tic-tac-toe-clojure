(ns tic-tac-toe.computer-move-generator-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.move-generator :as gen]
            [tic-tac-toe.computer-move-generator :as computer-gen]
            [tic-tac-toe.mocks.mock-io :as io]
            [tic-tac-toe.mocks.mock-user-interface :as ui]))

(defn- test-computer-move-generator [markers]
  (computer-gen/create-computer-move-generator markers))

(describe "select-space"
  (let [test-generator (test-computer-move-generator ["X" "O"])]

    (it "should force a tie"
      (let [board ["X" "X" "O" "O" "O" "X" "X" "O" nil]]
        (should= 8 (gen/select-space test-generator board))))

    (it "should block an opponent win"
      (let [board [nil nil nil nil "O" nil nil "X" "X"]]
        (should= 6 (gen/select-space test-generator board))))

    (it "should win if possible"
      (let [board ["O" "O" nil nil nil nil nil "X" "X"]]
        (should= 2 (gen/select-space test-generator board))))

    (it "should win late in the game"
      (let [board [ "X" "O" "X" "O" "O" "X" nil "X" nil ]]
        (should= 8 (gen/select-space test-generator board))))

    (it "should block on a second move"
      (let [board ["X" nil nil nil "O" nil nil nil "X"]]
        (should-not (= 0 (gen/select-space test-generator board)))
        (should-not (= 2 (gen/select-space test-generator board)))
        (should-not (= 4 (gen/select-space test-generator board)))
        (should-not (= 6 (gen/select-space test-generator board)))
        (should-not (= 8 (gen/select-space test-generator board)))))

    (it "should set up double win scenario if available"
      (let [board ["O" "O" "X" "X" nil nil nil nil nil]]
        (should= 4 (gen/select-space test-generator board))))

    (it "should set up center fork"
      (let [board ["X" "X" "O" nil nil nil nil nil nil]]
        (should-not (= 0 (gen/select-space test-generator board)))
        (should-not (= 1 (gen/select-space test-generator board)))
        (should-not (= 2 (gen/select-space test-generator board)))
        (should-not (= 3 (gen/select-space test-generator board)))
        (should-not (= 6 (gen/select-space test-generator board)))
        (should-not (= 7 (gen/select-space test-generator board)))
        (should-not (= 8 (gen/select-space test-generator board)))))))