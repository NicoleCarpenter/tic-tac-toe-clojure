(ns tic-tac-toe.ttt-board-presenter-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ttt-board-presenter :as presenter]))


(describe "format-board-to-string"

  (it "prints a board with positions"
    (let [board [1 2 3 4 5 6 7 8 9]]
      (should= " 1 | 2 | 3\n===+===+===\n 4 | 5 | 6\n===+===+===\n 7 | 8 | 9\n" (presenter/format-board-to-string board))))

  (it "prints a board with characters"
    (let [board [\X \O \X \O \X \O \X \O \X]]
      (should= " X | O | X\n===+===+===\n O | X | O\n===+===+===\n X | O | X\n" (presenter/format-board-to-string board))))

  (it "prints a board with empty spaces"
    (let [board [nil nil nil nil nil nil nil nil nil]]
      (should= "   |   |  \n===+===+===\n   |   |  \n===+===+===\n   |   |  \n" (presenter/format-board-to-string board))))

  (it "prints a board with characters and empty spaces"
    (let [board [\X \O nil nil \X nil nil \O \X]]
      (should= " X | O |  \n===+===+===\n   | X |  \n===+===+===\n   | O | X\n" (presenter/format-board-to-string board)))))
