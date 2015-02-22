; 19.04

; Write a method which finds the maximum of two numbers. You should not use if-
; else or any other comparison operator.
; EXAMPLE Input: 5,10 Output: 10

(ns cracking.p_19_04
  (:use [clojure.test]))

(defn _max [a b]
  (last (sort [a b])))

(deftest a-test
  (testing
    (is (= (_max 5 10) 10))
    (is (= (_max -5 -10) -5))))
