; 9.1 You are given two sorted arrays, A and B, and A has a large enough buffer
; at the end to hold B. Write a method to merge B into A in sorted order.

(ns cracking.p_09_01
  (:use [clojure.test]))

(defn merge-sorted [a b]
    (cond (empty? a) b
          (empty? b) a
          (<= (first a) (first b)) (cons (first a) (merge-sorted (rest a) b))
          :else (cons (first b) (merge-sorted a (rest b)))))

(deftest a-test
  (testing
    (is (= (merge-sorted [] []) []))
    (is (= (merge-sorted [1] []) [1]))
    (is (= (merge-sorted [] [1]) [1]))
    (is (= (merge-sorted [2 5 7] [1 2 3]) [1 2 2 3 5 7]))))
