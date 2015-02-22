; 1.1 Implement an algorithm to determine if a string has all unique characters
; What if you can not use additional data structures?

(ns cracking.p_01_01
  (:use [clojure.test]))

; Using Set (non-lazy)
; Go through the entire string and add each character to a Set. Then compare
; the size of the Set with the length of the string to determine if there were
; any duplicate characters.
(defn sol-1 [s]
  (let [char-set (reduce #(conj %1 %2) #{} s)]
    (= (count char-set) (count s))))

; Using Set (lazy)
; Lazily generates a sequence of booleans indicating if a duplicate character
; had been found at a given index. Then the generation stops.
(defn sol-2
  ([s] (not-any? identity (sol-2 s #{})))
  ([s char-set]
   (if (empty? s)
     ()
     (lazy-seq (cons (contains? char-set (first s))
           (sol-2 (rest s) (conj char-set (first s))))))))

; Using no additional data structure
; Sorts the string and then recursively compares pairs of characters.
(defn sol-3 [s]
  (let [s (sort s) f (first s) r (rest s)]
    (if (nil? f) true
      (if (= f (first r)) false
        (recur r)))))

; Using no additional data structure
; Same as above but uses cond instead of if
(defn sol-4 [s]
  (let [s (sort s) f (first s) r (rest s)]
    (cond (nil? f) true
          (= f (first r)) false
          :else (recur r))))

(def solutions [sol-1 sol-2 sol-3 sol-4])

(defn all [input expected] (doseq [f solutions] (is (= (f input) expected))))

(deftest a-test
  (testing "if a string has all unique characters"
    (all nil true)
    (all "" true)
    (all "a" true)
    (all "ab" true)
    (all "abcdefg" true)
    (all "aa" false)
    (all "aba" false)))
