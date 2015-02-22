; 1.4 Write a method to decide if two strings are anagrams or not.
;
; Note: An anagram is a type of word play, the result of rearranging the
; letters of a word or phrase to produce a new word or phrase, using all the
; original letters exactly once.

(ns cracking.p_01_04
  (:use [clojure.test]))

; Sort both strings and then compare them.
; Time: O(n * log(n)) Space: O(1)
(defn sol-1 [a b] (= (sort a) (sort b)))

(def solutions [sol-1])

(defn all [a b expected] (doseq [f solutions] (is (= (f a b) expected))))

(deftest a-test
  (testing "anagrams"
    (all "" "" true)
    (all "a" "a" true)
    (all "aaa" "aaa" true)
    (all "abc" "abc" true)
    (all "abc" "acb" true)
    (all "abc" "bac" true)
    (all "abc" "bca" true)
    (all "abc" "cab" true)
    (all "abc" "cba" true)
    (all "mary" "army" true)
    (all "army" "mary" true)
    (all "abc" "ab" false)
    (all "abc" "abb" false)))
