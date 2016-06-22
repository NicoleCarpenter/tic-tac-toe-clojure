(ns tic-tac-toe.user-interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.mocks.mock-io :as io]
            [tic-tac-toe.user-interface :as ui]))

(defn test-ui [mock-io]
  (ui/create-user-interface mock-io))

(describe "prompt-player-move"
  
  (it "promts a given player to make a move"
    (let [mock-io (io/mock-value "3") 
          test-ui (test-ui mock-io)]
      (view/prompt-player-move test-ui "Player 1")
      (should= "Player 1, select a position for your move: "
               @(:value mock-io)))))

(describe "get-player-move"
  
  (it "returns a valid move"
    (let [mock-io (io/mock-value "1")
          test-ui (test-ui mock-io)
          board [nil nil nil nil nil nil nil nil nil]]
      (view/get-player-move test-ui board)
      (should= "1"
               @(:value mock-io)))))

(describe "display-tie-message"
  
  (it "displays a tie message"
    (let [mock-io (io/mock-value "3") 
          test-ui (test-ui mock-io)]
      (view/display-tie-message test-ui)
      (should= "Game over. It's a tie."
                @(:value mock-io)))))

(describe "display-winning-message"
  
  (it "displays a winning message with the winner's name"
    (let [mock-io (io/mock-value "3") 
          test-ui (test-ui mock-io)]
      (view/display-winning-message test-ui "Nicole")
      (should= "Game over. Nicole won!"
                @(:value mock-io)))))

(run-specs)