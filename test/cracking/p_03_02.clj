; 3.2 How would you design a stack which, in addition to push and pop, also has
; a function min which returns the minimum element? Push, pop and min should
; all operate in O(1) time.

(ns cracking.p_03_02
  (:use [clojure.test]))

(defn get-or-zero [f]
  ((fnil identity 0) f))

(defn peek-item [s]
  (first (peek s)))

(defn push-item [s i]
  (let [min-item (min i (get-or-zero (peek-item s)))]
    (conj s [i min-item])))

(defn pop-item [s]
  (first (pop s)))

; Keeps track of minimum by wrapping elements together with current minimum.
; Time O(1)
(defn min-item [s] (second (peek s)))

(def stack (reduce #(push-item %1 %2) [] [3 4 5 2 6]))

(deftest a-test
  (testing
    (is (= (min-item stack) 2))))
