(ns luissantos.aoc-2022
  (:require [clojure.string :as string])
  (:gen-class))

(defn day1-part2
  "Assumes that there no snacks with Zero calories"
  []
  (->> (string/split-lines (slurp "input/day-1-input-1.txt"))
       (map #(if (empty? %) 0 (parse-long %)))
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
  (split-at (/ (count rs) 2) rs))

(defn item-priority [col]
  (->> (map set col)
       (apply clojure.set/intersection)
       (first)
       (priorities)))

(defn day3 []
  (let [lines (string/split-lines (slurp "input/day-3-input-1.txt"))]
    {:part1 (reduce + (map item-priority (map parse-rucksack lines)))
     :part2 (reduce + (map item-priority (partition 3 lines)))}))

(defn parse-assignment [a]
  (->> (string/split a #"-|,")
       (map parse-long)
       (partition 2)
       (map (fn [[a b]] (range a (inc b))))
       (map set)))

(defn subset? [[a b]]
  (or (clojure.set/subset? a b)
      (clojure.set/subset? b a)))

(defn intersection? [[a b]]
  (not (empty? (clojure.set/intersection a b))))

(defn day4 []
  (let [in (slurp "input/day-4-input-1.txt")
        assignments (map parse-assignment (string/split-lines in))
        count-assignments #(count (filter true? %))]
    {:part1 (count-assignments (map subset? assignments))
     :part2 (count-assignments (map intersection? assignments))}))

(defn -main
  [& args]
  (println (day4)))

