(ns tic-tac-toe.human-move-generator-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.move-generator :as gen]
            [tic-tac-toe.human-move-generator :as human-gen]
            [tic-tac-toe.mocks.mock-io :as io]
            [tic-tac-toe.mocks.mock-user-interface :as ui]))

(defn- test-human-move-generator [mock-ui io]
  (human-gen/create-human-move-generator mock-ui io))

(describe "select-space"

  (it "should return a valid space"
    (let [mock-ui (ui/mock-value "2")
          mock-io (io/mock-value "1")
          test-generator (test-human-move-generator mock-ui mock-io)
          board [\X nil \O nil nil \O nil \X nil]]
      (gen/select-space test-generator board)
      (should= "2" 
               @(:value mock-ui)))))