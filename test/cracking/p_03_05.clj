; 3.5 Implement a MyQueue class which implements a queue using two stacks.

(ns cracking.p_03_05
  (:use [clojure.test]))

(defn de-stack
  "copies stack to another stack by successively calling pop (thereby reversing order)"
  [s]
  (vec (map peek (take-while not-empty (iterate pop s)))))

; Algorithm: Pop all elements off s1 and onto s2. Pop top element off of s2 and
; then pop all remaining elments "back onto s1" (actually: a new stack).
(defn deq [s]
  (de-stack (pop (de-stack s))))

(deftest a-test
  (testing
    (is (= (deq [:x :y :z]) [:y :z]))
    (is (= (deq [:y :z]) [:z]))))
