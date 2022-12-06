(ns luissantos.aoc-2022.day-5
  (:require [clojure.string :as string]))


(defn transpose [xs]
  (apply map list xs))


(defn build-stacks [i]
  (->> (string/split-lines i)
       (map #(re-seq #"\[\w\]|    |[0-9]" %))
       (take-while (complement nil?))
       (transpose)
       (map reverse)
       (map #(remove string/blank? %))
       (reduce #(assoc %1 (first %2) (rest %2)) (sorted-map))))

(defn  parse-steps [i]
  (->> (string/split-lines i)
       (map #(re-seq #"move (\d*) from (\d) to (\d)" %))
       (filter (complement nil?))
       (map (comp rest first))
       (map #(cons (parse-long (first %)) (rest %)))))


(defn apply-step [stacks [n a b]]
  (let [sa  (stacks a)
        sb  (stacks b)]
    (-> stacks
        (assoc a (drop-last n sa))
        (assoc b (concat sb (take-last n sa))))))

(let [data (slurp "input/day-5-input.txt")
      stacks (build-stacks data)]
  (->> (parse-steps data)
       (reduce apply-step stacks)
       (vals)
       (map (comp second last))
       (reduce str)))
