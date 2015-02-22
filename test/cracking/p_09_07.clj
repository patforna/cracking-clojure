; 9.7 A circus is designing a tower routine consisting of people standing atop
; one another’s shoulders. For practical and aesthetic reasons, each person
; must be both shorter and lighter than the person below him or her. Given the
; heights and weights of each person in the circus, write a method to compute
; the largest possible number of people in such a tower.
;
; EXAMPLE:
; Input (ht, wt): (65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)
; Output: The longest tower is length 6 and includes from top to bottom:
; (56, 90) (60,95) (65,100) (68,110) (70,150) (75,190)

(ns cracking.p_09_07
  (:use [clojure.test]))

(def height first)

(def weight second)

; creates a map with ks as keys and their index as values, i.e. [k i]
(defn index-by [ks]
  (into {} (map-indexed (comp vec reverse vector) ks)))

; determines if it's valid to put person p on top of other people ps.
(defn can-stack? [ps p]
  (let [[lh lw] (last ps) [ph pw] p]
    (or (empty? ps) (and (<= ph lh) (<= pw lw)))))

; create valid stack, bottom up, by who can shoulder most.
(defn stack-em [m]
  (reduce
    (fn [ps [p _]]
      (if (can-stack? ps p) (conj ps p) ps)) [] (reverse (sort-by val m))))

; Algorithm:
; * Create two sorted collections, one by height and one by weight.
; * Then create another collection, which shows how many others each person
;   can put on their shoulders (this is computed by taking the min index of each
;   person in the above two collections).
; * Finally build the stack from the bottom up by iteratively picking the person
;   who can shoulder the largest number of others.
(defn stack [p]
  (let [h (index-by (sort-by height p))
        w (index-by (sort-by weight p))
        m (merge-with min h w)
        s (stack-em m)]
    (reverse s)))

(deftest a-test
  (testing
    (is (= (stack [[3 3] [1 1] [2 2]]) [[1 1] [2 2] [3 3]]))
    (is (= (stack [[1 4] [2 2] [3 3]]) [[2 2] [3 3]]))
    (is (= (stack [[5 1] [2 2] [3 3]]) [[2 2] [3 3]]))
    (is (= (stack [[5 2] [4 1] [3 3]]) [[4 1] [5 2]]))
    (is (= (stack [[65 100] [70 150] [56 90] [75 190] [60 95] [68 110]])
           [[56 90] [60 95] [65 100] [68 110] [70 150] [75 190]]))
    ))
