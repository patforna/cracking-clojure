; 4.5 Write an algorithm to find the ‘next’ node (i.e., in-order successor) of
; a given node in a binary search tree where each node has a link to its
; parent.

(ns cracking.p_04_05
  (:use [clojure.test])
  (:use [cracking.node]))

; -----------------------------------------------------------------------------
; SOLUTION 1:
; This is probably more idiomatic clojure, much more elegant, but not taking
; advantage of (parent) and therefore slower to compute
; -----------------------------------------------------------------------------
; Enumerate in-order sequence starting at root r and return element following n.
(defn successor-1 [r n]
  (second (drop-while #(not= % n) (in-order r))))

; -----------------------------------------------------------------------------
; SOLUTION 2:
; This is probably more in the spirit of the exercise, more time efficient but
; also much more verbose.
; -----------------------------------------------------------------------------

; Find the successor of node with value v in tree r
;
; if right subtree -> next in-order
; if no right subtree, "climb up" until on left branch and return parent
(defn successor-2 [r v]
  (let [n (node r v)]
    (if (right n)
      (first (in-order (right n)))
      (let [left-child (drop-while (partial right? r) (iterate #(parent r %) n))]
        (value (parent r (first left-child)))))))

; -----------------------------------------------------------------------------
; TESTS
; -----------------------------------------------------------------------------
(deftest a-test
  (testing "solution 1"
    ; balanced, complete
    (is (= (successor-1 [1 [2] [3]] 2) 1))
    (is (= (successor-1 [1 [2] [3]] 1) 3))
    (is (= (successor-1 [1 [2] [3]] 3) nil))

    ; only right branches
    (is (= (successor-1 [1 nil [3 nil [7]]] 1) 3))
    (is (= (successor-1 [1 nil [3 nil [7]]] 3) 7))
    (is (= (successor-1 [1 nil [3 nil [7]]] 7) nil))

    ; only left branches
    (is (= (successor-1 [1 [2 [4]]] 4) 2))
    (is (= (successor-1 [1 [2 [4]]] 2) 1))
    (is (= (successor-1 [1 [2 [4]]] 1) nil)))

  (testing "solution 2"
    (is (= (successor-2 [1 [2] [3]] 2) 1))
    (is (= (successor-2 [1 [2] [3]] 1) 3))
    (is (= (successor-2 [1 [2] [3]] 3) nil))

    (is (= (successor-2 [1 nil [3 nil [7]]] 1) 3))
    (is (= (successor-2 [1 nil [3 nil [7]]] 3) 7))
    (is (= (successor-2 [1 nil [3 nil [7]]] 7) nil))

    (is (= (successor-2 [1 [2 [4]]] 4) 2))
    (is (= (successor-2 [1 [2 [4]]] 2) 1))
    (is (= (successor-2 [1 [2 [4]]] 1) nil))))
