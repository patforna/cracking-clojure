; 19.06 Given an integer between 0 and 999,999, print an English phrase that
; describes the integer (eg, “One Thousand, Two Hundred and Thirty Four”).

(ns cracking.p_19_06
  (:use [clojure.test])
  (:use [clojure.string :only (join trim)])
  (:use [cracking.util :only (digits)]))

(def ones { 1 "One" 2 "Two" 4 "Four" })

(def tens { 3 "Thirty" })

(def powers { 2 "Hundred" 3 "Thousand" })

; returns a sequence of [i x] pairs, where i describe the power to which d
; is raised.
(defn index [ds]
  (reverse (map-indexed vector (reverse ds))))

; creates a word for digit d raised to power p.
(defn word [[p d]]
  (if (= p 1)
    (tens d)
    (trim (str (ones d) " " (powers p)))))

(defn say [n]
  (let [d (digits n)
        i (index d)
        w (reduce #(conj %1 (word %2)) [] i)]
    (join ", " (remove empty? w))))

(deftest a-test
  (testing
    (is (= (say 1) "One"))
    (is (= (say 200) "Two Hundred"))
    (is (= (say 201) "Two Hundred, One"))
    (is (= (say 4201) "Four Thousand, Two Hundred, One"))
    (is (= (say 30) "Thirty"))
    (is (= (say 31) "Thirty, One"))
    (is (= (say 1234) "One Thousand, Two Hundred, Thirty, Four"))))
