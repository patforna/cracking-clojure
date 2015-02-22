; 19.07 You are given an array of integers (both positive and negative). Find
; the continuous sequence with the largest sum. Return the sum.
; EXAMPLE Input: {2, -8, 3, -2, 4, -10} Output: 5 (i.e., {3, -2, 4})

(ns cracking.p_19_07
  (:use [clojure.test]))

; Kadan's algorithm. See http://en.wikipedia.org/wiki/Maximum_subarray_problem
(defn max-sum [c]
  (apply max
         (reduce #(conj %1 (max 0 (+ (last %1) %2))) [0] c)))

(deftest a-test
  (testing
    (is (= (max-sum [2 -8 3 2 -4 -10]) 5))))
