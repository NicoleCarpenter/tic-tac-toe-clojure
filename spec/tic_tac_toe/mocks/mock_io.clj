(ns tic-tac-toe.mocks.mock-io
  (:require [tic-tac-toe.system-util :as system]))

(defrecord MockIO [value]
  system/SystemUtil
  (get-user-input [x]
    @value)

  (display [x s]
    (reset! value s)
    ))

(defn mock-value [input]
  (->MockIO (atom input)))

(defn create-mock-io []
  (->MockIO))