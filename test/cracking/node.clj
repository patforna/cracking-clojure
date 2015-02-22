(ns cracking.node
  (:require [clojure.test :refer :all]))

(defn value "returns value of node" [[v _ _]] v)

(defn left "returns left subtree of node" [[_ l _]] l)

(defn right "returns right subtree of node" [[_ _ r]] r)

(defn node "finds node in subtree n containing value v" [n v]
  (if n
    (if (= v (value n))
      n
      (or (node (left n) v) (node (right n) v)))))

(defn child? "checks if node c is a child of node c" [n c]
  (or (= c (left n)) (= c (right n))))

(defn parent "find parent of node c in tree n" [n c]
  (if (and n c)
    (if (child? n c)
      n
      (or (parent (left n) c) (parent (right n) c)))))

(defn right? "checks if node c is a right child in tree r" [r c]
  (= c (right (parent r c))))

(defn ancest "returns ancestors of node n in tree r" [r n]
  (let [p (parent r n)]
    (if p
      (concat [p] (ancest r p)))))

(defn in-order "returns node values" [n]
  (let [[v l r] n]
    (if v
      (concat (in-order l) [v] (in-order r)))))

