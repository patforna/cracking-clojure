; 20.9 Numbers are randomly generated and passed to a method. Write a program
; to find and maintain the median value as new values are generated.

(ns cracking.p_20_09
  (:use [clojure.test])
  (:use [cracking.util :only (diff)]))

; If l is empty or n <= top of l, insert into l else, insert into r.
; l and r are treated as max and min heaps, respectively.
(defn insert [l r n]
  (if (or (empty? l) (<= n (peek l)))
    [(vec (sort (conj l n))) r] ; insert left
    [l (reverse (sort (conj r n)))])) ; insert right

; If l and r are equal size, do nothing. If more items in l, move top of l to r.
; If more items in r, move top of r to l.
(defn balance [l r]
  (cond (<= (diff (count l) (count r)) 1) [l r]
        (> (count l) (count r)) (let [n (peek l)] [(pop l) (conj r n)])
        :else (let [n (peek r)] [(conj l n) (pop r)])))

; If l has more elements than r, returns the largest item in r otherwise,
; return the average of the largest item in l and smallest in r
(defn median [l r]
  (double
    (if (or (> (count l) (count r)) (empty? r))
      (peek l)
      (/ (+ (peek l) (peek r)) 2))))

; Computes median after adding n
(defn next-median [[m l r] n]
  (let [[l r] (insert l r n) [l r] (balance l r) m (median l r)] [m l r]))

; * Create a max heap (left) and a min heap (right). As new numbers arrive,
; * If the number is smaller or equal than the top item on the left heap, add
;   it there. if not, add it to the right heap.
; * Then balance the two heaps so that their size won't differ by more than 1
; * The median is then either the top item on the left or the average of both
;   top items
; NOTE: we're actually using sorted arrays instead of heaps.
(defn medians [numbers]
  (map first (drop 1 (reductions next-median nil numbers))))

(deftest a-test
  (testing
    (is (= (take 5 (medians (cycle [5 4 1 3 2]))) [5.0 4.5 4.0 3.5 3.0]))))
