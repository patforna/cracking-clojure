; 2.1 Write code to remove duplicates from an unsorted linked list. FOLLOW UP
; How would you solve this problem if a temporary buffer is not allowed?

(ns cracking.p_02_01
  (:use [clojure.test]))

; Instead of implementing our own LinkedList, we just pretend we've got one
; by only using the API of a LinkedList, i.e. first, next
;
; Iterate through string front to back, for every character, check if we've
; already added it, and skip, if so.
; Time: O(n^2) Space: O(1)
(defn de-dupe [s]
  (reduce
    (fn [res c] (if (some #{c} res) res (str res c))) "" s))

(deftest a-test
  (testing
    (is (= (de-dupe "aa") "a"))
    (is (= (de-dupe "aba") "ab"))))
