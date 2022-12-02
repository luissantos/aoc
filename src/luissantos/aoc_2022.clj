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

(defn -main
  ""
  [& args]
  (println (day1-part2)))
