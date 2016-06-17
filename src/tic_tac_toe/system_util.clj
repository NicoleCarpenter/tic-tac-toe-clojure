(ns tic-tac-toe.system-util)

  (defprotocol SystemUtil
    (get-user-input [x])
    (display [x output]))