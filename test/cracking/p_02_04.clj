; 2.4 You have two numbers represented by a linked list, where each node
; contains a sin- gle digit. The digits are stored in reverse order, such that
; the 1’s digit is at the head of the list. Write a function that adds the two
; numbers and returns the sum as a linked list.
;
; Example: (3, 1, 5) + (5, 9, 2) => (8, 0, 8)

(ns cracking.p_02_04
  (:use [clojure.test])
  (:use [clojure.string :only (join)]))

; sums a and b and adds the result
(defn add-to-total [t [a b]]
  (let [sum (+ a b (last t))
        carry (int (/ sum 10))
        remain (mod sum 10)]
        (conj (vec (drop-last t)) remain carry)))

; strips leading zeroes (i.e. zeroes at end of list)
(defn strip [l]
  (reverse (drop-while #(zero? %) (reverse l))))

(defn add [a b]
  (let [zipped (map vector a b)]
    (strip (reduce add-to-total [0] zipped))))

(deftest a-test
  (testing
    (is (= (add [3 1 5] [5 9 2]) [8 0 8]))
    (is (= (add [4 1 5] [5 9 2]) [9 0 8]))
    (is (= (add [4 1 7] [5 9 2]) [9 0 0 1]))))

