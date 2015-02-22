; 1.2 Write code to reverse a C-Style String (C-String means that “abcd” is
; represented as five characters, including the null character )

(ns cracking.p_01_02
  (:use [clojure.test])
  (:use [clojure.string :only (join)]))

; Using recursion
; If string is length 1 or less, return as is. Else return last character
; followed by reversed rest of string.
; Time: O(n), Space: O(n)
(defn sol-1 [s]
  (if (<= (count s) 1)
    s
    (join (cons (last s) (sol-1 (butlast s))))))

(defn swap [v i1 i2]
  (assoc v i2 (v i1) i1 (v i2)))

; Iteratively
; Swap first and last element and then continue swapping as you move inward
; from both sides.
; Time: O(n), Space: O(1)
(defn sol-2 [s]
  (let [mid (int (/ (count s) 2))]
  (join (reduce #(swap %1 %2 (- (count s) 1 %2)) (vec s) (range mid)))))

(def solutions [sol-1 sol-2])

(defn all [input expected] (doseq [f solutions] (is (= (f input) expected))))

(deftest a-test
  (testing "reverse"
    (all "" "")
    (all "a" "a")
    (all "ab" "ba")
    (all "abcdefg" "gfedcba")))
