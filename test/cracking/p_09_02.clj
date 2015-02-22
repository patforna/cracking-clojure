; 9.2 Write a method to sort an array of strings so that all the anagrams are
; next to each other.

(ns cracking.p_09_02
  (:use [clojure.test]))

; sorts the list by sorting each word (anagrams are identical if sorted).
(defn sort-a [c]
  (sort-by #(apply str (sort %)) c))

; TODO implement sort function

(deftest a-test
  (testing
    (is (= (sort-a ["bar" "baz" "rab" "zab" "foo"]) ["bar" "rab" "baz" "zab" "foo"]))))
