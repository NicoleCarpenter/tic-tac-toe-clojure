(ns tic-tac-toe.move-validator-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.move-validator :as validator]))

(describe "is-valid?"

  (it "returns true if the move is valid"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= true (validator/is-valid? "1" active-board))))

  (it "returns false if the move is a fraction"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "1/2" active-board))))

  (it "returns false if the move is a decimal"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "1.5" active-board))))

  (it "returns false if the move is a negative number"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "-1" active-board))))

  (it "returns false if the move is greater than the board size"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "10" active-board))))

  (it "returns false if the move is a character"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "A" active-board))))

  (it "returns false if the move is a string"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "ABC" active-board))))

  (it "returns false if the move is a special character"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "!" active-board))))

  (it "returns false if the move is a carraige return"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "" active-board))))

  (it "returns false if the move is an empty space"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? " " active-board))))

  (it "returns false if the move is a series of empty spaces"
    (let [active-board [nil nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "   " active-board))))

  (it "returns false if the move is already taken"
    (let [active-board [\X nil nil nil nil nil nil nil nil]]
      (should= false (validator/is-valid? "0" active-board)))))