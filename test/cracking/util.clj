(ns cracking.util)

(defn in?
  "Returns true if elem is present in coll."
  [coll elem]
  (not (nil? (some #{elem} coll))))

(defn remove-at [coll i]
  "Removes elemet at index i from coll"
  (vec (concat (subvec coll 0 i) (subvec coll (inc i)))))

(defn swap [v i1 i2]
  "Swap element at i1 with element at i2"
  (assoc v i2 (v i1) i1 (v i2)))

(defn indices [m]
  "returns the indices (pairs) of matrix m"
  (let [dim (range (count m))]
    (for [x dim y dim] [x y])))

(defn avg [numbers]
  "Computes the average of the items in numbers"
  (double (/ (apply + numbers) (count numbers))))

(defn diff [a b]
  "returns the difference between a and b"
  (Math/abs (- a b)))

(defn roughly? [a b d]
  "Returns true if the difference between a and b is less than d"
  (< (diff a b) d))

(defn digits [n]
  "creates a sequence of digits"
  (map #(Character/getNumericValue %) (str n)))

(defn pow
  "computes b (defaults to 10) raised to the power of e"
  ([e] (pow 10 e))
  ([b e] (int (Math/pow b e))))

(defn limit [v l u]
  "limits value v to lower and upper bounds l and u, respectively (inclusive)."
  (max (min v u) l))

(defn drop-leading [d n]
  "drops leading digits d from n. Example for d=2 and n=5234 => 34"
  (- n (* (int (/ n (pow d))) (pow d))))

(defn char-range [start end]
  "Returns a seq of chars from start to end (inclusive)."
  (map char (range (int start) (inc (int end)))))

(defn pairs [c1 c2]
  "Generate the combination of pairs from c1 and c2"
  (if (or (empty? c1))
    []
    (let [xs (map #(vec [(first c1) %]) c2)]
      (concat xs (pairs (rest c1) c2)))))

(defn remove-first [c x]
  "Removes the first occurence of x in c"
  (let [[l r] (split-with (partial not= x) c)]
    (concat l (rest r))))

