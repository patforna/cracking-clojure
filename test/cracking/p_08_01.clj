; 8.1 Write a method to generate the nth Fibonacci number.

(ns cracking.p_08_01
  (:use [clojure.test]))

(defn fib-1 [n]
  (if (< n 2)
    1
    (+ (fib-1 (- n 1)) (fib-1 (- n 2)))))

(defn fib-2 [n]
  (if (< n 2)
    1
    (loop [i 2 l 1 m 1]
      (if (= i n)
        (+ l m)
        (recur (inc i) m (+ l m))))))

; This is probably the most idiomatic solution.
; It lazily generates pairs of previous fib numbers, which can be used to
; calculate the next number.
(defn fib-seq []
    (map first (iterate (fn [[l m]] [m (+ l m)]) [1 1])))

(deftest a-test
  (testing
    (is (= (map fib-1 (range 9)) [1 1 2 3 5 8 13 21 34]))
    (is (= (map fib-2 (range 9)) [1 1 2 3 5 8 13 21 34]))
    (is (= (take 9 (fib-seq)) [1 1 2 3 5 8 13 21 34]))))
