; 8.8 Write an algorithm to print all ways of arranging eight queens on a chess
; board so that none of them share the same row, column or diagonal.

(ns cracking.p_08_08
  (:use [clojure.test])
  (:use [cracking.util :only (in?)]))

; returns the colums in which a new queen can be added to the existing setup
(defn valid-cols [size qs]
  (let [row (count qs)
        du (map-indexed (fn [y x] (+ y x)) qs) ; diagonal up y-intersections
        dd (map-indexed (fn [y x] (- y x)) qs)] ; diagonal down y-intersections
    (remove #(or (in? qs %) (in? du (+ row %)) (in? dd (- row %))) (range size))))

; returns a list of lists, each describing the cols at which a queen can be placed.
;
; algorithm: pick all colums of first row, where a queen can be placed. then
; move to next row and return all combinations where the next queen can be
; added. repeat until the end of rows have been reached.
(defn queens [size]
  (loop [row 0 res [[]]]
    (if (>= row size)
      res
      (let [res' (mapcat (fn [qs] (map #(conj qs %) (valid-cols size qs))) res)]
      (recur (inc row) res')))))

(deftest a-test
  (testing
    (is (= (queens 4) [[1 3 0 2] [2 0 3 1]]))
    (is (= (count (queens 8)) 92))))
