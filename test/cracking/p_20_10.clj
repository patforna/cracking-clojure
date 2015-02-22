; 20.10 Given two words of equal length that are in a dictionary, write a
; method to transform one word into another word by changing only one letter at
; a time. The new word you get in each step must be in the dictionary.
;
; Example:
; Input: DAMP, LIKE Output: DAMP -> LAMP -> LIMP -> LIME -> LIKE

(ns cracking.p_20_10
  (:use [clojure.test])
  (:use [cracking.util :only (in? char-range)]))

(def letters (char-range \A \Z))

(def dictionary (shuffle ["CAMP" "DAMP" "LAMP" "LIMP" "LIME" "LIKE"]))

(defn neighbours [word]
  (filter #(in? dictionary %)
          (mapcat (fn [index]
                    (let [[l [c & r]] (split-at index word)]
                      (map (fn [letter] (apply str (concat l [letter] r)))
                           (remove #(= c %) letters))))
                  (range (count word)))))

(defn find-path [start target]
  (loop [paths [[start]]] ; TODO keep track of visited
    ; (println "paths: " paths)
    (if (seq paths)
      (let [complete (filter #(= (last %) target) paths)]
        (if (seq complete)
          (first complete)
          (let [paths (reduce
                        (fn [paths path]
                          ; (println "neibs: " (neighbours (last path)))
                          (let [neighbours (remove #(in? path %) (neighbours (last path)))
                                new-paths (map #(conj path %) neighbours)]
                            (if (seq new-paths) (concat paths new-paths) paths)))
                        [] paths)]
            (recur paths)))))))

(deftest a-test
  (testing
    (is (= (find-path "DAMP" "LIKE") ["DAMP" "LAMP" "LIMP" "LIME" "LIKE"]))))
