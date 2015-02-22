; 8.2 Imagine a robot sitting on the upper left hand corner of an NxN grid. The
; robot can only move in two directions: right and down. How many possible
; paths are there for the robot?
;
; FOLLOW UP: Imagine certain squares are “off
; limits”, such that the robot can not step on them. Design an algorithm to get
; all possible paths for the robot.

(ns cracking.p_08_02
  (:use [clojure.test]))

(defn paths [[x y] size]
  (cond (= x y size) 1
        (or (> x size) (> y size)) 0
        :else (+ (paths [(+ x 1) y] size) (paths [x (+ y 1)] size))))

(deftest a-test
  (testing
    (is (= (paths [1 1] 1) 1))
    (is (= (paths [1 1] 3) 6))
    (is (= (paths [1 1] 6) 252))))
