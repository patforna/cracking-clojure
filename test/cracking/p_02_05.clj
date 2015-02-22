; 2.5 Given a circular linked list, implement an algorithm which returns node
; at the beginning of the loop.
;
; DEFINITION
; Circular linked list: A (corrupt) linked list in which a node’s next pointer
; points to an earlier node, so as to make a loop in the linked list.
;
; EXAMPLE
; input: A -> B -> C -> D -> E -> C [the same C as earlier]
; output: C

(ns cracking.p_02_05
  (:use [clojure.test]))

(defn different? [[a b]] (not= a b))

(defn first-elem [[a b]] a)

; This is a solution using "Floyd's cycle-finding algorithm"
(defn detect-loop [coll]
  (let [tortoise (drop 1 coll)
        hare (drop 2 coll)
        steps (map vector tortoise (take-nth 2 hare))
        met (drop-while different? steps)
        reset (map vector coll (map first-elem met))]
    (first-elem (first (drop-while different? reset)))))

(def circular (concat [\a \b] (cycle [\c \d \e])))

(deftest a-test
  (testing
    (is (= (detect-loop circular) \c))))
