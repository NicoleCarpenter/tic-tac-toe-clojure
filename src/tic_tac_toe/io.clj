(ns tic-tac-toe.io
  (:require [tic-tac-toe.system-util :as system]))

(defrecord IO []
  system/SystemUtil
  (get-user-input [x]
    (read-line))

  (display [x output]
    (println output)))

(defn create-io []
  (map->IO {}))