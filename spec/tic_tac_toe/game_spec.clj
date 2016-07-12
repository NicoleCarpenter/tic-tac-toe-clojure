(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.human-move-generator :as human-move-generator]
            [tic-tac-toe.computer-move-generator :as computer-move-generator]
            [tic-tac-toe.mocks.mock-io :as mock-io]
            [tic-tac-toe.mocks.mock-user-interface :as mock-ui]))

(defn create-test-ui [mock-io]
  (mock-ui/create-mock-user-interface mock-io))

(describe "play-game"

  (it "displays a tie message when there is a tie"
    (let [mock-io (mock-io/mock-value "8")
          mock-value (mock-ui/mock-value (:value mock-io))
          test-ui (create-test-ui mock-io)
          board ["X" "X" "O" "O" "O" "X" "X" "O" nil]
          players [(player/create-player "Player 1" "X" (human-move-generator/create-human-move-generator test-ui))
                   (player/create-player "Computer" "O" (computer-move-generator/create-computer-move-generator "O"))]]
      (should= "Display tie message called" (game/play-game board players test-ui))))

  (it "displays a winning message when the player wins with the player's name"
    (let [mock-io (mock-io/mock-value "2")
          mock-value (mock-ui/mock-value (:value mock-io))
          test-ui (create-test-ui mock-io)
          board ["X" "X" nil "O" "O" nil nil nil nil]
          players [(player/create-player "Player 1" "X" (human-move-generator/create-human-move-generator test-ui))
                   (player/create-player "Computer" "O" (computer-move-generator/create-computer-move-generator "O"))]]
      (should= "Display winning message called with Player 1" (game/play-game board players test-ui))))


  (it "displays a winning message when the computer wins with the computer's name"
    (let [mock-io (mock-io/mock-value "6")
          mock-value (mock-ui/mock-value (:value mock-io))
          test-ui (create-test-ui mock-io)
          board ["X" "X" nil "O" "O" nil nil nil nil]
          players [(player/create-player "Player 1" "X" (human-move-generator/create-human-move-generator test-ui))
                   (player/create-player "Computer" "O" (computer-move-generator/create-computer-move-generator "O"))]]
      (should= "Display winning message called with Computer" (game/play-game board players test-ui)))))