(ns luissantos.aoc-2022
  (:require [clojure.string :as string])
  (:gen-class))

(defn day1-part2
  "Assumes that there no snacks with Zero calories"
  []
  (->> (string/split-lines (slurp "input/day-1-input-1.txt"))
       (map #(if (empty? %) 0 (Integer/parseInt %)))
       (partition-by zero?)
       (map #(reduce + %))
       (sort >)
       (take 3)
       (reduce +)))

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

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(def priorities
  (zipmap
   (concat (char-range \a \z) (char-range \A \Z))
   (range 1 53)))

(defn parse-rucksack [rs]
  (let [rs (seq rs)]
    (split-at (/ (count rs) 2) rs)))

(defn item-priority [col]
  (->> col
       (map set)
       (apply clojure.set/intersection)
       (first)
       (priorities)))

(defn day3-part1 []
  (->> (string/split-lines (slurp "input/day-3-input-1.txt"))
       (map parse-rucksack)
       (map item-priority)
       (reduce +)))

(defn day3-part2 []
  (->> (string/split-lines (slurp "input/day-3-input-1.txt"))
       (partition 3)
       (map seq)
       (map item-priority)
       (reduce +)))

(defn -main
  [& args]
  (println (day3-part2)))
