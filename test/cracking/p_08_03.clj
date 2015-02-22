; 8.3 Write a method that returns all subsets of a set.

(ns cracking.p_08_03
  (:use [clojure.test])
  (:use [clojure.set :only (union)]))

; if s is empty, return empty set else, remove one element e from s and return
; every combination of e and (s - r)
(defn subsets [s]
  (if (empty? s)
    #{#{}}
    (let [t (subsets (rest s))
          u (map #(conj % (first s)) t)]
      (union t u))))

(deftest a-test
  (testing
    (is (= (subsets #{}) #{#{}}))
    (is (= (count (subsets #{:a :b :c})) 8))
    (is (= (subsets #{:a :b :c}) #{ #{} #{:c} #{:b} #{:b :c} #{:a} #{:a :c} #{:a :b} #{:a :b :c} }))))

