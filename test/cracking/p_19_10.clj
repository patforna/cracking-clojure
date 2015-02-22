; 19.10 Write a method to generate a random number between 1 and 7, given a
; method that generates a random number between 1 and 5 (i.e., implement
; rand7() using rand5()).

(ns cracking.p_19_10
  (:use [clojure.test])
  (:use [cracking.util :only (roughly? avg)]))

; generates a random int between 1 and 5.
(defn rand5 []
  (inc (rand-int 5)))

; generates a random int between 1 and 25, using rand5.
(defn rand25 []
  (+ (* 5 (dec (rand5))) (rand5)))

; uses rand25 repeatedly until it gets a number in the desired range (i.e. 1-7)
(defn rand7 []
  (first (drop-while #(> %1 7) (repeatedly rand25))))

(deftest a-test
  (testing
    (is (roughly? (avg (take 10000 (repeatedly rand5))) 3 0.1))
    (is (roughly? (avg (take 10000 (repeatedly rand7))) 4 0.1))))
