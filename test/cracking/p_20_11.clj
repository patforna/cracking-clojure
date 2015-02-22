; 20.11 Imagine you have a square matrix, where each cell is filled with either
; black or white. Design an algorithm to find the maximum subsquare such that
; all four borders are filled with black pixels.

(ns cracking.p_20_11
  (:use [clojure.test])
  (:use [cracking.util :only (indices)]))

; checks if the square is black
(defn black? [v] (and v (zero? v)))

; returns the indices of the squares in m which are black
(defn blacks [m]
  (filter #(black? (get-in m %)) (indices m)))

; checks if [x, y] is the top left corner of a black-bordered square of size d.
(defn spans? [m [x y] d]
  (let [ds (range 1 (inc 2))
        t (map #(vec [(+ x %) y]) ds)
        b (map #(vec [(+ x %) (+ y d)]) ds)
        l (map #(vec [x (+ y %)]) ds)
        r (map #(vec [(+ x d) (+ y %)]) ds)
        border (set (concat t b l r))]
    (every? #(black? (get-in m %)) border)))

; returns the largest black-bordered suqare that can be drawn starting from sq.
(defn max-span [m sq]
  (inc (reduce #(if (spans? m sq %2) %2 %1) (range 1 (count m)))))

; * find all black squares
; * for each one, compute the largest black-bordered square that can be drawn
; * return the max found
(defn max-sq [m]
  (apply max (map (partial max-span m) (blacks m))))

(deftest a-test
  (testing
    (is (= (max-sq [[1 1 1 1]
                    [1 0 0 0]
                    [1 0 1 0]
                    [1 0 0 0]]) 3))))
