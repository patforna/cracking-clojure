; 19.11 Design an algorithm to find all pairs of integers within an array which
; sum to a specified value.

(ns cracking.p_19_11
  (:use [clojure.test]))

; * create map m using ints in c as keys
; * then, for each item in c, check if (sum-c) is a key in m. If so, add it as
;   a pair
(defn pairs [c sum]
  (let [m (zipmap c c)]
    (reduce #(if (m (- sum %2)) (conj %1 [%2 (- sum %2)]) %1) [] c)))

(deftest a-test
  (testing
    (is (= (pairs [1 2 3 4 5] 7) [[2 5] [3 4] [4 3] [5 2]]))))
