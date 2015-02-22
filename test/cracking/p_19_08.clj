; 19.08 Design a method to find the frequency of occurrences of any given word
; in a book.

(ns cracking.p_19_08
  (:use [clojure.test]))

(defn occurences [w b]
  (count (filter #(= %1 w) b)))

(deftest a-test
  (testing
    (is (= (occurences "foo" ["foo" "bar" "baz" "foo"]) 2))))
