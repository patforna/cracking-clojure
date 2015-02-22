; 3.3 Imagine a (literal) stack of plates. If the stack gets too high, it might
; topple. Therefore, in real life, we would likely start a new stack when the
; previous stack exceeds some threshold. Implement a data structure SetOfStacks
; that mimics this. SetOfStacks should be composed of several stacks, and
; should create a new stack once the previous one exceeds capacity.
; SetOfStacks.push() and SetOfStacks.pop() should behave identically to a
; single stack (that is, pop() should return the same values as it would if
; there were just a single stack).
;
; FOLLOW UP Implement a function popAt(int index) which performs a pop
; operation on a specific sub-stack.)

(ns cracking.p_03_03
  (:use [clojure.test]))

(defn set-count [s]
  (reduce #(+ %1 (count %2)) 0 (s :stacks)))

(defn set-curr-index [s]
  (int (/ (- (set-count s) 1) (s :size))))

(defn set-next-index [s]
  (int (/ (set-count s) (s :size))))

(defn set-create "create a set of n stacks of size s." [n s]
  {:size s :stacks (vec (repeat n []))})

(defn set-push [s i]
  (update-in s [:stacks (set-next-index s)] #(conj % i)))

(defn set-peek-at [s index]
  (peek (get-in s [:stacks index])))

(defn set-peek [s]
  (set-peek-at s (set-curr-index s)))

(def stack (reduce #(set-push %1 %2) (set-create 3 2) [1 2 3 4 5 6]))

(deftest a-test
  (testing
    (is (= (set-peek stack) 6))
    (is (= (set-peek-at stack 1) 4))))
