; 9.5 Given a sorted array of strings which is interspersed with empty strings,
; write a method to find the location of a given string.
;
; Example: find “ball” in [“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”, “”, “dad”, “”, “”]
; will return 4
;
; Example: find “ballcar” in [“at”, “”, “”, “”, “”, “ball”, “car”, “”, “”, “dad”, “”, “”]
; will return -1

(ns cracking.p_09_05
  (:use [clojure.test]))

; Finds x in coll, where coll is a collection of [index, value] tuples.
;
; Algorithm: do a binary search with the following modification: if pivot is
; empty string, keep going right until non-empty element and proceed as normal.
; If there are no non-empty elements remaining on the right, proceed left.
;
; l: left , m: middle, r: right, rst: m + r, i: index, v: value
(defn find-index [x coll]
  (if (seq coll)
    (let [[l rst] (split-at (int (/ (count coll) 2)) coll)
          [m & r] (drop-while (fn [[_ v]] (empty? v)) rst)
          [i v] m]
      (cond
        (= x v) i
        (or (nil? v) (neg? (compare x v))) (find-index x l)
        :else (find-index x r)))))

; Dealing with indexes feels clumsy in Clojure. To make things a bit more
; natural, this function zips up the values in coll as [index value] tuples.
(defn find-loc [x coll]
  (find-index x (map-indexed #(vec [%1 %2]) coll)))

(deftest a-test
  (testing
    (is (= (find-loc "a" ["a" "b" "c"]) 0))
    (is (= (find-loc "b" ["a" "b" "c"]) 1))
    (is (= (find-loc "c" ["a" "b" "c"]) 2))
    (is (= (find-loc "d" ["a" "b" "c"]) nil))
    (is (= (find-loc "b" ["a" "" "" "" "b" "" "" "c" "" ""]) 4))))
