; 4.3 Given a sorted (increasing order) array, write an algorithm to create a
; binary tree with minimal height.

(ns cracking.p_04_03
  (:use [clojure.test]))

(defn node [v l r]
  (remove nil? (conj [v] l r)))

; Constructs a balanced binary tree by recursively splitting array in middle
; and using left and right half for left and right subtree, respectively.
(defn bt [a]
  (if (not-empty a)
    (let [split (int (/ (- (count a) 1) 2))
          v (nth a split)
          l (take split a)
          r (drop (+ split 1) a)]
      (node v (bt l) (bt r)))))

(deftest a-test
  (testing
    (is (= (bt [1]) [1]))
    (is (= (bt [1 3 5 6 9 13 18]) [6 [3 [1] [5]] [13 [9] [18]]]))
    (is (= (bt [1 3 5 6 9 13 18 21]) [6 [3 [1] [5]] [13 [9] [18 [21]]]]))))
