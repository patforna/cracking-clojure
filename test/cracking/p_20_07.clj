; 20.7 Write a program to find the longest word made of other words in a list of words.
;
; EXAMPLE
; (test, tester, testertest, testing, testingtester) => testingtester

(ns cracking.p_20_07
  (:use [clojure.test])
  (:use [cracking.util :only (in?)]))

; splits a word in all possible ways.
; e.g. (splits "foot") = ("f" "oot") ("fo" "ot") ("foo" "t")
(defn splits [w]
  (let [splits (map #(split-at % w) (range 1 (count w)))]
    (map (fn [x] (map #(apply str %) x)) splits)))

; sort by longest first
; for each word, compute splits and check if both parts exist
; return first one found (which will be longest, because we sorted)
(defn longest [ws]
  (let [composite? (fn [[w1 w2]] (and (in? ws w1) (in? ws w2)))]
    (loop [ws (reverse (sort-by count ws))]
      (let [w (first ws)]
        (if (seq (filter composite? (splits w))) w (recur (rest ws)))))))

(deftest a-test
  (testing
    (is (= (longest ["test", "tester", "testertest", "testing", "testingtester"]) "testingtester"))))
