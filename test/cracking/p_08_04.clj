; 8.4 Write a method to compute all permutations of a string.

(ns cracking.p_08_04
  (:use [clojure.test]))

(defn push "pushes element e into index i in collection c" [c i e]
  (let [[l r] (split-at i c)]
    (flatten (concat l [e] r))))

(defn inject
  "returns a list of collections with e pushed into every possible position in c"
  [c e]
  (map #(push c % e) (range (+ (count c) 1))))

; compute permutations of [x2..xn] and inject x1 into every possible position.
(defn perm [s]
  (if (empty? s)
    #{[]}
    (set (mapcat #(inject % (first s)) (perm (rest s))))))

(deftest a-test
  (testing
    (is (= (perm #{1 2 3}) #{[1 2 3] [2 1 3] [2 3 1] [1 3 2] [3 1 2] [3 2 1]}))))
