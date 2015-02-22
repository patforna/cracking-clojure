; 20.6 Describe an algorithm to find the largest 1 million numbers in 1 billion
; numbers. Assume that the computer memory can hold all one billion numbers.

(ns cracking.p_20_06
  (:use [clojure.test]))

(defn top [c n]
  (take-last n (sort c)))

(deftest a-test
  (testing
    (is (= (top [5 9 3 1 0 10] 3) [5 9 10]))))
