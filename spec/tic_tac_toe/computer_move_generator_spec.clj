(ns tic-tac-toe.computer-move-generator-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.move-generator :as gen]
            [tic-tac-toe.computer-move-generator :as computer-gen]
            [tic-tac-toe.mocks.mock-io :as io]
            [tic-tac-toe.mocks.mock-user-interface :as ui]))

(defn- test-computer-move-generator [io]
  (computer-gen/create-computer-move-generator io))

(describe "select-space"

  (it "should return a valid space"
    (let [mock-io (io/mock-value "1")
          test-generator (test-computer-move-generator mock-io)
          board [\X nil \O nil nil \O nil \X nil]]
      (gen/select-space test-generator board)
      (should= "1" 
               @(:value mock-io)))))