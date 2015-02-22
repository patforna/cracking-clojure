; 1.6 Given an image represented by an NxN matrix, where each pixel in the
; image is 4 bytes, write a method to rotate the image by 90 degrees Can you do
; this in place?

(ns cracking.p_01_06
  (:use [clojure.test]))

; Rotates (x,y) by -90 and returns the value of the rotated position
(defn rotate-90 [x y m]
  (let [x' (- (count m) y 1) y' x] (get (get m x') y')))

; Create a new matrix, cell by cell (left to right, top to bottom), by copying
; cells from the old matrix.
(defn sol-1 [m]
  (map-indexed (fn [x row] (map-indexed (fn [y _] (rotate-90 x y m)) row)) m))

(def solutions [sol-1])

(defn all [input expected] (doseq [f solutions] (is (= (f input) expected))))

(deftest a-test
  (testing
    (all [[:a]] [[:a]])
    (all [[:a :b] [:c :d]] [[:c :a] [:d :b]])))
