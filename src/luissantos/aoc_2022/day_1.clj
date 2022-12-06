(ns luissantos.aoc-2022.day-1
  (:require [clojure.string :as string]))

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
