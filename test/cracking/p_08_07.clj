; 8.7 Given an infinite number of quarters (25 cents), dimes (10 cents),
; nickels (5 cents) and pennies (1 cent), write code to calculate the number of
; ways of representing a cents.

(ns cracking.p_08_07
  (:use [clojure.test]))

; returns all coins that dont exceed amount a
(defn coins [a]
  (filter #(>= (/ a %) 1) [25 10 5 1]))

; picks one of each kind of coin that doesn't exceed amount a and combines them
; with all the stacks that can be built with the remaining amount.
; a: amount, c: coin, s: stack (of coins)
(defn stacks [a]
  (if (zero? a)
    [[]]
    (mapcat (fn [c] (map (fn [s] (cons c s)) (stacks (- a c)))) (coins a))))

(deftest a-test
  (testing
    (is (= (stacks 0) [[]]))
    (is (= (stacks 1) [[1]]))
    (is (= (stacks 5) [[5] [1 1 1 1 1]]))))
