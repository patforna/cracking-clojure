; 1.7 Write an algorithm such that if an element in an MxN matrix is 0, its
; entire row and column is set to 0

(ns cracking.p_01_07
  (:use [clojure.test])
  (:use [cracking.util :only (indices)]))

; returns true if cell at location x, y is zero
(defn reset-cell? [m x y]
  (zero? (get-in m [x y])))

; tracks rows and cols to reset
(defn track [r x y]
  (-> r
      (update-in [:rows] #(conj % x))
      (update-in [:cols] #(conj % y))))

; returns the rows and columns that should be reset
(defn resets [m]
  (reduce (fn [r [x y]] (if (reset-cell? m x y) (track r x y) r))
          {:rows #{} :cols #{}}
          (indices m)))

; determines if the given cell is part of a row or column that should be reset
(defn reset? [{:keys [rows cols]} x y]
  (or (contains? rows x) (contains? cols y)))

; fills row with zeros
(defn reset-row [m row]
  (assoc m row (vec (replicate (count m) 0))))

; fills col with zeros
(defn reset-col [m col]
  (reduce #(assoc-in %1 [%2 col] 0) m (range (count m))))

; Go through each cell of matrix and record which rows and columns should be
; reset to zero. Then, go through the matrix again and make a copy, using zero
; for cells that should be reset.
; Time: O(n) Space: O(1)
(defn reset [m]
  (let [{:keys [rows cols]} (resets m)]
    (reduce reset-row (reduce reset-col m cols) rows)))

(deftest a-test
  (testing
    (is (= (reset [[1 0] [1 1]]) [[0 0] [1 0]]))
    (is (= (reset [[1 1 1] [1 0 1] [1 1 1]]) [[1 0 1] [0 0 0] [1 0 1]]))))
