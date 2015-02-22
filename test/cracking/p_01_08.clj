; 1.8 Assume you have a method isSubstring which checks if one word is a
; substring of another Given two strings, s1 and s2, write code to check if s2
; is a rotation of s1 using only one call to isSubstring (i e , “waterbottle”
; is a rotation of “erbottlewat”)

(ns cracking.p_01_08
  (:use [clojure.test]))

; Concatenate s1 with itself and then check if s2 is a substring.
; Time: O(n), depending on String.contains implementation. Space: O(1)
(defn rotation? [s1 s2]
  (and (= (count s1) (count s2))
       (.contains (str s1 s1) s2)))

(deftest a-test
  (testing
    (is (= (rotation? "abc" "ab") false))
    (is (= (rotation? "abc" "abc") true))
    (is (= (rotation? "waterbottle" "erbottlewat") true))))
