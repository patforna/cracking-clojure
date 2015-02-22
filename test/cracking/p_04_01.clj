; 4.1 Implement a function to check if a tree is balanced. For the purposes of
; this question, a balanced tree is defined to be a tree such that no two leaf
; nodes differ in distance from the root by more than one.

(ns cracking.p_04_01
  (:use [clojure.test]))

; For Clojure DFS / BFS see
; http://stackoverflow.com/questions/11409140/stumped-with-functional-breadth-first-tree-traversal-in-clojure
(defn depths
  "performs a DFS and returns a list of numbers indicating how far the leaf
  nodes are away from the root"
  [[value left right] & [depth]]
  (let [depth (or depth 0)]
    (if-not value
      [depth]
      (concat (depths left (inc depth)) (depths right (inc depth))))))

; Compute depth of each leaf node and check if they differ by less than two.
(defn balanced? [tree]
  (let [d (depths tree)
        diff (- (apply max d) (apply min d))]
    (< diff 2)))

(deftest a-test
  (testing
    (is (= (balanced? '(:a (:b) (:c))) true))
    (is (= (balanced? '(:a (:b (:d) (:e)) (:c))) true))
    (is (= (balanced? '(:a (:b (:d (:f)) (:e)) (:c))) false))))
