; 1.3 Design an algorithm and write code to remove the duplicate characters in
; a string without using any additional buffer NOTE: One or two additional
; variables are fine An extra copy of the array is not

(ns cracking.p_01_03
  (:use [clojure.test]))

; Iterate through string, skipping chars that already exist.
; Time: O(n^2) Space: O(n)
(defn sol-1 [s]
  (reduce (fn [xc c] (if (= (.indexOf xc (str c)) -1) (str xc c) xc)) "" s))

(def solutions [sol-1])

(defn all [input expected] (doseq [f solutions] (is (= (f input) expected))))

(deftest a-test
  (testing
    (all "" "")
    (all "a" "a")
    (all "ab" "ab")
    (all "aa" "a")
    (all "ababc" "abc")))
