; 20.2 Write a method to shuffle a deck of cards. It must be a perfect shuffle
; - in other words, each 52! permutations of the deck has to be equally likely.
; Assume that you are given a random number generator which is perfect.

(ns cracking.p_20_02
  (:use [clojure.test])
  (:use [cracking.util :only (swap roughly?)]))

; randomly select a number r between 0 and x (initially x is (count c))
; then swap positions of element at r with element at x (i.e. last element)
; then decrease value of x by one and repeat until no more elements left to swap.
(defn shuf [c]
  (reduce
    #(swap %1 (rand-int (inc %2)) %2)
    c (reverse (range (count c)))))

(deftest a-test
  (testing
    (let [nbr 10000 samples (take nbr (repeatedly #(shuf [1 2 3])))]
      (doseq [[_ counts] (frequencies samples)]
        (is (roughly? (double (/ nbr counts)) 6 0.5))))))
