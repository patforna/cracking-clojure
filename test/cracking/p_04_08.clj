; 4.8 You are given a binary tree in which each node contains a value. Design
; an algorithm to print all paths which sum up to that value. Note that it can
; be any path in the tree - it does not have to start at the root.

(ns cracking.p_04_08
  (:use [clojure.test]))

; returns a sequence of all possible paths from root to leaves
; example: ((1 2 4) (1 2 5) (1 3 6 -1) ...)
(defn paths [[v l r]]
  (if v
    (if (or l r)
      (map #(cons v %) (concat (paths l) (paths r)))
      [[v]])))

; returns a sequence of coll, (rest coll), (rest (rest coll)), etc.
; example: (1 2 3) -> ((1 2 3) (2 3) (3))
(defn steps [coll]
  (take-while not-empty (iterate rest coll)))

; Generates paths starting anywhere and ending at leaf. Then filters out the
; ones that add up to the desired sum and returns them.
;
; Note: if paths not stopping at a leaf should also be considered, then we'd
; have to modify the steps function to return more possible paths.
(defn sums-to [n sum]
  (let [paths (mapcat steps (paths n))
        sums (map #(reduce + %) paths)
        matches (filter (fn [[_ s]] (= s sum)) (map vector paths sums))]
    (map first matches)))

(def t1 [1 [2 [4] [5]] [3 [6 [-1]] [7 [-2]]]])

(deftest a-test
  (testing
    (is (= (sums-to t1 8) [[1 2 5] [3 6 -1] [3 7 -2]]))))
