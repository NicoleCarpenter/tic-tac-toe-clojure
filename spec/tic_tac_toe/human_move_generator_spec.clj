(ns tic-tac-toe.human-move-generator-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.move-generator :as gen]
            [tic-tac-toe.human-move-generator :as human-gen]
            [tic-tac-toe.mocks.mock-io :as io]
            [tic-tac-toe.mocks.mock-user-interface :as ui]))

(defn- test-human-move-generator [io]
  (human-gen/create-human-move-generator io))

(describe "select-space"

  (it "should return a valid space"
    (let [mock-io (io/mock-value "1")
          test-generator (test-human-move-generator mock-io)
          board [\X nil \O nil nil \O nil \X nil]]
      (gen/select-space test-generator board)
      (should= "1" 
               @(:value mock-io)))))