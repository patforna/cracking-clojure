; 9.3 Given a sorted array of n integers that has been rotated an unknown
; number of times, give an O(log n) algorithm that finds an element in the
; array. You may assume that the array was originally sorted in increasing
; order.
;
; EXAMPLE: Input: find 5 in array (15 16 19 20 25 1 3 4 5 7 10 14)
; Output: 8 (the index of 5 in the array)

(ns cracking.p_09_03
  (:use [clojure.test]))

; finds the middle between left and right bound
(defn mid [l r]
  (int (+ l (/ (- r l) 2))))

; Finds the item of the smallest index in O(log n) time.
;
; Algorithm: initialise minimum to last item of coll. Then do a binary search
; through rest of collection. If we find a smaller value, we keep going left.
; If we encounter a larger value, we must have jumped over the rotation and
; therefore change direction, i.e. right. Repeat until no more items left.
;
; The binary search is done in place. We just keep track of a left and right
; bound and adjust the bounds accordingly when we go left or right.
;
; l: left bound (incl.) r: right bound (excl.) m: middle mn: minimum
(defn min-index [coll]
  (loop [mn (- (count coll) 1) l 0 r mn]
    (let [m (mid l r)]
      (if (zero? (- r l))
        mn
        (if (<= (coll m) (coll mn))
          (recur m l m)
          (recur mn (inc m) r))))))

; Finds item x in coll which is sorted but offset (rotated) by o in O(log n) time.
;
; This is essentially a binary search with a slight modification, which is to
; offset the index when comparing.
(defn binary-find [x o coll]
  (loop [l 0 r (count coll)]
    (let [m (mid l r) om (mod (+ m o) (count coll))]
      (if (zero? (- r l))
        nil
        (if (= x (coll om))
          om
          (if (< x (coll om))
            (recur l m)
            (recur (inc m) r)))))))

; Algorithm: Find offset (O log n), then use offset to perform a slightly
; modified binary search (O log n).
(defn index-of [x coll]
  (binary-find x (min-index coll) coll))

(deftest a-test
  (testing
    (is (= (min-index [0 1 2]) 0))
    (is (= (min-index [1 0 2]) 1))
    (is (= (min-index [1 2 0]) 2))
    (is (= (min-index [1 2 0 0]) 2))

    (is (= (index-of 1 [1]) 0))
    (is (= (index-of 5 [15 16 19 20 25 1 3 4 5 7 10 14]) 8))
    (is (= (index-of 15 [15 16 19 20 25 1 3 4 5 7 10 14]) 0))))
