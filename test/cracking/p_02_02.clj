; 2.3 Implement an algorithm to find the nth to last element of a singly
; linked list.

(ns cracking.p_02_02
  (:use [clojure.test]))

; Only using LinkedList API, i.e. first, next.

; The algorithm works by maintaining two pointers: p1 to a current element and
; another one, p2, that is index steps ahead. We then advance both pointers
; simultaneously. If p2 reaches the end of the list, we return the value that
; p1 points at.
(defn find-nth-last [s index]
  (let [s2 (drop (+ index 1) s)]
    (first (reduce (fn [res _] (next res)) s s2))))

(deftest a-test
  (testing
    (is (= (find-nth-last "abc" 0) \c))
    (is (= (find-nth-last "abc" 1) \b))
    (is (= (find-nth-last "abc" 2) \a))))

