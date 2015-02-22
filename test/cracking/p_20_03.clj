; 20.3 Write a method to randomly generate a set of m integers from an array of
; size n. Each element must have equal probability of being chosen.

(ns cracking.p_20_03
  (:use [clojure.test])
  (:use [cracking.util :only (swap roughly? remove-at)]))

; We could have implemented this in the same way as 20.2. To spice things up a
; bit, here's an alternative:
;
; * randomly pick/remove an element from c
; * repeat until we've got <size> elements
; * sort before returning, because we don't care about order
(defn pick-rand [c size]
  (sort
    (loop [res [] c c]
      (if (= (count res) size)
        res
        (let [r (rand-int (count c))]
          (recur (conj res (c r)) (remove-at c r)))))))

(deftest a-test
  (testing
    (let [nbr 10000 samples (take nbr (repeatedly #(pick-rand [1 2 3] 2)))]
      (doseq [[_ counts] (frequencies samples)]
        (is (roughly? (double (/ nbr counts)) 3 0.5))))))
