; 4.2 Given a directed g, design an algorithm to find out whether there is
; a route between two nodes.

(ns cracking.p_04_02
  (:use [clojure.test])
  (:use [clojure.set :only (difference)])
  (:use [cracking.util :only (in?)]))

(defn reachable? [g from to & [visited]]
  (let [visit (difference (get g from) visited)]
    (reduce #(or %1 (reachable? g %2 to (conj visited from))) (in? visit to) visit)))

(deftest a-test
  (testing
    (is (= (reachable? {:x #{:y}} :x :y) true))

    (is (= (reachable? {:x #{:y}
                        :y #{:z}} :x :z) true))

    (is (= (reachable? {:x #{:a :b :c}
                        :b #{:z}} :x :z) true))

    (is (= (reachable? {:x #{:x :y}
                        :y #{:z}} :x :z) true))

    (is (= (reachable? {:x #{:z}} :x :y) false))))
