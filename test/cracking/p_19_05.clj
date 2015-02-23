; 19.5 The Game of Master Mind is played as follows:
; The computer has four slots containing balls that are red (R), yellow (Y),
; green (G) or blue (B). For example, the computer might have RGGB (e.g., Slot
; #1 is red, Slots #2 and #3 are green, Slot #4 is blue).
;
; You, the user, are trying to guess the solution. You might, for example,
; guess YRGB.
;
; When you guess the correct color for the correct slot, you get a “hit”. If
; you guess a color that exists but is in the wrong slot, you get a
; “pseudo-hit”. For example, the guess YRGB has 2 hits and one pseudo hit.
;
; For each guess, you are told the number of hits and pseudo-hits.
;
; Write a method that, given a guess and a solution, returns the number of hits
; and pseudo hits.

(ns cracking.p_19_05
  (:use [clojure.test])
  (:use [cracking.util :only (in? remove-first)]))

(defn remove-exact [s g]
  (let [zipped (remove #(= (first %) (second %)) (map vector s g))]
    [(map first zipped) (map second zipped)]))

(defn remove-pseudo [s g]
  (reduce
    (fn [[s g] x]
      (if (in? s x)
        [(remove-first s x) (remove-first g x)]
        [s g]))
    [s g] g))

; * removes/counts all exact guesses
; * then removes/counts all pseudo guesses
(defn score [solution guess]
  (let [[s g] (remove-exact solution guess)
        exact (- (count solution) (count s))
        [s g] (remove-pseudo s g)
        pseudo (- exact (count s))]
    [exact pseudo]))

(deftest a-test
  (testing
    (is (= (score [:r :g :g :b] [:y :r :g :b]) [2 1]))))
