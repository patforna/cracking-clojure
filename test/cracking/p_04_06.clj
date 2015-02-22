; 4.6 Design an algorithm and write code to find the first common ancestor of
; two nodes in a binary tree. Avoid storing additional nodes in a data
; structure. NOTE: This is not necessarily a binary search tree.

(ns cracking.p_04_06
  (:use [clojure.test])
  (:use [cracking.node]))

; list all ancestors for node, then pair them up in reversed order and return
; the last common one before they diverge
(defn common-ancestor [r n m]
  (let [an (reverse (ancest r n))
        am (reverse (ancest r m))
        common (take-while (fn [[x y]] (= x y)) (map vector an am))]
    (first (last common))))

(def tree [1 [2 [4] [5]] [3 [6] [7]]])

(deftest a-test
  (testing
    (let [n (partial node tree)]
      (is (= (common-ancestor tree (n 2) (n 3)) (n 1)))
      (is (= (common-ancestor tree (n 6) (n 7)) (n 3)))
      (is (= (common-ancestor tree (n 6) (n 3)) (n 1)))
      (is (= (common-ancestor tree (n 6) (n 6)) (n 3))))))
