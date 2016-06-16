(ns tic-tac-toe.io-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.io :refer :all]))

(describe "get-user-input"
  (around [it]
    (with-out-str (it)))

  (it "tests the input of display"
    (should= "Hello"
      (with-in-str "Hello"
        (get-user-input "Say Hello")))))

(describe "display"
  (around [it]
    (with-out-str (it)))

  (it "tests the output of display"
    (should= "Hello World\n"
      (with-out-str "Hello World"
        (display "Hello World")))))

(run-specs)