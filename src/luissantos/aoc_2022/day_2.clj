(ns luissantos.aoc-2022.day-2
  (:require [clojure.string :as string]))

(defn round-score [round]
  (case round
    [:rock :paper] (+ 0 1)
    [:rock :scissors] (+ 6 1)
    [:paper :rock] (+ 6 2)
    [:paper :scissors] (+ 0 2)
    [:scissors :rock] (+ 0 3)
    [:scissors :paper] (+ 6 3)
    [:rock :rock] (+ 3 1)
    [:paper :paper] (+ 3 2)
    [:scissors :scissors] (+ 3 3)))

(defn pick-correct-hand [round]
  (case round
    [:x :paper] [:rock :paper]
    [:x :rock]  [:scissors :rock]
    [:x :scissors] [:paper :scissors]
    [:y :paper] [:paper :paper]
    [:y :rock]  [:rock :rock]
    [:y :scissors] [:scissors :scissors]
    [:z :paper] [:scissors :paper]
    [:z :rock]  [:paper :rock]
    [:z :scissors] [:rock :scissors]))

(defn day2-part2 []
  (let [hands {\A :rock \B :paper \C :scissors \X :x \Y :y \Z :z}]
    (->> (string/split-lines (slurp "input/day-2-input-1.txt"))
         (map #(string/replace % " " ""))
         (map #(map hands %))
         (map reverse)
         (map pick-correct-hand)
         (map round-score)
         (reduce +))))