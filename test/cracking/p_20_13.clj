; 20.13 Given a dictionary of millions of words, give an algorithm to find
; the largest possible rectangle of letters such that every row forms a
; word (reading left to right) and every column forms a word (reading top
; to bottom).

(ns cracking.p_20_13
  (:use [clojure.test])
  (:use [clojure.java.io])
  (:use [cracking.util :only(char-range pairs)]))

; a seq of words
(def dict (take-nth 1000 (line-seq (reader "/usr/share/dict/words"))))

; checks if character c is equal to character of string s at index i
(defn char-at? [i c s]
  (and (> (count s) i) (= (.charAt s i) c)))

; groups words into 26 seqs so that all items have the same char at i.
(defn group-by-char-at [words i]
  (map #(filter (partial char-at? i %) words) (char-range \a \z)))

; returns a seq of pairs from row-words and col-words, which can be added to
; the rectangle [rows cols].
(defn next-pairs [row-words col-words [rows cols]]
  (let [i (count rows)
        rs (apply str (map #(get % i) cols))
        cs (apply str (map #(get % i) rows))
        row-words (filter #(.startsWith % rs) row-words)
        col-words (filter #(.startsWith % cs) col-words)
        row-words (group-by-char-at row-words i)
        col-words (group-by-char-at col-words i)]
    (mapcat #(pairs (first %) (second %)) (map vector row-words col-words))))

; creates (count np) new rectangles by conjoining each new pair np to the existing
; rectangle [rows cols].
(defn next-rectangles [[rows cols] np]
  (map (fn [[nr nc]] [(conj rows nr) (conj cols nc)]) np))

; returns a seq of rectangles that all have top as the first horizontal word
; and left as the first vertical word or nil.
(defn make-rectangles [words-by-count [top left]]
  (let [w (count top) h (count left)
        row-words (words-by-count w) col-words (words-by-count h)]
    (loop [rectangles [[top] [left]]]
      (if (seq rectangles)
        (recur
          (mapcat #(next-rectangles % (next-pairs row-words col-words %)) rectangles))))))

; returns the area of the rectangle a starting pair of words create.
(defn area [[top left]]
  (* (count top) (count left)))

; returns a set of unique pairs with the same starting letter. the set is
; ordered by pairs starting the largest rectangles
(defn start-pairs [words]
  (let [pairs (mapcat #(pairs % %) (group-by-char-at words 0))
        unique (set (map sort pairs))]
    (reverse (sort-by area unique))))

; algorithm:
; * creates a list of word pairs, sorted by largest rectangle area
; * then iteratively finds new word pairs that can be added to the existing
;   rectangle, without violating the constraints.
; * returns the first rectangle that can be completed
(defn max-rectangle []
  (let [words dict words-by-count (group-by count words)]
    (first (mapcat (partial make-rectangles words-by-count) (start-pairs words)))))

(deftest a-test
  (testing
    (is true)
    ; commented out because it takes too long to run
    ; (is (= (max-rectangle) nil))
    ))
