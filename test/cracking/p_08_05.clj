; 8.5 Implement an algorithm to print all valid (e.g., properly opened and
; closed) combinations of n-pairs of parentheses.
; EXAMPLE: input: 3, output: ()()(), ()(()), (())(), ((()))

(ns cracking.p_08_05
  (:use [clojure.test]))

; decide if it's currently possible to open left, right or both. Then prepend
; to recursive call for rest.
; o: opened, c: closed, r: remaining (to open)
(defn ps [o c r]
  (set
    (let [open? (> r 0) close? (> (- o c) 0)
          open (fn [] (map #(str "(" %) (ps (inc o) c (dec r))))
          close (fn [] (map #(str ")" %) (ps o (inc c) r)))]
      (if (or open? close?)
        (concat (if open? (open)) (if close? (close)))
        [""]))))

(defn parens [n] (ps 0 0 n))

(deftest a-test
  (testing
    (is (= (parens 1) #{"()"}))
    (is (= (parens 3) #{"()()()" "()(())" "(())()" "((()))" "(()())"}))))
