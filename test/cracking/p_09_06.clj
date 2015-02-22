; 9.6 Given a matrix in which each row and each column is sorted, write a
; method to find an element in it.

(ns cracking.p_09_06
  (:use [clojure.test]))

; Given: matrix with m rows and n columns
; 1) Find rows where x is larger than first and smaller than last element O(log m)
; 2) For each row (worst case: m), do a binary search
;
; Time: O(log m + m * log n) = O(m * log n)
;
; Note: the implementation below is actually O(m) for step 1. For step 2, it
; uses a library function, instead of implementing a binary search (again).
(defn find-1 [x m]
  (let [rows (filter #(<= (first %) x (last %)) m)]
    (loop [[row & _] rows]
      (if (seq row)
        (or (some #{x} row) (recur _))))))

; This solution is more elegant and faster. O(m + n)
;
; Start in the top right corner:
;   If x = current cell, we're done.
;   If x > current cell, move down (because x can't be in current row)
;   If x < current cell, move left (because x can't be in current column)
(defn find-2 [x m]
  (let [c (last (first m))]
    (if c
      (cond
        (= x c) c
        (> x c) (recur x (rest m))
        :else (recur x (map butlast m))))))

(def m [[1 3 5]
        [2 4 7]
        [6 8 9]])

(deftest a-test
  (testing
    (is (= (find-1 -1 m) nil))
    (is (= (find-1 1 m) 1))
    (is (= (find-1 5 m) 5))
    (is (= (find-1 4 m) 4))
    (is (= (find-1 6 m) 6))

    (is (= (find-2 -1 m) nil))
    (is (= (find-2 1 m) 1))
    (is (= (find-2 5 m) 5))
    (is (= (find-2 4 m) 4))
    (is (= (find-2 6 m) 6))))
