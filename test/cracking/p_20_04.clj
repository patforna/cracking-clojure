; 20.4 Write a method to count the number of 2s between 0 and n.

(ns cracking.p_20_04
  (:use [clojure.test])
  (:use [cracking.util :only (pow digits limit drop-leading)]))

; computes how many "full cycles" of twos we had in the previous base.
; For example, for 42, we'd have (prev 4 1) = 4 * 10^0 = 4
(defn prev [d b]
  (* d (int (pow (dec b)))))

; computes how many two's we have in the current base.
; For example, for (curr 2 1 125), we'd have 25 - 20 + 1 = 6
(defn curr [d b n]
  (let [masked (drop-leading (inc b) n)]
    (limit (inc (- masked (* 2 (pow b)))), 0, (pow b))))

; for each (digit, base) compute previous and current twos and return sum.
(defn count-twos [n]
  (reduce + (map-indexed #(+ (prev %2 %1) (curr %2 %1 n)) (reverse (digits n)))))

(deftest a-test
  (testing
    (is (= (count-twos 1) 0))
    (is (= (count-twos 2) 1))
    (is (= (count-twos 3) 1))
    (is (= (count-twos 10) 1))
    (is (= (count-twos 11) 1))
    (is (= (count-twos 12) 2))
    (is (= (count-twos 19) 2))
    (is (= (count-twos 20) 3))
    (is (= (count-twos 21) 4))
    (is (= (count-twos 22) 6))
    (is (= (count-twos 30) 13))
    (is (= (count-twos 40) 14))
    (is (= (count-twos 214) 37))
    (is (= (count-twos 324) 138))))
