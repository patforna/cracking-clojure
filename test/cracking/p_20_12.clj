; 20.12 Given an NxN matrix of positive and negative integers, write code
; to find the sub- matrix with the largest possible sum.

(ns cracking.p_20_12
  (:use [clojure.test])
  (:use [cracking.util :only(indices)]))

; returns a sequence of tuples, which in turn describe the start (top left) and
; end (bottom right) indices that can be formed
(defn sub-indices [m]
  (remove #(= [[0 0] [(dec (count m)) (dec (count m))]] %)
          (for [start (indices m) end (indices m)
                :let [[sx sy] start [ex ey] end]
                :when (and (<= sx ex) (<= sy ey))]
            [start end])))

(defn sum [m [[sx sy] [ex ey]]]
  (reduce + (mapcat #(subvec % sx (inc ex)) (subvec m sy (inc ey)))))

(defn max-subm [m]
  (apply max (map (partial sum m) (sub-indices m))))

(deftest a-test
  (testing
    (is (= (max-subm [[1 5 6]
                      [3 1 -5]
                      [4 2 2]]) 16))))
