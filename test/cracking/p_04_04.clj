; 4.4 Given a binary search tree, design an algorithm which creates a linked
; list of all the nodes at each depth (i.e., if you have a tree with depth D,
; you’ll have D linked lists).

(ns cracking.p_04_04
  (:use [clojure.test]))

; BFS
; lazily enumerate all child nodes (rest) and put their values (first) together
; into lists.
(defn ll [n]
  (map #(map first %)
       (take-while not-empty
                   (iterate #(mapcat rest %) [n]))))

(deftest a-test
  (testing
    (is (= (ll [6 [3 [1] [5]] [13 [9] [18]]]) [[6] [3 13] [1 5 9 18]]))))
