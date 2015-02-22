; 2.3 Implement an algorithm to delete a node in the middle of a single linked
; list, given only access to that node.
;
; EXAMPLE
; Input: node ‘c’ of a->b->c->d->e
; Result: a->b->d->e

(ns cracking.p_02_03
  (:use [clojure.test])
  (:use [clojure.string :only (join)]))

; I'd solve this problem by copying the value of node-d to node-c and then
; update node-c (now node-d) to point to e. Only works if the node to be
; deleted is not the last node.
;
; This is kind of difficult to do without introducing state, so here's a
; more clojure-esque solution
(defn delete [c s]
  (join (filter #(not (= c %)) s)))

(deftest a-test
  (testing
    (is (= (delete \c "abcde") "abde"))))

