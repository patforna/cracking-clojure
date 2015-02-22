; 1.5 Write a method to replace all spaces in a string with ‘%20’

(ns cracking.p_01_05
  (:use [clojure.test])
  (:use [clojure.string :only (join)]))

; Copy each char of string into a new list, replacing spaces with "%20".
; Time O(n) Space: O(n)
(defn sol-1 [s]
  (join (map (fn [x] (if (= x \space) "%20" x)) s)))

(def solutions [sol-1])

(defn all [input expected] (doseq [f solutions] (is (= (f input) expected))))

(deftest a-test
  (testing
    (all "" "")
    (all "abc" "abc")
    (all "a b" "a%20b")))
