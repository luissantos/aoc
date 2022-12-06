(ns luissantos.aoc-2022.day-4
  (:require [clojure.string :as string]
            [clojure.set :as cset]))


(defn parse-assignment [a]
  (->> (string/split a #"-|,")
       (map parse-long)
       (partition 2)
       (map (fn [[a b]] (range a (inc b))))
       (map set)))

(defn subset? [[a b]]
  (or (cset/subset? a b)
      (cset/subset? b a)))


(defn intersection? [[a b]]
  (not (empty? (cset/intersection a b))))

(defn day4 []
  (let [in (slurp "input/day-4-input-1.txt")
        assignments (map parse-assignment (string/split-lines in))
        count-assignments #(count (filter true? %))]
    {:part1 (count-assignments (map subset? assignments))
     :part2 (count-assignments (map intersection? assignments))}))