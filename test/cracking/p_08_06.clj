; 8.6 Implement the “paint fill” function that one might see on many image
; editing programs. That is, given a screen (represented by a 2 dimensional
; array of Colors), a point, and a new color, fill in the surrounding area
; until you hit a border of that color.’

(ns cracking.p_08_06
  (:use [clojure.test]))

(defn all-neighbours [[x y]]
  [[(+ x 1) y] [(- x 1) y] [x (+ y 1)] [x (- y 1)]])

; returns the neighbours of point [x, y] with color c.
(defn neighbours [s p c]
  (filter #(= (get-in s %) c) (all-neighbours p)))

; This is essentially a BFS graph traversal.
; Paint first point. Then get its neighbours (children) that still need to be
; painted (i.e. still using old color) and repeat.
; s: screen, p: point, c: new colour, o: old colour
(defn fill [s p c]
  (let [o (get-in s p)]
    (loop [s s ps #{p}]
      (if (empty? ps)
        s
        (let [s' (reduce #(assoc-in %1 %2 c) s ps)
              ps' (set (mapcat #(neighbours s' % o) ps))]
          (recur s' ps'))))))

; This uses recursion (DFS) instead. Might be a bit simpler and more elegant,
; but will blow the stack quite quickly.
(defn fill-dfs [s p c]
  (letfn [(dfs [s p o c]
            (if-not (= (get-in s p) o)
              s
              (reduce #(dfs %1 %2 o c) (assoc-in s p c) (all-neighbours p))))]
    (dfs s p (get-in s p) c)))

(def before [[0 0 0 1 1]
             [0 0 0 0 1]
             [0 0 0 0 0]
             [1 0 0 0 0]
             [1 1 0 0 0]])

(def after [[2 2 2 1 1]
            [2 2 2 2 1]
            [2 2 2 2 2]
            [1 2 2 2 2]
            [1 1 2 2 2]])

(deftest a-test
  (testing
    (is (= (fill before [3 3] 2) after))
    (is (= (fill-dfs before [3 3] 2) after))))
