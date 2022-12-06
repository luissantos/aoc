(ns luissantos.aoc-2022.day-6)


(defn sliding-window [seq length]
  (partition length 1 seq))

(defn find-marker [m-size col]
  (->> (sliding-window col m-size)
       (reduce (fn [acc n]
                 (if (= (count (set n)) m-size)
                   (reduced (+ acc (count n)))
                   (inc acc))) 0)))


(let [col (slurp "input/day-6-input.txt")]
  {:part1 (find-marker 4 col)
   :part2 (find-marker 14 col)})
