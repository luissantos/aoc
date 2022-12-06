(ns luissantos.aoc-2022.day-3
  (:require [clojure.string :as string]
            [clojure.set :as cset]))



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