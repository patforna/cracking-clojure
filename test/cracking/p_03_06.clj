; 3.6 Write a program to sort a stack in ascending order. You should not make
; any assumptions about how the stack is implemented. The following are the
; only functions that should be used to write this program: push | pop | peek |
; isEmpty.

(ns cracking.p_03_06
  (:use [clojure.test]))

(defn push [s x] (conj s x))

(defn can-move? [s1 s2] (or (empty? s2) (<= (peek s1) (peek s2))))

; Use an additional stack s2 to sort stack s1 as follows:
; If top of s1 is smaller than top of s2, pop element from s1 onto s2
; If top of s2 is larger than top os s2, pop element x from s1, then pop
; elements from s2 onto s1 until x is smaller than top of s2, then push x to s2.
; Return when no more elements are left in s1.
; Time O(n^2) Space O(n)
(defn sort-asc [s]
  (loop [s1 s s2 []]
    (if (empty? s1)
      s2
      (let [popping (iterate pop s2)
            cant-move #(not (can-move? s1 %))
            s1' (reduce #(push %1 %2) (pop s1) (map peek (take-while cant-move popping)))
            s2' (push (first (drop-while cant-move popping)) (peek s1))]
        (recur s1' s2'))
      )
    )
  )

(deftest a-test
  (testing
    (is (= (sort-asc [5 4 5 1 2 3]) [5 5 4 3 2 1]))))
