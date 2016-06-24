(ns tic-tac-toe.io
  (:require [tic-tac-toe.system-util :as system]))

(defrecord IO []
  system/SystemUtil
  (get-user-input [x]
    (read-line))

  (display [x output]
    (println output))

  (clear-scr [x]
    (let [esc (char 27)]
      (print (str esc "[2J"))
      (print (str esc "[;H")))))

(defn create-io []
  (map->IO {}))