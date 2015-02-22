; 3.4 In the classic problem of the Towers of Hanoi, you have 3 rods and N
; disks of different sizes which can slide onto any tower. The puzzle starts
; with disks sorted in ascending order of size from top to bottom (e.g., each
; disk sits on top of an even larger one).
; You have the following constraints:
;
; (A) Only one disk can be moved at a time.
; (B) A disk is slid off the top of one rod onto the next rod.
; (C) A disk can only be placed on top of a larger disk.
;
; Write a program to move the disks from the first rod to the last using Stacks.

(ns cracking.p_03_04
  (:use [clojure.test]))

(defn done? [pegs] (every? empty? (take 2 pegs)))

(defn index-of "returns the index of item in coll that is equal or nil"
  [item coll]
  (first (first (filter (fn [[_ v]] (= v item)) (map-indexed vector coll)))))

(defn smallest "returns index of peg with smallest disc" [peg]
  (index-of (first (sort (remove nil? peg))) peg))

(defn next-smallest "returns index of peg with second smallest disc" [peg]
  (index-of (second (sort (remove nil? peg))) peg))

(defn top-discs [pegs] (map peek pegs))

(defn legal?
  "returns true if the move would result in a legal arrangement of discs" [pegs [from to]]
  (let [above (peek (nth pegs from))
        below (peek (nth pegs to))]
    (or (nil? below) (< above below))))

(defn find-move [pegs dir from]
  (let [left (rest (drop-while #(not= % from) dir))
        moves (map vector (repeat 2 from) left)]
    (first (drop-while #(not (legal? pegs %)) moves))))

(defn move-disc [pegs [from to]]
  (let [disc (peek (nth pegs from))]
    (-> pegs
        (update-in [from] pop)
        (update-in [to] #(conj % disc)))))

; Solution:
; * DIR = LEFT if nbr-of-discs is ODD, RIGHT otherwise
; * Move smallest disc, legally, to next peg in DIR (if no peg, continue on
;   opposite end)
; * Move second-smallest disc to next peg in DIR.
; * Repeat until complete.
(defn hanoi [nbr-of-discs]
   (let [dir (cycle (if (even? nbr-of-discs) [1 2 0] [0 2 1]))]
   (loop [pegs [(vec (reverse (range 1 (+ nbr-of-discs 1))))[][]]
          disc-picker (cycle [smallest next-smallest])]
   ;(println pegs)
   (if (done? pegs)
     pegs
     (let [nxt ((first disc-picker) (top-discs pegs))
           move (find-move pegs dir nxt)]
       (recur (move-disc pegs move) (rest disc-picker)))))))

(deftest a-test
  (testing
    (is (= (hanoi 5) [[] [] [5 4 3 2 1]]))
    (is (= (hanoi 6) [[] [] [6 5 4 3 2 1]]))))
