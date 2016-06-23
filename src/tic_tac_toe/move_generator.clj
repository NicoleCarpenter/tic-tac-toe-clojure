(ns tic-tac-toe.move-generator)
  
  (defprotocol MoveGenerator
    (select-space [x board]))