(ns tic-tac-toe.player)

(defrecord Player [name marker move-generator])

(defn create-player [name marker move-generator]
  (map->Player {:name name :marker marker :move-generator move-generator}))