; 4.7 You have two very large binary trees: T1, with millions of nodes, and T2,
; with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.

(ns cracking.p_04_07
  (:use [clojure.test])
  (:use [cracking.node]))

; this is a naive implementation that only considers the subsequence starting
; at the first match
(defn subseq? [s1 s2]
  (let [chopped (drop-while #(not= % (first s2)) s1)
        zipped (map vector chopped s2)]
        (and
          (= (count zipped) (count s2))
          (every? (fn [[x y]] (= x y)) zipped))))

(defn subtree? [n1 n2]
  (subseq? (in-order n1) (in-order n2)))

(def t1 [1 [2 [4] [5]] [3 [6] [7]]])

(def t2 [2 [4] [5]])

(deftest a-test
  (testing
    (is (= (subtree? t1 t1) true))
    (is (= (subtree? t1 t2) true))
    (is (= (subtree? t2 t1) false))))
