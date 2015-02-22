; 20.5 You have a large text file containing words. Given any two words,
; find the shortest distance (in terms of number of words) between them in
; the file.

(ns cracking.p_20_05
  (:use [clojure.test])
  (:use [cracking.util :only (diff)]))

; returns a map of word-to-locations entries

; this would also work and only lib functions, but seems a bit more obscure
; (into {} (map (fn [[k v]] [k (map first v)]) (group-by second (map-indexed vector ws))))
(defn locations [ws]
  (reduce
    (fn [locs [i w]]
      (update-in locs [w] #(conj % i)))
    {} (map-indexed vector ws))
  )

; finds the minimum by comparing all elements in xs to all elements in ys using
; comparator function f.
(defn min-by [xs ys f]
  (reduce (fn [mn x]
            (min mn (apply min (map (fn [y] (f x y)) ys)))
            ) (f (first xs) (first ys)) xs))

; find locations where each word occurs
; then, find minimum difference between locations of two words.
(defn shortest [ws w1 w2]
  (let [locs (locations ws)]
    (min-by (locs w1) (locs w2) diff)))

(deftest a-test
  (testing
    (is (= (shortest ["a" "b" "c" "a" "d" "c"] "a" "c") 1))
    (is (= (shortest ["a" "b" "c" "a" "d" "c"] "d" "b") 3))))
